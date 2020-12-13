package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.entity.VideoRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Doctors and Physiotherapist")
public class DoctorDto {

    //OneToMany(mappedBy = "doctor")
    Collection<PatientDto> patientCollection;

    UserDto user;

    //OneToMany(mappedBy = "doctor")
    Collection<VideoRequestDto> videoRequestCollection;
}
