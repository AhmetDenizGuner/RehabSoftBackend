package com.hacettepe.rehabsoft.service.implementations;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.PhysiotherapyProgramDto;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.PhysiotherapyProgram;
import com.hacettepe.rehabsoft.entity.ScheduledExercise;
import com.hacettepe.rehabsoft.repository.*;
import com.hacettepe.rehabsoft.service.NotificationService;
import com.hacettepe.rehabsoft.service.PhysiotherapyProgramService;
import com.hacettepe.rehabsoft.util.NotificationPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service(value = "exerciseProgramAssignService")
public class PhysiotherapyProgramServiceImpl implements PhysiotherapyProgramService {

    private final ModelMapper modelMapper;
    private final PhysiotherapyProgramRepository physiotherapyProgramRepository;
    private final ScheduledExerciseRepository scheduledExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final NotificationService notificationService;

    @Override
    @Transactional(readOnly = true)
    public List<PhysiotherapyProgramDto> getAllAssignedExercisePrograms(String tcKimlikNo) {
        List<PhysiotherapyProgram> physiotherapyPrograms = physiotherapyProgramRepository.findAllByPatientOrderByCreationDateDesc(patientRepository.getPatientByTcKimlikNo(tcKimlikNo));

        if( physiotherapyPrograms == null || physiotherapyPrograms.isEmpty()){
            log.warn("Belirtilen hastaya ait fegzersiz programı kaydı bulunmuyor");
            return Collections.emptyList();
        }

        List<PhysiotherapyProgramDto> physiotherapyProgramDtoList =  Arrays.asList(modelMapper.map(physiotherapyPrograms, PhysiotherapyProgramDto[].class));

        if( physiotherapyProgramDtoList.isEmpty()){
            log.warn("Belirtilen hastaya ait fegzersiz programı kaydı bulunmuyor");
            return Collections.emptyList();
        }

        return physiotherapyProgramDtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignProgram(PhysiotherapyProgramDto physiotherapyProgramDto) throws FirebaseMessagingException {
        PhysiotherapyProgram physiotherapyProgram = modelMapper.map(physiotherapyProgramDto, PhysiotherapyProgram.class);
        Patient patient = patientRepository.findById(physiotherapyProgram.getPatient().getId()).get();
        physiotherapyProgram.setDoctor(doctorRepository.findById(physiotherapyProgram.getDoctor().getId()).get());
        physiotherapyProgram.setPatient(patient);
        List<ScheduledExercise> scheduledExercises = new ArrayList<>();
        for (ScheduledExercise scheduledExercise : physiotherapyProgram.getScheduledExerciseCollection()){
            scheduledExercise.setExercise(exerciseRepository.findById(scheduledExercise.getExercise().getId()).get());
            scheduledExercise.setPhysiotherapyProgram(physiotherapyProgram);
            scheduledExercise.getScheduledDate().minusHours(1L);
            scheduledExercise.setIsApplied(false);
            ScheduledExercise scheduledExerciseFromDB = scheduledExerciseRepository.save(scheduledExercise);
            scheduledExercises.add(scheduledExerciseFromDB);
        }
        physiotherapyProgram.setScheduledExerciseCollection(scheduledExercises);
        physiotherapyProgramRepository.save(physiotherapyProgram);

        notificationService.createNotification(patient.getUser(),
                "Doktorunuz tarafından bir program atandı!",
                NotificationPaths.BASE_PATH+"/user/physiotherapy-program",
                true);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        physiotherapyProgramRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProgram(PhysiotherapyProgramDto physiotherapyProgramDto) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public PhysiotherapyProgramDto getById(Long id) {
        PhysiotherapyProgram physiotherapyProgram = physiotherapyProgramRepository.findById(id).get();
        return modelMapper.map(physiotherapyProgram, PhysiotherapyProgramDto.class);
    }
}
