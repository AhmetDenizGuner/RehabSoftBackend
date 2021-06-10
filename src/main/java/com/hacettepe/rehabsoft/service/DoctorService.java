package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    String save(String username);
    List<DoctorDto> getAll();

    DoctorDto getDoctorByPatientUsername(String username);
}
