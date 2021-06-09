package com.hacettepe.rehabsoft.service.implementations;


import com.hacettepe.rehabsoft.dto.MessageDto;
import com.hacettepe.rehabsoft.dto.MessageHistoryDto;
import com.hacettepe.rehabsoft.entity.Message;
import com.hacettepe.rehabsoft.entity.OtherOrthesisInfo;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.entity.VideoRequest;
import com.hacettepe.rehabsoft.helper.SecurityHelper;
import com.hacettepe.rehabsoft.repository.MessageRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.MessageService;
import com.hacettepe.rehabsoft.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;
    private final SecurityHelper securityHelper;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Override
    public boolean save(MessageDto messageDto) {
        try {
        log.warn("Message Save metoduna girdi");
        Message message = modelMapper.map(messageDto, Message.class);
        message.setSenderUser(userRepository.findByUsername(securityHelper.getUsername()));
        message.setReceiverUser(userRepository.findByEmail(messageDto.getReceiverEmail()));
        //simdilik obje olarak göndereceğiz

        messageRepository.save(message);

        notificationService.createNotificationForMessage(message);
        return true;
        }
        catch (Exception e){
            log.error("Message save Failed=>", e);
            return false;
        }
    }



    @Override
    public List<MessageHistoryDto> receiveMessageHistory(String email) {


        try {

            Long  receiverId = userRepository.findByUsername(securityHelper.getUsername()).getId();
            String receiverEmail=  userRepository.findByUsername(securityHelper.getUsername()).getEmail();
            Long  senderId = userRepository.findByEmail(email).getId();

            List<Message> messages = messageRepository.getHistory(receiverId,senderId);
            //bir liste kur, her messages elemanını messageHistoryDto'ya çevir

            List<MessageHistoryDto> messageHistory = new ArrayList<>();

            for (Message message : messages) {
                MessageHistoryDto tempMessageHistoryDto = new MessageHistoryDto();
                tempMessageHistoryDto.setCreationDate(message.getCreationDate());

                tempMessageHistoryDto.setMessageContent(message.getMessageContent());
                tempMessageHistoryDto.setMessageTitle(message.getMessageTitle());

                tempMessageHistoryDto.setReceiverName(message.getReceiverUser().getFirstName());
                tempMessageHistoryDto.setReceiverSurname(message.getReceiverUser().getSurname());
                tempMessageHistoryDto.setReceiverEmail(receiverEmail);


                tempMessageHistoryDto.setSenderName(message.getSenderUser().getFirstName());
                tempMessageHistoryDto.setSenderSurname(message.getSenderUser().getSurname());
                tempMessageHistoryDto.setSenderEmail(message.getSenderUser().getEmail());

                messageHistory.add(tempMessageHistoryDto);
            }

            return messageHistory;

        }

        catch (Exception e) {
            log.error("Message History loading Failed=>", e);
            return null;

        }
    }


}