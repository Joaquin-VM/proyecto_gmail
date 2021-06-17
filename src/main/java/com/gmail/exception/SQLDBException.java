package com.gmail.exception;

public class SQLDBException extends java.sql.SQLException {

  String mensaje;

  public SQLDBException(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

}
