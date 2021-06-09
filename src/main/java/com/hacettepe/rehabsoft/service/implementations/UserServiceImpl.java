package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.PasswordChangeDto;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.*;
import com.hacettepe.rehabsoft.repository.RoleRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.NotificationService;
import com.hacettepe.rehabsoft.service.UserService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import com.hacettepe.rehabsoft.util.NotificationPaths;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationService notificationService;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username.toLowerCase());
        if(user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }


    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Role role = user.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return authorities;
    }



    @Override
    public UserDto getByUsername(String username) {
        User tempUser = userRepository.findByUsername(username);
        return modelMapper.map(tempUser, UserDto.class);
    }



    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }


    @Override
    public String updateUser(UserDto tempUser){
        log.warn("user update is on");
        User dbUser = userRepository.getOne(tempUser.getId());

        if(!dbUser.getEmail().equals(tempUser.getEmail()) ){ //email değişmişse, yeni e-mailin zaten başka biri tarafından kullanıldığını check ediyor
            if(this.isEmailExists(tempUser.getEmail())){
                return "E-mail zaten kullanılıyor.Lütfen başka bir e-mail adresi seçin";
            }
        }

        dbUser.setSurname(tempUser.getSurname());
        dbUser.setFirstName(tempUser.getFirstName());
        dbUser.setEmail(tempUser.getEmail().toLowerCase());
        userRepository.save(dbUser);
        return "Degisiklikler başarıyla kaydedildi!";

    }

    @Override
    public Boolean isUsernameExists(String username) {
        if(userRepository.findByUsername(username.toLowerCase())==null){
            return false;
        }
        return true;
    }


    @Override
    public Boolean isEmailExists(String email) {
        if(userRepository.findByEmail(email.toLowerCase())==null){
            return false;
        }
        return true;
    }


    @Override
    public Boolean deleteUser(Long id) {
       if(userRepository.existsById(id)){
           userRepository.deleteById(id);
           return true;
       }
       else{
           return false;
       }

    }




    @Override
    @Transactional
    public UserDto save(UserDto user) {
        User tempUser = modelMapper.map(user, User.class);
        tempUser = userRepository.save(tempUser);
        user.setId(tempUser.getId());
        System.out.println();
        return user;
    }



    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            log.warn("Register'a giriyor");
            User user = new User();
            user.setEmail(registrationRequest.getEmail().toLowerCase());
            user.setFirstName(registrationRequest.getFirstName());
            user.setSurname(registrationRequest.getSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername().toLowerCase());
            final Role role = roleRepository.findByName("USER");
            //System.out.println(role.getName());
            user.setRole(role);
            User userFromDatabase = userRepository.save(user);
            notificationService.createNotification(userFromDatabase,
                    "Lütfen kaydı tamamlamak için değerlendirme formunu doldurunuz.",
                    NotificationPaths.BASE_PATH+"/user/general-evaluation-form",
                    false);
            return Boolean.TRUE;

        } catch (Exception e) {
            log.error("REGISTRATION Failed=>", e);
            return Boolean.FALSE;
        }
    }





    @Override
    public String updateResetPasswordToken(String token, String email) throws UsernameNotFoundException, UnsupportedEncodingException, MessagingException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
            String resetPasswordLink = ApiPaths.LOCAL_CLIENT_BASE_PATH + "/reset_password/" + token;
            sendEmail(email, resetPasswordLink);
            return "Şifre yenileme linki " + email +" adresine gönderilmiştir"; }
        else {
            return "Sistemde " + email +" ile kayıtlı bir kullanici bulunmamaktadır";
        }
    }

    @Override
    public String changePassword(PasswordChangeDto passdto) throws UsernameNotFoundException{
        log.warn("change password aktif " + passdto.getUsername());
        User currentUser = userRepository.findByUsername(passdto.getUsername());


        String currentPassword = currentUser.getPassword();

        if(!bCryptPasswordEncoder.matches(passdto.getOldPassword(), currentPassword)){
            return "Mevcut şifrenizi yanlış girdiniz lütfen tekrar deneyin";
        }
        else{
            String newEncodedPassword = bCryptPasswordEncoder.encode(passdto.getNewPassword());
            currentUser.setPassword(newEncodedPassword);
            userRepository.save(currentUser);
            return "Sifre değiştirme işleminiz başarıyla gerçekleştirilmiştir.";
        }

    }


    @Override
    public Boolean resetTokenChecker(String token) {
        Boolean isExists = userRepository.existsByResetPasswordToken(token);

        if (isExists){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }

    }



    @Override
    public Boolean updatePassword(String token, String newPassword) {
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        User user = userRepository.findByResetPasswordToken(token);

        if(user !=null){
            user.setPassword(encodedPassword);
            user.setResetPasswordToken(null);
            userRepository.save(user);
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }


    }



    @Override
    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("rehabsoft.cs@gmail.com", "RehabSoft Support");
        helper.setTo(recipientEmail);

        String subject = "Sifre sifirlama linkiniz";

        String content = "<p>Merhaba,</p>"
                + "<p>Kısa bir süre önce şifre değiştirme talebinde bulundunuz</p>"
                + "<p>Sifrenizi yenilemek icin lütfen baglantiya tıklayın:</p>"
                + "<p><a href=\"" + link + "\">Sifremi Degistir</a></p>"
                + "<br>"
                + "<p>Sifre talebinde bulunmadıysanız lütfen bu maili göz ardı edin, ";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


}
