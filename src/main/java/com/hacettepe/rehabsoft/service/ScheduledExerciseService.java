package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.ScheduledExerciseDto;

import java.util.List;

public interface ScheduledExerciseService {
    public boolean updateScheduledExerciseListAsApplied(List<ScheduledExerciseDto> scheduledExerciseList);
}
