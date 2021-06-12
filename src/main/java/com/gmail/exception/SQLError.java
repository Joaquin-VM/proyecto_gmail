package com.gmail.exception;

import java.sql.SQLException;

public class SQLError extends SQLException {

  String mensaje;

  public SQLError(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

}
