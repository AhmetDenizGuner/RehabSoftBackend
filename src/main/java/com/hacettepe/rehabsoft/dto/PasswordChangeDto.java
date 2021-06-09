package com.hacettepe.rehabsoft.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "Data Transfer Object for Password Reset")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDto {

    String username;

    String oldPassword;

    String newPassword;
}
