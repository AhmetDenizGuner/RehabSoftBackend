package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.ResponseVideoRequestDto;
import com.hacettepe.rehabsoft.entity.RequestedVideo;
import com.hacettepe.rehabsoft.entity.ResponseVideoRequest;
import com.hacettepe.rehabsoft.helper.BeanValidationDeserializer;
import com.hacettepe.rehabsoft.helper.FileOperationHelper;
import com.hacettepe.rehabsoft.repository.RequestedVideoRepository;
import com.hacettepe.rehabsoft.repository.ResponseVideoRequestRepository;
import com.hacettepe.rehabsoft.repository.VideoRequestRepository;
import com.hacettepe.rehabsoft.service.ResponseVideoRequestService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseVideoRequestServiceImp implements ResponseVideoRequestService {

    private final ModelMapper modelMapper;
    private final VideoRequestRepository videoRequestRepository;
    private final ResponseVideoRequestRepository responseVideoRequestRepository;
    private final RequestedVideoRepository requestedVideoRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public String save(String responseVideoRequestJSON, MultipartFile[] responseMediaList, Long videoRequestId) throws Exception {
        try {
            System.out.println("responseVideoRequestJSON: "+responseVideoRequestJSON);
            // Mapping JSON to Object DTO and Entity after
            ResponseVideoRequestDto responseVideoRequestDto = (ResponseVideoRequestDto) BeanValidationDeserializer.deserializeJSONWithValidation(responseVideoRequestJSON, ResponseVideoRequestDto.class);

            ResponseVideoRequest tempResponseVideoRequest = modelMapper.map(responseVideoRequestDto, ResponseVideoRequest.class);
            BigInteger idOfSavingResponse = (BigInteger) entityManager.createNativeQuery("SELECT nextval('response_video_request_seq')").getSingleResult();
            idOfSavingResponse = idOfSavingResponse.add(BigInteger.ONE);
            fillRequestedVideoCollection( tempResponseVideoRequest, responseMediaList,idOfSavingResponse );

            tempResponseVideoRequest.setVideoRequest(videoRequestRepository.findById(videoRequestId).orElseThrow());
            tempResponseVideoRequest = responseVideoRequestRepository.save(tempResponseVideoRequest);
            responseVideoRequestDto.setId(tempResponseVideoRequest.getId());

            return "ResponseVideoRequest başarıyla kaydedildi!";
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public List<ResponseVideoRequestDto> getAll() {
        List<ResponseVideoRequest> responseVideoRequestList = responseVideoRequestRepository.findAll();
        return  Arrays.asList(modelMapper.map(responseVideoRequestList, ResponseVideoRequestDto[].class));
    }

    @Override
    public ResponseVideoRequestDto getById(Long id) {
        ResponseVideoRequest responseVideoRequest = responseVideoRequestRepository.getOne(id);
        return modelMapper.map(responseVideoRequest, ResponseVideoRequestDto.class);
    }

    private void fillRequestedVideoCollection(ResponseVideoRequest responseVideoRequest, MultipartFile[] responseMediaList, BigInteger idOfSavingResponse) throws Exception {
        try{
            if(responseVideoRequest.getRequestedVideoCollection()!=null){
                List<RequestedVideo> forIterationNewRequestedVideoCollection = new ArrayList<>(responseVideoRequest.getRequestedVideoCollection());
                List<RequestedVideo> newRequestedVideoCollection = new ArrayList<>();

                for(RequestedVideo requestedVideo:forIterationNewRequestedVideoCollection){

                    saveRequestedVideoAndSetRequestedVideoUrls(requestedVideo, responseMediaList, responseVideoRequest, newRequestedVideoCollection, idOfSavingResponse);
                }

            }
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }

    }

    private void saveRequestedVideoAndSetRequestedVideoUrls(RequestedVideo requestedVideo, MultipartFile[] responseMediaList, ResponseVideoRequest responseVideoRequest, List<RequestedVideo> newRequestedVideoCollection,  BigInteger idOfSavingResponse)
            throws Exception {
        if( responseMediaList == null){
            return ;
        }

        for(MultipartFile multipartFile:responseMediaList){
            try {
                StringBuilder newFileName = new StringBuilder();
                String fileType = FileOperationHelper.popFileTypeFromFileName(multipartFile.getOriginalFilename(), newFileName);

                // videonun ismi, url'e girilecek front endde.
                if(newFileName.toString().equals(requestedVideo.getVideoUrl())){

                    requestedVideo.setVideoUrl(null);
                    responseVideoRequest.getRequestedVideoCollection().remove(requestedVideo);
                    RequestedVideo persistedRequestedVideo = requestedVideoRepository.save(requestedVideo);
                    String directoryAndMediaURL = FileOperationHelper.createURLWithDirectory(
                            ApiPaths.SavingResponseVideoRequestPath.CTRL+"",
                            idOfSavingResponse.toString() +"",
                            persistedRequestedVideo.getId()+"-" + newFileName.toString(),
                            fileType+""
                    );

                    String savedUrl = FileOperationHelper.saveFileByDirectory(multipartFile, directoryAndMediaURL);

                    persistedRequestedVideo.setVideoUrl(savedUrl);
                    persistedRequestedVideo.setResponseVideoRequest(responseVideoRequest);
                    newRequestedVideoCollection.add(persistedRequestedVideo);
                }
            }  catch(Exception e){
                e.printStackTrace();
                throw new Exception();
            }
        }
    }
}
