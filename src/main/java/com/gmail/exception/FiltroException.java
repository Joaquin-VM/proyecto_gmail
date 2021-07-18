package com.gmail.exception;

public class FiltroException extends Exception {

  private String message;
  private int error;
  private int id;

  public FiltroException(String message) {
    this.message = message;
  }

  public FiltroException(int error, int id) {
    super();
    this.error = error;
    this.id = id;
  }

  public FiltroException(int error) {
    super();
    this.error = error;
  }

  @Override
  public String getMessage() {

    if (message != null && !message.isBlank()) {
      return message;
    }

    switch (error) {
      case 1:
        message = "Filtro_Error 01: No existe Filtro con id = " + id + ".";
        break;
      case 4:
        message = "Filtro_Error 04: No pudo modificarse.";
      case 5:
        message = "Filtro_Error 05: No pudo eliminarse.";
      default:
        message = "Filtro_Error 00: No identificado.";
        break;
    }

    return message;

  }

  public void setMessage(String message){
    this.message = message;
  }

}
