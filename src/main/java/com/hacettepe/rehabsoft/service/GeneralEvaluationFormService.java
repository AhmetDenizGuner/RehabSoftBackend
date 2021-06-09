package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.GeneralEvaluationFormDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GeneralEvaluationFormService {
    Boolean save(String GEFD, MultipartFile botoxImage, MultipartFile[] epicrisisImages, MultipartFile[] otherOrthesisImages) throws Exception;

    boolean isGeneralEvaluationFormExist();

    GeneralEvaluationFormDto getGefd(String tcKimlikNo);

    byte[] getBotoxImageById(Long id) throws IOException;

    byte[] getEpicrisisImageById(Long id) throws IOException;

    byte[] getOrthesisImageById(Long id) throws IOException;
}
