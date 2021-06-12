package com.gmail.exception;

public class CorreoError extends Exception {

  private int error;
  private int id;

  public CorreoError(int error, int id) {
    super();
    this.error = error;
    this.id = id;
  }

  public CorreoError(int error) {
    super();
    this.error = error;
    this.id = id;
  }

  public CorreoError(String msg) {
    super(msg);
  }

  @Override
  public String getMessage() {

    String mensaje;

    switch (error) {
      case 1:
        mensaje = "Correo_Error 01: No existe correo con id = " + id;
        break;
      case 2:
        mensaje = "Correo_Error 02: El correo esta en papelera";
        break;
      case 3:
        mensaje = "Correo_Error 03: El correo esta enviado";
        break;
      case 4:
        mensaje = "Correo_Error 04: No pudo modificarse";
      case 5:
        mensaje = "Correo_Error 05: No pudo eliminarse";
      case 6:
        mensaje = "Correo_Error 06: No pudo enviar el correo al usuario id = " + id;
      case 7:
        mensaje = "Correo_Error 07: No pudo enviar el correo a " + id + " usuarios";
      default:
        mensaje = "Correo_Error 00: No identificado";
        break;
    }

    return mensaje;

  }

}
