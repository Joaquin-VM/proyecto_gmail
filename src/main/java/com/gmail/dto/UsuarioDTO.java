package com.gmail.dto;

import com.gmail.model.AbsUsuario;
import com.gmail.model.UsuarioFactory;

public class UsuarioDTO {

  public static AbsUsuario generarDTO(){
    return UsuarioFactory.buildUsuario();
  }

}
