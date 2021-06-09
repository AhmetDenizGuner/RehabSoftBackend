package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.ExerciseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getAll();

    ExerciseDto getById(Long id);

    String save(String exerciseJSON, MultipartFile[] exerciseMedia ) throws Exception;

    Boolean delete(Long id) throws Exception;

    String updateExercise(String exerciseJSON, MultipartFile[] exerciseMedia) throws Exception;

    Boolean isExerciseNameExists(String name);

    byte[] getExerciseImageById(Long id) throws IOException;
}
