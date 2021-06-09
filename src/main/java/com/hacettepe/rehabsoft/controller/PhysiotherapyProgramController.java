package com.hacettepe.rehabsoft.controller;


import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.PhysiotherapyProgramDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.PhysiotherapyProgramService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.PhysiotherapyProgramPath.CTRL)
@RestController
@Api(value = "/api/physiotherapy-program")
public class PhysiotherapyProgramController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    PhysiotherapyProgramService physiotherapyProgramService;

    @RequestMapping(value = "/get-all-assigned/{tcKimlikNo}", method = RequestMethod.GET)
    public ResponseEntity<List<PhysiotherapyProgramDto>> getAllAssignedForm(@PathVariable String tcKimlikNo) {

        List<PhysiotherapyProgramDto> physiotherapyProgramDtoList = physiotherapyProgramService.getAllAssignedExercisePrograms(tcKimlikNo);

        return ResponseEntity.ok(physiotherapyProgramDtoList);
    }

    @RequestMapping(value = "/assign-new", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> assignDynamicForm(@RequestBody PhysiotherapyProgramDto physiotherapyProgramDto) throws FirebaseMessagingException {
        log.warn("Hastaya programatama controller'ı çalışıyor");

        boolean sonuc = physiotherapyProgramService.assignProgram(physiotherapyProgramDto);

        if (sonuc) {
            responseMessage.setResponseMessage("islem basarili");
            return ResponseEntity.ok(responseMessage);
        } else {
            responseMessage.setResponseMessage("Program atama sırasında bir hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.warn("delete controller'ı çalışıyor");
        boolean sonuc = physiotherapyProgramService.delete(id);
        return ResponseEntity.ok(sonuc);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> updateDynamicForm(@RequestBody PhysiotherapyProgramDto physiotherapyProgramDto) {
        log.warn("updateDynamicForm controller'ı çalışıyor");
        boolean sonuc = physiotherapyProgramService.updateProgram(physiotherapyProgramDto);
        if (sonuc) {
            responseMessage.setResponseMessage("islem basarili");
        } else {
            responseMessage.setResponseMessage("Program guncelleme sırasında bir hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<PhysiotherapyProgramDto> getById(@PathVariable Long id) {

        PhysiotherapyProgramDto physiotherapyProgramDto = physiotherapyProgramService.getById(id);

        return ResponseEntity.ok(physiotherapyProgramDto);
    }
}
