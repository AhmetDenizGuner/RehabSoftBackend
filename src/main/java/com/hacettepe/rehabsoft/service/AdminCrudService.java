package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.DoctorInfoDto;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserCrudDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.User;

import java.util.List;

public interface AdminCrudService {


    boolean createNewDoctor(RegistrationRequest registrationRequest);
    boolean deleteDoctor(Long id);
    List<UserCrudDto> listAllDoctors();
    DoctorInfoDto readDoctorInfo();


    boolean createNewAdmin(RegistrationRequest registrationRequest);
    boolean deleteAdmin(Long id);
    List<UserCrudDto> listAllAdmins();

    boolean deletePatient(Long id);
    List<UserCrudDto> listAllPatients();

}
