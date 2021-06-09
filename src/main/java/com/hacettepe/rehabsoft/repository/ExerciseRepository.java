package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    List<Exercise> findAllByOrderByIdDesc();

    Exercise getByExerciseName(String name);

}
