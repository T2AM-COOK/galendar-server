package com.galendar.global.s3;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String putFile(MultipartFile file);
    void deleteFile(String url);

}
