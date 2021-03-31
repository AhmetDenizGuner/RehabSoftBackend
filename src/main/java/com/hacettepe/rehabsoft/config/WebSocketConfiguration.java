package com.hacettepe.rehabsoft.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacettepe.rehabsoft.service.OnlineMeetingService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final OnlineMeetingService onlineMeetingService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        SocketHandlerConfig webSocketHandlerConfig = new SocketHandlerConfig(onlineMeetingService);
        registry.addHandler(webSocketHandlerConfig, ApiPaths.OnlineMeetingWebSocket.CTRL)

                // this code can be used to eliminate other users connection who has nto any meeting url in database.
//                .addInterceptors(new HttpSessionHandshakeInterceptor()
//        {
//            @Override
//            public void afterHandshake(ServerHttpRequest request,
//                                       ServerHttpResponse response, WebSocketHandler wsHandler,
//                                       @Nullable Exception ex) {
//
//                super.afterHandshake(request, response, wsHandler, ex);
//
//            }
//
//            @Override
//            public boolean beforeHandshake(ServerHttpRequest request,
//                                           ServerHttpResponse response, WebSocketHandler wsHandler,
//                                           Map<String, Object> attributes) throws Exception {
//                boolean b = super.beforeHandshake(request, response,
//                        wsHandler, attributes) &&
//                        ((UsernamePasswordAuthenticationToken)
//                                request.getPrincipal()).isAuthenticated();
//                return b;
//            }
//        })
        .setAllowedOrigins(ApiPaths.LOCAL_CLIENT_BASE_PATH);
    }

}
