package com.hacettepe.rehabsoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "Data Transfer Object for Password Reset")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {


    @Email
    @NotBlank(message = "Lütfen e-mailinizi giriniz")
    private String email;
}
