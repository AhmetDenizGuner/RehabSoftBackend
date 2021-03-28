package com.hacettepe.rehabsoft.config;

import com.hacettepe.rehabsoft.dto.OnlineMeetingDto;
import com.hacettepe.rehabsoft.service.OnlineMeetingService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
@Api(value = ApiPaths.OnlineMeetingWebSocket.CTRL)
public class SocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private final OnlineMeetingService onlineMeetingService;
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public SocketHandler(OnlineMeetingService onlineMeetingService) {
        this.onlineMeetingService = onlineMeetingService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();

        for (WebSocketSession connectedSession : sessions) {
            System.out.println(connectedSession.getPrincipal());
            if (connectedSession.isOpen() && isThisUserNotCurrentUserAndHasMeetingWithCurrentConnectedUser(Objects.requireNonNull(session.getPrincipal()).getName(), Objects.requireNonNull(connectedSession.getPrincipal()).getName())) {
                connectedSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("connections: "+session.toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("Server transport error: " + exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("access-token");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.warn("Session is removed: "+session);
        sessions.remove(session);
    }

    private boolean isThisUserNotCurrentUserAndHasMeetingWithCurrentConnectedUser(String currentConnectedUser, String otherUser) throws Exception {
        return !currentConnectedUser.equals(otherUser)
                && onlineMeetingService.getOnlineMeetingsByUsername(currentConnectedUser)
                .stream()
                .anyMatch(onlineMeetingDto -> (onlineMeetingDto.getDoctorUser().getUsername().equals(otherUser) || onlineMeetingDto.getPatientUser().getUsername().equals(otherUser)) && isMeetingToday(onlineMeetingDto));
    }

    private boolean isMeetingToday(OnlineMeetingDto onlineMeetingDto) {
        return LocalDateTime.now().getDayOfMonth() == onlineMeetingDto.getMeetingDate().getDayOfMonth()
                && LocalDateTime.now().getMonth().equals(onlineMeetingDto.getMeetingDate().getMonth())
                && LocalDateTime.now().getYear() == onlineMeetingDto.getMeetingDate().getYear();
    }
}
