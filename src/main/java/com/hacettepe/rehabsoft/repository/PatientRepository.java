package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Doctor;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> getAllByOrderById();
    Patient getPatientByUser(User user);
    Patient getPatientByTcKimlikNo(String tcKimlikNo);
    //Patient getPatientByUserUsername(String username);
    Patient getPatientByUser_Username(String username);
    List<Patient> getAllByDoctor(Doctor doctor);

    Boolean existsByUserId(Long id);

}
