package com.gmail.model;

import com.gmail.dto.UsuarioDTO;

public class UsuarioFactory {

  public static AbsUsuario buildUsuario() {
    return new Usuario();
  }

  public static AbsUsuario buildUsuario(UsuarioDTO dto) {
    return new Usuario(dto);
  }

}
