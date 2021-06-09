package com.hacettepe.rehabsoft.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.PhysiotherapyProgramDto;
import com.hacettepe.rehabsoft.entity.PhysiotherapyProgram;

import java.util.List;

public interface PhysiotherapyProgramService {

    List<PhysiotherapyProgramDto> getAllAssignedExercisePrograms(String tcKimlikNo);
    boolean assignProgram(PhysiotherapyProgramDto physiotherapyProgramDto) throws FirebaseMessagingException;

    boolean delete(Long id);

    boolean updateProgram(PhysiotherapyProgramDto physiotherapyProgramDto);
    PhysiotherapyProgramDto getById(Long id);
}
