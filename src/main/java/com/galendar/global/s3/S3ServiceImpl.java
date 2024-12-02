package com.galendar.global.s3;

import com.galendar.global.s3.exception.S3Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public String putFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new S3Exception("파일을 확인해주세요.");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = validateImageFileExtention(originalFilename);
        String fileName = UUID.randomUUID() + "." + fileExtension;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        PutObjectResponse putObjectResponse = null;

        try {
            putObjectResponse = s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            throw new S3Exception("파일 업로드 실패");
        }

        if (!isSuccess(putObjectResponse)) {
            throw new S3Exception("파일 업로드 실패");
        }

        GetUrlRequest request = GetUrlRequest.builder().bucket(bucketName).key(fileName).build();
        return s3Client.utilities().getUrl(request).toString();
    }

    public void deleteFile(String url) {
        String objectKey = s3Client.utilities().parseUri(URI.create(url)).key().orElseThrow(() -> new S3Exception("파일을 찾을 수 없습니다."));
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(deleteObjectRequest);
        if (!isSuccess(deleteObjectResponse)) {
            throw new S3Exception("파일 삭제 실패");
        }
    }

    private String validateImageFileExtention(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new S3Exception("파일 확장자가 올바른지 확인해주세요.");
        }

        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extention)) {
            throw new S3Exception("지원하지 않는 이미지 확장자입니다.");
        }

        return extention;
    }
    
    private boolean isSuccess(S3Response response) {
        return isSuccessfulStatusCode(response.sdkHttpResponse().statusCode());
    }

    private boolean isSuccessfulStatusCode(int statusCode) {
        return statusCode == HttpStatus.OK.value() || statusCode == HttpStatus.NO_CONTENT.value();
    }

}
