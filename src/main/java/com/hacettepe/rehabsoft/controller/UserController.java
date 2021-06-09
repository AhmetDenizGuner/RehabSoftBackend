package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.UserService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



//@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.UserPath.CTRL)
@RestController
@Api(value = "/api/users")
public class UserController {

    private final UserService userService;
    private final ResponseMessage responseMessage;

    public UserController(UserService userService,ResponseMessage responseMessage){
        this.userService = userService;
        this.responseMessage = responseMessage;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getOne(@PathVariable String username){
        log.warn("GetOne(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userService.getByUsername(username));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listUsers(){
        log.warn("listUsers(Admin icin) metodu basariyla calisti");
        List<UserDto> data = userService.getAll();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateUser(@Valid @RequestBody UserDto userDto){
        log.warn("update controllerına girdi");
        responseMessage.setResponseMessage(userService.updateUser(userDto));
        return ResponseEntity.ok(responseMessage);
    }









}
