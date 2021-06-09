package com.hacettepe.rehabsoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Physiotherapy Program")
public class PhysiotherapyProgramDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @NotEmpty
    private LocalDateTime creationDate;

    @NotEmpty
    @ApiModelProperty(required = true,value = "doctor")
    private DoctorDto doctor;

    @Size(max = 500)
    @NotEmpty
    @ApiModelProperty(required = true,value = "goal")
    private String goal;

    @NotEmpty
    @ApiModelProperty(required = true,value = "patient")
    private PatientDto patient;

    @NotNull
    @ApiModelProperty(required = true,value = "isActive")
    private Boolean isActive;

    @NotEmpty
    @ApiModelProperty(required = true,value = "startDate")
    private LocalDateTime startDate;

    @NotEmpty
    @ApiModelProperty(required = true,value = "finishDate")
    private LocalDateTime finishDate;

    @ApiModelProperty(required = true,value = "scheduledExerciseCollection")
    private Collection<ScheduledExerciseDto> scheduledExerciseCollection;
}
