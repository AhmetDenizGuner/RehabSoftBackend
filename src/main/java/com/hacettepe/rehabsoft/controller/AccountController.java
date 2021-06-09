package com.hacettepe.rehabsoft.controller;
import com.hacettepe.rehabsoft.dto.LoginRequest;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.TokenResponse;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.helper.RegistrationHelper;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.security.JwtTokenUtil;
import com.hacettepe.rehabsoft.service.GeneralEvaluationFormService;
import com.hacettepe.rehabsoft.service.PatientService;
import com.hacettepe.rehabsoft.service.implementations.UserServiceImpl;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api/token") //Token pathine gelen bütün isteklere izin verilecek.Bunun ayarı SecurityConfig'de
@Api(value = "/api/token")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, allowCredentials = "true")
public class AccountController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    RegistrationHelper registrationHelper;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PatientService patientService;
    private final GeneralEvaluationFormService generalEvaluationFormService;

    public AccountController(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil,UserRepository userRepository,UserServiceImpl userService,PatientService patientService,GeneralEvaluationFormService generalEvaluationFormService){
        this.userService = userService;
        this.patientService = patientService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.generalEvaluationFormService = generalEvaluationFormService;

    }

    @RequestMapping(method = RequestMethod.POST) //LoginRequest'i DTO kısmında olusturduk.Front-end'den gelen login objesi gibi düsün
    @ApiOperation(value = "Login Operation", response = TokenResponse.class)
    @ResponseBody
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername().toLowerCase(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        // Add a session cookie
        Cookie sessionCookie = new Cookie( "someSessionId", token );
        // httpsler icin bu degisecek deploy sonrasi
        sessionCookie.setSecure(request.isSecure());
        response.addCookie( sessionCookie );


        User userFromDB = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());

        boolean isPatientSaved = patientService.isPatientAlreadySaved();
        boolean isGeneralInformationSaved = generalEvaluationFormService.isGeneralEvaluationFormExist();

        //Burada sorun olabilir:Bunu yaz:         return ResponseEntity.ok(new AuthToken(token));
        return ResponseEntity.ok(new TokenResponse(userFromDB.getUsername(),userFromDB.getFirstName(), userFromDB.getSurname(), userFromDB.getRole().getName(),token,isPatientSaved,isGeneralInformationSaved));
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="Register Operation",response = Boolean.class)
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {

        responseMessage.setResponseMessage(registrationHelper.registrationValidator(registrationRequest));

        if(responseMessage.getResponseMessage()!=null){
            responseMessage.setResponseType(0);
            return ResponseEntity.ok(responseMessage);
        }

        Boolean response = userService.register(registrationRequest);

        if(!response){
            responseMessage.setResponseMessage("Kayıt sırasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
                return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseType(1);
        responseMessage.setResponseMessage("Basariyla kaydoldunuz!");
        return ResponseEntity.ok(responseMessage);

    }
}
