package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.ScheduledExerciseDto;
import com.hacettepe.rehabsoft.entity.ScheduledExercise;
import com.hacettepe.rehabsoft.repository.ExerciseRepository;
import com.hacettepe.rehabsoft.repository.ScheduledExerciseRepository;
import com.hacettepe.rehabsoft.service.ScheduledExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledExerciseServiceImp implements ScheduledExerciseService {
    private final ScheduledExerciseRepository scheduledExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateScheduledExerciseListAsApplied(List<ScheduledExerciseDto> scheduledExerciseList) {
        List<ScheduledExercise> scheduledExercises = Arrays.asList(modelMapper.map(scheduledExerciseList, ScheduledExercise[].class));
        scheduledExercises.forEach(scheduledExercise -> scheduledExercise.setExercise(exerciseRepository.findById(scheduledExercise.getExercise().getId()).get()));
        scheduledExercises.forEach(scheduledExercise -> scheduledExerciseRepository.updateAllScheduledExerciseAsApplied(scheduledExercise.getId(),scheduledExercise.getIsApplied()));
        return true;
    }
}
