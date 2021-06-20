package com.gmail.exception;

public class ValidationException extends Exception {

  private String message;

  public ValidationException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
