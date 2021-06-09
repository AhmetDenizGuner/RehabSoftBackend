package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.PhysiotherapyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhysiotherapyProgramRepository extends JpaRepository<PhysiotherapyProgram,Long> {
    List<PhysiotherapyProgram> findAllByPatientOrderByCreationDateDesc(Patient patient);
}
