package com.hacettepe.rehabsoft.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.*;
import com.hacettepe.rehabsoft.entity.GeneralEvaluationForm;
import com.hacettepe.rehabsoft.entity.Patient;

import java.util.List;

public interface PatientService {


    PatientDto savePatient(PatientDto patientDto);

    Boolean isPatientAlreadySaved();

    Boolean isIdentityNoExists(String tcKimlikNo);
    List<PatientDetailsDto> getAllPatientUsers();

    PatientDetailsDto findPatientByTcKimlikNo(String tcKimlikNo);

    DoctorInfoDto receiveDoctorInfo();
    List<PatientDto> findPatientNewRegistred();

    Boolean setDoctorToPatient(String patientTC, String doctorUserID) throws FirebaseMessagingException;

    List<PatientDetailsDto> getAllPatientUsersByDoctor(String docUsername);


    PatientDto get(String tcKimlikNo);

    List<PatientDto> getPatientsByDoctor(String doctorUsername);
}
