package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.OnlineMeetingDto;
import com.hacettepe.rehabsoft.entity.OnlineMeeting;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.repository.OnlineMeetingRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.OnlineMeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnlineMeetingServiceImp implements OnlineMeetingService {
    private final ModelMapper modelMapper;
    private final OnlineMeetingRepository onlineMeetingRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(OnlineMeetingDto onlineMeetingDto) throws Exception {

        OnlineMeeting onlineMeeting = modelMapper.map(onlineMeetingDto, OnlineMeeting.class);
        User doctorUser = userRepository.findByUsername(onlineMeeting.getDoctorUser().getUsername());
        User patientUser = userRepository.findByUsername(onlineMeeting.getPatientUser().getUsername());
        onlineMeeting.setDoctorUser(doctorUser);
        onlineMeeting.setPatientUser(patientUser);
        onlineMeetingRepository.save(onlineMeeting);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<OnlineMeetingDto> getOnlineMeetingsByUsername(String username) throws Exception {
        List<OnlineMeeting> onlineMeetingList = onlineMeetingRepository.getByUsername(username)
                .stream()
                .filter(onlineMeeting -> onlineMeeting.getMeetingDate().isAfter(LocalDateTime.now().minusDays(1))).collect(Collectors.toList());
        return onlineMeetingList.stream().map(onlineMeeting->modelMapper.map(onlineMeeting, OnlineMeetingDto.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean isUsernameHasOnlineMeetingInCurrentDay(String username) {
        final List<OnlineMeeting> onlineMeetings = onlineMeetingRepository.getByUsername(username);
        return onlineMeetings.isEmpty() && isMeetingTimeInCurrentDay(onlineMeetings);
    }

    @Override
    public void deleteById(Long id) {
        onlineMeetingRepository.deleteById(id);
    }

    private boolean isMeetingTimeInCurrentDay(List<OnlineMeeting> onlineMeetings) {
        final LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        final LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return onlineMeetings.stream()
                .anyMatch(onlineMeeting ->onlineMeeting.getMeetingDate().isBefore(tomorrow) && onlineMeeting.getMeetingDate().isAfter(yesterday));
    }
}
