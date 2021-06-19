package com.gmail.model.impl;

import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.Usuario;
import com.gmail.dto.UsuarioDTO;

public class UsuarioFactory {

  public static AbsUsuario buildUsuario() {
    return new Usuario();
  }

  public static AbsUsuario buildUsuario(UsuarioDTO dto) {
    return new Usuario(dto);
  }

}
