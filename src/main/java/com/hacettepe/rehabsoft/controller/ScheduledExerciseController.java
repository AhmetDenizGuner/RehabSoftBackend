package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.ScheduledExerciseDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.ScheduledExerciseService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.ScheduledExercisePath.CTRL)
@RestController
@Api(value = "/api/scheduled-exercise")
@RequiredArgsConstructor
public class ScheduledExerciseController {
    private final ResponseMessage responseMessage;
    private final ScheduledExerciseService scheduledExerciseServiceImp;

    @RequestMapping(value = "/update-applied", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> updateScheduledExerciseListAsApplied(@RequestBody List<ScheduledExerciseDto> scheduledExerciseList) {
        log.warn("updateScheduledExerciseList controller'ı çalışıyor");
        boolean sonuc = scheduledExerciseServiceImp.updateScheduledExerciseListAsApplied(scheduledExerciseList);
        if (sonuc) {
            responseMessage.setResponseMessage("islem basarili");
        } else {
            responseMessage.setResponseMessage("Guncelleme sırasında bir hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }
}
