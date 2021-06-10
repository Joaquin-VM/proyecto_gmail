package com.gmail;

import com.gmail.dao.UsuarioDAO;

import com.gmail.model.AbsUsuario;
import com.gmail.model.UsuarioFactory;
import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {
//    LocalDateTime ld = LocalDateTime.now();
//    System.out.println(ld.getHour() + " " + ld.getMinute() + " " + ld.getSecond());
//    LocalDate localDate = LocalDate.of(2021, 8, 3);
//    LocalDate localDate2 = LocalDate
//        .of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
//    System.out.println(localDate2);

    AbsUsuario usuario = UsuarioFactory.buildUsuario();
    usuario.setNombre("Sebastian").setApellido("Martinez").setCorreo("1234567@gmail.com")
        .setContrasenia("1234").setTelefono("54 9 323124213").setFechaNacimiento(LocalDate.now())
        .setSexo("Masculino");

    AbsUsuario usuario2 = UsuarioFactory.buildUsuario();
    usuario2.setIdUsuario(1).setNombre("Update").setApellido("Martinez").setCorreo("correo22@gmail.com")
        .setContrasenia("12345").setTelefono("54 9 3231242131").setFechaNacimiento(LocalDate.now())
        .setSexo("Masculino");

    AbsUsuario usuario3 = UsuarioFactory.buildUsuario();
    usuario3.setIdUsuario(1).setNombre("Pablo").setApellido("Martinez").setCorreo("errorfactory")
        .setContrasenia("12345").setTelefono("54 9 3231242131").setFechaNacimiento(LocalDate.now())
        .setSexo("Masculino");

    System.out.println(UsuarioDAO.getUsuario(usuario2.getIdUsuario()));
    System.out.println();
    System.out.println(UsuarioDAO.getUsuario("correo22@gmail.com"));


  }
}
