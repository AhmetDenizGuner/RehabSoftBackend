package com.hacettepe.rehabsoft.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "Data Transfer Object for Password Update")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {

    private String token;

    private String password;
}
