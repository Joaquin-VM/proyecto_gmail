package com.gmail.model.impl;

import com.gmail.model.AbsCorreo;
import com.gmail.model.impl.Correo;
import com.gmail.dto.CorreoDTO;

public class CorreoFactory {

  public static AbsCorreo buildCorreo() {
    return new Correo();
  }

  public static AbsCorreo buildCorreo(CorreoDTO correo) {
    return new Correo(correo);
  }

}
