package com.gmail.exception;

public class CorreoException extends Exception {

  private String message;
  private int error;
  private int id;

  public CorreoException(int error, int id) {
    super();
    this.error = error;
    this.id = id;
  }

  public CorreoException(int error) {
    super();
    this.error = error;
    this.id = id;
  }

  public CorreoException(String msg) {
    super(msg);
  }

  @Override
  public String getMessage() {

    if (message != null && !message.isBlank()) {
      return message;
    }

    switch (error) {
      case 1:
        message = "Correo_Error 01: No existe correo con id = " + id + ".";
        break;
      case 2:
        message = "Correo_Error 02: El correo esta en papelera.";
        break;
      case 3:
        message = "Correo_Error 03: El correo esta enviado.";
        break;
      case 4:
        message = "Correo_Error 04: No pudo modificarse.";
      case 5:
        message = "Correo_Error 05: No pudo eliminarse.";
      case 6:
        message = "Correo_Error 06: No pudo enviar el correo al usuario id = " + id + ".";
      case 7:
        message = "Correo_Error 07: No pudo enviar el correo a " + id + " usuarios.";
      default:
        message = "Correo_Error 00: No identificado.";
        break;
    }

    return message;

  }

  public void setMessage(String message){
    this.message = message;
  }

}
