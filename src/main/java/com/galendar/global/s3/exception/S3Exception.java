package com.galendar.global.s3.exception;

import com.galendar.global.exception.CustomException;

public class S3Exception extends CustomException {
    public S3Exception(int status, String message) {
        super(status, message);
    }

    public S3Exception(String message) {
        super(400, message);
    }
}
