package com.gmail.exception;

public class ValidationError extends Exception {

  String mensaje;

  public ValidationError(String mensaje){
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

}
