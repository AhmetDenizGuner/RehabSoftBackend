package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.AssignedForm;
import com.hacettepe.rehabsoft.entity.FormField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Form Answers")
public class FormAnswerDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @NotEmpty
    @Size( max = 500)
    @ApiModelProperty(required = true, value = "answer")
    private String answer;

    @NotEmpty
    @ApiModelProperty(required = true, value = "assignedForm")
    private AssignedFormDto assignedForm;

    @NotEmpty
    @ApiModelProperty(required = true, value = "formField")
    private FormField formField;

}
