package com.hacettepe.rehabsoft.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hacettepe.rehabsoft.entity.VideoRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
@Data
public class ResponseVideoRequestDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;

    private String responseContent;

    private Collection<RequestedVideoDto> requestedVideoCollection;
}
