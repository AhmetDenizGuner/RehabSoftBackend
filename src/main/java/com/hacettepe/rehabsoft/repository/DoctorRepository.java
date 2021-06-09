package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Doctor;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor getDoctorByUser(User user);

    Doctor getDoctorByPatientCollectionContains(Patient patient);

    Boolean removeByUserId(Long id);

    Boolean existsByUserId(Long id);



}
