package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.FormDynamic;
import com.hacettepe.rehabsoft.entity.FormFieldDefaultValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Form Field")
public class FormFieldDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @Size(max= 600)
    @NotEmpty
    @ApiModelProperty(required = true, value = "formFieldName")
    private String fieldName;

    @Size(max= 255)
    @NotEmpty
    @ApiModelProperty(required = true, value = "fieldType")
    private String fieldType;

    @NotEmpty
    @ApiModelProperty(required = true, value = "fieldOrder")
    private int fieldOrder;


    @ApiModelProperty(required = true, value = "formFieldDefaultValueList")
    private Collection<FormFieldDefaultValueDto> formFieldDefaultValueCollection;

}
