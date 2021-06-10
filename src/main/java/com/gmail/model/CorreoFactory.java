package com.gmail.model;

public class CorreoFactory {

  public static AbsCorreo buildCorreo(){
    return new Correo();
  }

}
