package com.hacettepe.rehabsoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hacettepe.rehabsoft.entity.Doctor;
import com.hacettepe.rehabsoft.entity.Exercise;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.ResponseVideoRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Video Request From Patient")
public class VideoRequestDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    private String requestContent;

    private String requestTitle;

    //@ManyToOne
    private DoctorDto doctor;

    //@ManyToOne
    private PatientDto patient;

    //ManyToMany
    private Collection<ExerciseDto> exerciseCollection;

    //one to one
    private ResponseVideoRequestDto responseVideoRequest;

    @NotNull
    private LocalDateTime creationDate;

}
