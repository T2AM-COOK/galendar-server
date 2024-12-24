package com.galendar.global.firebase.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "firebase")
public class FcmMessageProperties {
    private Map<String, MessageTemplate> messages = new HashMap<>();

    public Map<String, MessageTemplate> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, MessageTemplate> templates) {
        this.messages = templates;
    }

    @Getter
    @Setter
    public static class MessageTemplate {
        private String title;
        private String body;
    }

}
