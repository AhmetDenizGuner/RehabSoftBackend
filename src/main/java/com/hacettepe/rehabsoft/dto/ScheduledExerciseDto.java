package com.hacettepe.rehabsoft.dto;

import com.hacettepe.rehabsoft.entity.Exercise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for Scheduled Exercise")
public class ScheduledExerciseDto {

    @ApiModelProperty(required = true,value = "id")
    private Long id;

    @NotEmpty
    @ApiModelProperty(required = true,value = "scheduledDate")
    private LocalDateTime scheduledDate;

    @NotNull
    @ApiModelProperty(required = true,value = "isApplied")
    private Boolean isApplied;

    @NotNull
    @ApiModelProperty(required = true,value = "exercise")
    private ExerciseDto exercise;

}
