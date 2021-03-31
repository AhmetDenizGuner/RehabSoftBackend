package com.hacettepe.rehabsoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Valid
public class OnlineMeetingDto {

    private Long id;

    @NotNull
    private UserDto patientUser;

    @NotNull
    private UserDto doctorUser;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meetingDate;
}
