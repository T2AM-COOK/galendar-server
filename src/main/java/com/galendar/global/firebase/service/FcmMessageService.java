package com.galendar.global.firebase.service;

import com.galendar.global.firebase.config.FcmMessageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FcmMessageService {

    private final FcmMessageProperties fcmMessageProperties;

    public String getTitle(String type, Map<String, String> variables) {
        String template = fcmMessageProperties.getMessages().get(type).getTitle();
        return replaceVariables(template, variables);
    }

    public String getBody(String type, Map<String, String> variables) {
        String template = fcmMessageProperties.getMessages().get(type).getBody();
        return replaceVariables(template, variables);
    }

    private String replaceVariables(String template, Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            template = template.replace(placeholder, entry.getValue());
        }
        return template;
    }

}
