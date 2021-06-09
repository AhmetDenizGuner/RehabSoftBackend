package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.FormDynamic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Form Template")
public class FormTemplateDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    @NotEmpty
    @ApiModelProperty(required = true, value = "formDynamic")
    private FormDynamicDto formDynamic;

    @NotEmpty
    @ApiModelProperty(required = true, value="user")
    private UserDto user;

    @NotEmpty
    private LocalDateTime creationDate;

}
