package com.hacettepe.rehabsoft.service;


import com.hacettepe.rehabsoft.dto.OnlineMeetingDto;

import java.util.List;

public interface OnlineMeetingService {
//    List<OnlineMeetingDto> getAllByUserId();

    boolean save(OnlineMeetingDto onlineMeetingDto) throws Exception;

    List<OnlineMeetingDto> getOnlineMeetingsByUsername(String username) throws Exception;

    Boolean isUsernameHasOnlineMeetingInCurrentDay(String username);

    void deleteById(Long id);

//    Boolean delete(Long id);
//
//    String updateOnlineMeetingDto(OnlineMeetingDto onlineMeetingDto) throws Exception;
}
