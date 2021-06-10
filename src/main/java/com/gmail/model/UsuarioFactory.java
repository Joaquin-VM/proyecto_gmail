package com.gmail.model;

public class UsuarioFactory {

  public static AbsUsuario buildUsuario(){
    return new Usuario();
  }

}
