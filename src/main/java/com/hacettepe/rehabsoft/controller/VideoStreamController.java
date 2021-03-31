package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.service.implementations.VideoStreamService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import com.hacettepe.rehabsoft.util.VideoStreamConstants.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, allowCredentials = "true")
@RestController
@RequestMapping("/video")
public class VideoStreamController {

    private final VideoStreamService videoStreamService;

    public VideoStreamController(VideoStreamService videoStreamService) {
        this.videoStreamService = videoStreamService;
    }

    @GetMapping("/stream/{fileType}/{id}")
    public Mono<ResponseEntity<byte[]>> streamVideo(@RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("fileType") String fileType,
                                                    @PathVariable("id") Long id) {

        log.warn("Video stream controller'a girdi");
        return Mono.just(videoStreamService.prepareContent(id, fileType, httpRangeList));
    }


    @GetMapping("/stream/requested-video/{fileType}/{id}")
    public Mono<ResponseEntity<byte[]>> streamRequestedVideo(@RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("fileType") String fileType,
                                                    @PathVariable("id") Long id) {

        log.warn("Video stream controller'a girdi");
        return Mono.just(videoStreamService.prepareRequestedVideoContent(id, fileType, httpRangeList));
    }
}

