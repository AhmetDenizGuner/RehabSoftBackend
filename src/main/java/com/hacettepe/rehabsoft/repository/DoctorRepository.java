package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Doctor;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor getDoctorByUser(User user);

    Doctor getDoctorByPatientCollectionContains(Patient patient);

    Boolean removeByUserId(Long id);

    Boolean existsByUserId(Long id);

    @Query("select d from Doctor d, Patient p where p.doctor.id = d.id and p.user.username = ?1")
    Doctor getDoctorByPatientUsername(String username);

    @Query("select d from Doctor d where d.user.username = ?1")
    Doctor getDoctorByUsername(String username);
}
