package com.hacettepe.rehabsoft.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Valid
public class NotificationDto {

    @NotNull
    private Long id;

    @NotNull
    private Long version;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    @NotEmpty
    private String notificationContent;

    @NotNull
    @NotEmpty
    private String notificationUrl;

    @NotNull
    @NotEmpty
    private String notificationTitle;

    private int status;

}
