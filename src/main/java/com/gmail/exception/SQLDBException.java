package com.gmail.exception;

import java.sql.SQLException;

public class SQLDBException extends SQLException {

  private String message;

  public SQLDBException(String message) {
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
