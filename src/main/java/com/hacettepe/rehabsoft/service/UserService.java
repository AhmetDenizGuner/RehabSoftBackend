package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.PasswordChangeDto;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getByUsername(String username);

    List<UserDto> getAll();

    Boolean isUsernameExists(String username);

    Boolean isEmailExists(String email);

    Boolean deleteUser(Long id);

    Boolean register(RegistrationRequest registrationRequest);

    String updateUser(UserDto user);

    String updateResetPasswordToken(String token, String email) throws UnsupportedEncodingException, MessagingException;

    String changePassword(PasswordChangeDto passdto);

    Boolean resetTokenChecker(String token);

    Boolean updatePassword(String token, String newPassword);

    void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException;

}
