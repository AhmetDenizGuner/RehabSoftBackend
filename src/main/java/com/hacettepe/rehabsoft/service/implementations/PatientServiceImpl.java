package com.hacettepe.rehabsoft.service.implementations;


import com.hacettepe.rehabsoft.dto.*;
import com.hacettepe.rehabsoft.entity.Doctor;
import com.hacettepe.rehabsoft.entity.Parent;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.helper.SecurityHelper;
import com.hacettepe.rehabsoft.repository.DoctorRepository;
import com.hacettepe.rehabsoft.repository.PatientRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.ParentService;
import com.hacettepe.rehabsoft.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentService parentService;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;


    public Boolean isPatientAlreadySaved(){
        String username = securityHelper.getUsername();
        Patient patient = patientRepository.getPatientByUser(userRepository.findByUsername(username));
        if(patient!=null){
            return true;
        }
        return false;
    }


    public Boolean isIdentityNoExists(String tcKimlikNo){
        Patient patient = patientRepository.getPatientByTcKimlikNo(tcKimlikNo);
        if(patient!=null){
            return true;
        }
        return false;
    }



    @Override
    public PatientDto savePatient(PatientDto patientDto){

        log.warn("Patient servisine girdi:Save:");
        Patient patient = modelMapper.map(patientDto, Patient.class);

        //Log-in olmus user'ın ismi Spring Security'den alınarak atama yapılır
        patient.setUser(userRepository.findByUsername(securityHelper.getUsername()));
        patient.setGeneralEvaluationForm(null);

        //Simdilik doktor ataması yapmıyoruz.Database tarafında otomatik olarak 1 veriyor
        //Doktor servisini yazınca orada bir setDoc oluşturup burayı tekrar yazacağız
        patient.setDoctor(null);


        for(Parent parent:patient.getParentCollection()){
            parentService.saveParent(parent);
        }

        patientRepository.save(patient);

        return patientDto;
    }

    public List<PatientDetailsDto> getAllPatientUsers(){


        List<Patient> patientList = patientRepository.findAll();
        //gefd doldurulmamıssa sil
        ListIterator<Patient> iter = patientList.listIterator();
        while(iter.hasNext()){
            if(iter.next().getGeneralEvaluationForm()==null){
                iter.remove();
            }
        }


        if(patientList==null){
            return null;}

        List<PatientDetailsDto> patientDetailsDtoList=  Arrays.asList(modelMapper.map(patientList, PatientDetailsDto[].class));

        return patientDetailsDtoList;
    }


    @Override
    public PatientDetailsDto findPatientByTcKimlikNo(String tcKimlikNo) {

        User user = userRepository.findByUsername(tcKimlikNo); //hastanın username'i kimlik numarasıdır
        if (user==null){
            return null;
        }

        UserDto userDto = modelMapper.map(user,UserDto.class);

        PatientDetailsDto patientDetailsDto =new PatientDetailsDto(tcKimlikNo,userDto);

        return patientDetailsDto;

    }

    @Override
    public DoctorInfoDto receiveDoctorInfo() {

        Patient patient = patientRepository.getPatientByUser_Username(securityHelper.getUsername());
        if(patient==null){
            return null;

        }

        Doctor doctor = doctorRepository.getDoctorByPatientCollectionContains(patient);

        if(doctor==null){
            return null;
        }

        DoctorInfoDto doctorInfoDto = new DoctorInfoDto();
        doctorInfoDto.setName(doctor.getUser().getFirstName());
        doctorInfoDto.setSurname(doctor.getUser().getSurname());
        doctorInfoDto.setEmail(doctor.getUser().getEmail());

        return doctorInfoDto;
    }
}
