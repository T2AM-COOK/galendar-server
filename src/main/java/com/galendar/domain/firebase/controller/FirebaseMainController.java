package com.galendar.domain.firebase.controller;

import com.galendar.domain.firebase.dto.request.RequestDto;
import com.galendar.domain.firebase.service.FirebaseCloudMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "알림", description = "FCM알림 관련 api입니다.")
@RestController
@RequiredArgsConstructor
public class FirebaseMainController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Operation(summary = "알림 보내기", description = "토큰을 사용하여 특정 회원에게 알림을 보냅니다.")
    @PostMapping("/fcm")
    public ResponseEntity pushMessage(@RequestBody RequestDto requestDTO) throws IOException {
        System.out.println(requestDTO.getTargetToken() + " "
                +requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "토큰 저장", description = "토큰을 저장합니다.")
    @PostMapping("/token")
    public void saveToken(@RequestBody String token) {
        firebaseCloudMessageService.saveToken(token);
    }

    @Operation(summary = "토큰 삭제", description = "토큰을 삭제합니다.")
    @DeleteMapping("/token")
    public void deleteToken(@RequestBody String token) {
        firebaseCloudMessageService.deleteToken(token);
    }
}
