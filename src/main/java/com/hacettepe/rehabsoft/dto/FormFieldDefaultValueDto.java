package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.FormField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Form Field Default Value")
public class FormFieldDefaultValueDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @Size(max = 255)
    @NotEmpty
    @ApiModelProperty(required = true, value = "defaultValueName")
    private String valueName;


}
