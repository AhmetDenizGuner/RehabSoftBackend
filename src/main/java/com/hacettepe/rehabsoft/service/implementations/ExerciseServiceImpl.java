package com.hacettepe.rehabsoft.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacettepe.rehabsoft.dto.ExerciseDto;
import com.hacettepe.rehabsoft.dto.ExerciseVideoDto;
import com.hacettepe.rehabsoft.entity.*;
import com.hacettepe.rehabsoft.helper.BeanValidationDeserializer;
import com.hacettepe.rehabsoft.helper.FileOperationHelper;
import com.hacettepe.rehabsoft.helper.SecurityHelper;
import com.hacettepe.rehabsoft.repository.ExerciseRepository;
import com.hacettepe.rehabsoft.repository.ExerciseVideoRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.ExerciseService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service//(value = "exerciseService")
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseVideoRepository exerciseVideoRepository;
    private final UserRepository userRepository;
    private final SecurityHelper securityHelper;
    private final ObjectMapper objectMapper;
    private final FileOperationHelper fileOperationHelper;

    @Override
    public List<ExerciseDto> getAll(){
        List<Exercise> exercises = exerciseRepository.findAllByOrderByIdDesc();
        return  Arrays.asList(modelMapper.map(exercises, ExerciseDto[].class));
    }

    @Override
    public ExerciseDto getById(Long id){
        Exercise exercise = exerciseRepository.getOne(id);
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(String exerciseJSON, MultipartFile[] exerciseMediaList) throws Exception {

        System.out.println("exerciseJSON: "+exerciseJSON);
        // Mapping JSON to Object DTO and Entity after
        ExerciseDto exerciseDto = (ExerciseDto) BeanValidationDeserializer.deserializeJSONWithValidation(exerciseJSON, ExerciseDto.class);
        if(this.isExerciseNameExists(exerciseDto.getExerciseName())){
            return "Bu isim başka bir egzersiz için kullanılıyor.Lütfen başka bir isim seçiniz.";
        }
        Exercise tempExercise = modelMapper.map(exerciseDto, Exercise.class);
        tempExercise.setUser(userRepository.findByUsername(securityHelper.getUsername()));
        List<ExerciseVideo> exerciseVideoListToSave = (List<ExerciseVideo>) tempExercise.getExerciseVideoCollection();
        tempExercise.setExerciseVideoCollection(null);
        tempExercise = exerciseRepository.save(tempExercise);
        exerciseDto.setId(tempExercise.getId());
        fillExerciseVideoCollection( tempExercise, exerciseMediaList, exerciseVideoListToSave );
        return "Egzersiz başarıyla kaydedildi!";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) throws Exception {

        if(exerciseRepository.findById(id).isPresent()){
            deleteExerciseMediaFiles(exerciseRepository.findById(id).get());
            exerciseRepository.deleteById(id);
        }
        else{
            throw new Exception();
        }
        log.warn("Egzersiz silindi=>"+ id);
        return Boolean.TRUE;

    }


    @Override
    public Boolean isExerciseNameExists(String name) {
        return exerciseRepository.getByExerciseName(name) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateExercise(String exerciseJSON, MultipartFile[] exerciseFiles) throws Exception {

        ExerciseDto exerciseDto = (ExerciseDto) BeanValidationDeserializer.deserializeJSONWithValidation(exerciseJSON, ExerciseDto.class);
        Exercise dbExercise = exerciseRepository.findById(exerciseDto.getId()).orElseThrow(EntityNotFoundException::new);
        if(!dbExercise.getExerciseName().equals(exerciseDto.getExerciseName()) ){
            if(this.isExerciseNameExists(exerciseDto.getExerciseName())){
                return "Bu isim başka bir egzersiz için kullanılıyor.Lütfen başka bir isim seçiniz.";
            }
        }
        dbExercise.setExerciseName(exerciseDto.getExerciseName());
        dbExercise.setExerciseContent(exerciseDto.getExerciseContent());
        exerciseRepository.save(dbExercise);
        updateExerciseMediaCollection(exerciseFiles, dbExercise, exerciseDto);
        return "Egzersiz ile ilgili değişiklikler başarıyla kaydedildi!";
    }

    @Override
    public byte[] getExerciseImageById(Long id) throws IOException {
        Optional<ExerciseVideo> exerciseVideo = exerciseVideoRepository.findById(id);
        if(exerciseVideo.isPresent()){
            String path = exerciseVideo.get().getVideoUrl();
            return fileOperationHelper.readFileAsByte(path);
        } else{
            return null;
        }
    }

    private void isFileSizeProper(MultipartFile exerciseMediaList){
        // if(exerciseMediaList.getSize()>50000)
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateExerciseMediaCollection(MultipartFile[] exerciseFiles, Exercise exerciseFromDatabase, ExerciseDto exerciseDto) throws Exception {

        if(!exerciseDto.getExerciseVideoCollection().isEmpty()){
            cleanDeletedExerciseMedia(exerciseFromDatabase, exerciseDto);
            updateExerciseVideoNamesInCollection(exerciseDto.getExerciseVideoCollection());
            setExerciseVideoCollectionWithNewAddedExerciseVideo(exerciseFromDatabase, exerciseDto);
        }
        else{
            if(!exerciseFromDatabase.getExerciseVideoCollection().isEmpty()){
                cleanDeletedExerciseMedia(exerciseFromDatabase, exerciseDto);
                deleteExerciseMediaFiles(exerciseFromDatabase);
            }
        }

        fillExerciseVideoCollection(exerciseFromDatabase, exerciseFiles, (List<ExerciseVideo>) exerciseFromDatabase.getExerciseVideoCollection());
    }

    private void setExerciseVideoCollectionWithNewAddedExerciseVideo(Exercise exerciseFromDatabase, ExerciseDto exerciseDto) {
        exerciseFromDatabase.setExerciseVideoCollection(
                exerciseDto.getExerciseVideoCollection()
                        .stream()
                        .filter(exerciseVideoDto -> exerciseVideoDto.getId() == null)
                        .map(exerciseVideoDto ->  modelMapper.map(exerciseVideoDto, ExerciseVideo.class))
                        .collect(Collectors.toList())
        );
    }

    private void updateExerciseVideoNamesInCollection(Collection<ExerciseVideoDto> exerciseVideoCollection) {
        exerciseVideoCollection.forEach(exerciseVideoDto -> exerciseVideoRepository.updateTitleById(exerciseVideoDto.getTitle(), exerciseVideoDto.getId()));
    }

    private void cleanDeletedExerciseMedia(Exercise exerciseFromDatabase, ExerciseDto exerciseDto) {
        List<ExerciseVideo> deletingExerciseVideoList = exerciseFromDatabase.getExerciseVideoCollection()
                .stream()
                .filter(exerciseVideo -> !isIdInExerciseVideoCollection(exerciseVideo, exerciseDto.getExerciseVideoCollection()) )
                .collect(Collectors.toList());

        deletingExerciseVideoList.forEach(exerciseVideo -> {
            fileOperationHelper.deleteFileByPath(exerciseVideo.getVideoUrl());
        });
        exerciseFromDatabase.getExerciseVideoCollection().removeAll(deletingExerciseVideoList);
        exerciseVideoRepository.deleteAll(deletingExerciseVideoList);
    }

    private static boolean isIdInExerciseVideoCollection(ExerciseVideo exerciseVideo, Collection<ExerciseVideoDto> exerciseVideoCollectionFromDto){
        for(ExerciseVideoDto exerciseVideoDto:exerciseVideoCollectionFromDto){
            if(exerciseVideo.getId().equals(exerciseVideoDto.getId())){
                return true;
            }
        }
        return false;
    }

    private void deleteExerciseMediaFiles(Exercise exerciseFromDatabase) throws Exception {
        if(exerciseFromDatabase.getExerciseVideoCollection() != null) {
            String folderPath = ApiPaths.SavingExerciseMediaPath.CTRL + exerciseFromDatabase.getId()+"/";
            fileOperationHelper.deleteDirectoryByPath(folderPath);
        }
    }

    private void fillExerciseVideoCollection(Exercise exercise, MultipartFile[] exerciseMediaList, List<ExerciseVideo> exerciseVideoList) throws Exception {
        if(exerciseVideoList!=null){
            for(ExerciseVideo exerciseVideo:exerciseVideoList){
                saveExerciseMediaAndSetExerciseMediaUrls(exerciseVideo, exerciseMediaList, exercise);
            }
        }
    }

    private void saveExerciseMediaAndSetExerciseMediaUrls(ExerciseVideo exerciseVideo, MultipartFile[] exerciseMediaList, Exercise exercise)
            throws Exception {
        if( exerciseMediaList == null){
            return ;
        }

        for(MultipartFile multipartFile:exerciseMediaList){
            StringBuilder newFileName = new StringBuilder();
            String fileType = fileOperationHelper.popFileTypeFromFileName(multipartFile.getOriginalFilename(), newFileName);
            if(newFileName.toString().equals(exerciseVideo.getTitle())){
                exerciseVideo.setVideoUrl(null);
                ExerciseVideo persistedExerciseVideo = exerciseVideoRepository.save(exerciseVideo);
                String directoryAndMediaURL = fileOperationHelper.createURLWithDirectory(
                        ApiPaths.SavingExerciseMediaPath.CTRL+exercise.getId()+"",
                        persistedExerciseVideo.getId()+"-" + newFileName.toString(),
                        fileType+""
                );
                String savedUrl = fileOperationHelper.saveFileByDirectory(multipartFile, directoryAndMediaURL);
                persistedExerciseVideo.setVideoUrl(savedUrl);
                persistedExerciseVideo.setExercise(exercise);
                exerciseVideoRepository.save(persistedExerciseVideo);
            }
        }
    }

}
