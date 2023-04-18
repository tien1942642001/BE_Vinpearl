package dev.kienntt.demo.BE_Vinpearl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(commentWebSocketHandler(), "/comment").setAllowedOrigins("*");
//    }

//    @Bean
//    public WebSocketHandler commentWebSocketHandler() {
//        return new CommentWebSocketHandler();
//    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatbotHandler(), "/chatbot").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler chatbotHandler() {
        return new ChatbotHandler();
    }
}
