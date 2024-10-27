package com.k.garlander.global.exception;

public class RegisterFormException extends FormException {
  public RegisterFormException(String message) {
    super(message);
  }

  public RegisterFormException(String message, Throwable cause) {
    super(message, cause);
  }

  public RegisterFormException(Throwable cause) {
    super(cause);
  }

  public RegisterFormException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public RegisterFormException() {
    super();
  }
}
