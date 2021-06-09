package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.AssignedForm;
import com.hacettepe.rehabsoft.entity.FormField;
import com.hacettepe.rehabsoft.entity.FormTemplate;
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
@ApiModel(value = "Data Transfer Object for Assigned Form Dynamic")
public class FormDynamicDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @NotEmpty
    @Size(max = 255)
    @ApiModelProperty(required = true, value = "title")
    private String title;

    @Size(max = 600)
    @ApiModelProperty(required = true, value = "explanation")
    private String explanation;

    @ApiModelProperty(required = true, value = "formFieldList")
    private Collection<FormFieldDto> formFieldCollection;



    /*@ApiModelProperty(required = true, value = "assignedFormCollection")
    private Collection<AssignedForm> assignedFormCollection;*/

}
