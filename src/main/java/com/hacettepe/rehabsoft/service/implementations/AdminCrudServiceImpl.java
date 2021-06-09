package com.hacettepe.rehabsoft.service.implementations;


import com.hacettepe.rehabsoft.dto.DoctorInfoDto;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserCrudDto;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.repository.*;
import com.hacettepe.rehabsoft.service.AdminCrudService;
import com.hacettepe.rehabsoft.service.AdminService;
import com.hacettepe.rehabsoft.service.DoctorService;
import com.hacettepe.rehabsoft.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCrudServiceImpl implements AdminCrudService {

    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;

    private final PatientRepository patientRepository;

    @Override
    public boolean createNewDoctor(RegistrationRequest registrationRequest) {
        //önce register'a gönder.Dönen değeri ise,
        if(userService.register(registrationRequest)){ //eger register işlemi başarılı ise, doctor'a çeviriyoruz
            //sonra doctor save'e gönder
            doctorService.save(registrationRequest.getUsername());
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteDoctor(Long id) {
    log.warn("Silme servisi aktif");

        if(doctorRepository.existsByUserId(id)){
            userService.deleteUser(id);
            return true;
        }

        return false;
    }

    @Override
    public List<UserCrudDto> listAllDoctors(){
        List<User> doctors= userRepository.getAllByRoleName("DOCTOR");

        if(doctors==null){
            return null;}

        return Arrays.asList(modelMapper.map(doctors, UserCrudDto[].class));


    }




    @Override
    public DoctorInfoDto readDoctorInfo() {
        return null;
    }




    @Override
    public boolean createNewAdmin(RegistrationRequest registrationRequest) {
        //önce register'a gönder.Dönen değeri ise,
        if(userService.register(registrationRequest)){ //eger register işlemi başarılı ise, admin'e çeviriyoruz
            adminService.save(registrationRequest.getUsername());
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteAdmin(Long id) {
        log.warn("Admin silme operasyonu aktif");

        if(adminRepository.existsByUserId(id)){
            userService.deleteUser(id);
            log.warn("Admin silme operasyonu basariyla sona erdi");
            return true;
        }

        return false;

    }


    @Override
    public List<UserCrudDto> listAllAdmins(){
        List<User> adminUsers= userRepository.getAllByRoleName("ADMIN");

        if(adminUsers==null){
            return null;}

        return Arrays.asList(modelMapper.map(adminUsers, UserCrudDto[].class));

    }



    @Override
    public boolean deletePatient(Long id) {
        log.warn("Patient silme operasyonu aktif:" + id);
        try {
            userService.deleteUser(id);
            log.warn("Patient silme operasyonunda bir sorun meydana geldi");
            return Boolean.TRUE;
        }
        catch (Exception e){
            return Boolean.FALSE;
        }



    }


    @Override
    public List<UserCrudDto> listAllPatients(){
        //User rolü sadece hastalara veriliyor.
        List<User> patients= userRepository.getAllByRoleName("USER");

        if(patients==null){
            return null;}

        return Arrays.asList(modelMapper.map(patients, UserCrudDto[].class));

    }





}
