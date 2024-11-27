package com.galendar.global.firebase.service;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseMessaging firebaseMessaging;

    public void sendMessage(String token, String title, String body) {
        Message message = createMessage(token, title, body);
        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
        }
    }

    public Message createMessage(String token, String title, String body) {
        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return Message
                .builder()
                .setNotification(notification)
                .setToken(token)
                .setApnsConfig(
                    ApnsConfig.builder().setAps(Aps.builder().setSound("default").build()).build()
                )
                .build();
    }

}
