package com.gmail.model;

import com.gmail.dto.CorreoDTO;

public class CorreoFactory {

  public static AbsCorreo buildCorreo() {
    return new Correo();
  }

  public static AbsCorreo buildCorreo(CorreoDTO correo) {
    return new Correo(correo);
  }

}
