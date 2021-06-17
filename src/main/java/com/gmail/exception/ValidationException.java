package com.gmail.exception;

public class ValidationException extends Exception {

  String mensaje;

  public ValidationException(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

}
