package com.hacettepe.rehabsoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Assigned Form")
public class AssignedFormDto {

    @NotEmpty
    private LocalDateTime creationDate;

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @NotEmpty
    @ApiModelProperty(required = true, value = "formDynamic")
    private FormDynamicDto formDynamic;

    @NotEmpty
    @ApiModelProperty(required = true, value = "patient")
    private PatientDto patient;

    @NotEmpty
    @ApiModelProperty(required = true, value = "isAnswered")
    private boolean isFormAnswered;

    @ApiModelProperty(required = true, value = "formAnswersCollection")
    private Collection<FormAnswerDto> formAnswersCollection;

}

