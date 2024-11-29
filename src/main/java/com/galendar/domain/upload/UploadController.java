package com.galendar.domain.upload;

import com.galendar.global.common.dto.response.ResponseData;
import com.galendar.global.s3.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File", description = "")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class UploadController {

    private final S3Service s3Service;

    @Operation(summary = "이미지 업로드", description = "")
    @PostMapping
    public ResponseEntity<ResponseData> upload(@RequestParam("file") MultipartFile file) {
        String url = s3Service.putFile(file);
        return ResponseEntity.ok(ResponseData.ok(url, "업로드 성공"));
    }

    @Operation(summary = "이미지 삭제", description = "")
    @DeleteMapping
    public ResponseEntity<ResponseData> upload(@RequestParam("url") String url) {
        s3Service.deleteFile(url);
        return ResponseEntity.ok(ResponseData.ok("삭제 성공"));
    }

}
