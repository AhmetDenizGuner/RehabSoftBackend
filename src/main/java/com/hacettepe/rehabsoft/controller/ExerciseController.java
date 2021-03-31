package com.hacettepe.rehabsoft.controller;


import com.hacettepe.rehabsoft.dto.ExerciseDto;
import com.hacettepe.rehabsoft.entity.ExerciseVideo;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.ExerciseService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, maxAge = 3600)
@RequestMapping(ApiPaths.ExercisePath.CTRL)
@RestController
@Api(value = "/api/exercises")
public class ExerciseController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    ExerciseService exerciseService;


    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createExercise(
            @RequestParam(value = "exerciseMediaList", required = false) MultipartFile[] exerciseMedia,
            @Valid @RequestParam("model") String exerciseJSON) throws Exception {
        log.warn("exercise creation controllerına girdi");
        String message = exerciseService.save(exerciseJSON, exerciseMedia);
        responseMessage.setResponseMessage(message);
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteExercise(@PathVariable(value = "id") Long id){
        log.warn("exercise delete controllerına girdi: "+id);
        exerciseService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<ExerciseDto>> listExercises(){
        log.warn("listExercises metoduna girdi");
        List<ExerciseDto> exerciseList = exerciseService.getAll();
        return ResponseEntity.ok(exerciseList);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable(value = "id") Long id){
        log.warn("getExerciseById() metoduna girdi");
        ExerciseDto exercise = exerciseService.getById(id);
        return ResponseEntity.ok(exercise);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> updateExercise(
            @RequestParam(value = "exerciseMediaList", required = false) MultipartFile[] exerciseMedia,
            @Valid @RequestParam("model") String exerciseJSON) throws Exception {
        log.warn("exercise update controllerına girdi");
        String message = exerciseService.updateExercise(exerciseJSON, exerciseMedia);
        responseMessage.setResponseMessage(message);
        return ResponseEntity.ok(responseMessage);
    }

    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/getimage/{id}",method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getExerciseImageById(@PathVariable Long id) throws IOException {
        log.warn("getExerciseImage() metoduna girdi "+id);
        byte[] exerciseImage = exerciseService.getExerciseImageById(id);
        if(exerciseImage==null){
            log.error("Egzersiz resmi bulunamadi ");
            responseMessage.setResponseMessage("Hata oldu");
            return ResponseEntity.badRequest().body(null);
        }
        else {
            log.error("Egzersiz resmi basariyla bulundu ");
            return ResponseEntity.ok(exerciseImage);
        }
    }

}
