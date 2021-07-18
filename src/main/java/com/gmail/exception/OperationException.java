package com.gmail.exception;

public class OperationException extends Exception {

  private String message;

  public OperationException(String message) {
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
