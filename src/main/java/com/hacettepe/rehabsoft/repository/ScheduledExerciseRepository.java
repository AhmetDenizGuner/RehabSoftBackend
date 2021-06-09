package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.ScheduledExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduledExerciseRepository extends JpaRepository<ScheduledExercise,Long> {

    @Query("update ScheduledExercise s set s.isApplied=?2 where s.id = ?1")
    @Transactional
    @Modifying
    void updateAllScheduledExerciseAsApplied(Long scheduledExerciseId, Boolean isApplied);
}
