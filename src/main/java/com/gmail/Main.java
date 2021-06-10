package com.gmail;

import com.gmail.dao.UsuarioDAO;

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

    UsuarioDAO.addUsuario(
        UsuarioFactory.buildUsuario().setNombre("Pedro2").setApellido("Jejox2")
            .setCorreo("correo22@gmail.com")
            .setContrasenia("contrasenia22").setTelefono("+54 9 32412331232")
            .setFechaNacimiento(LocalDate.of(2020, 5, 7)).setSexo("Masculino"));
    System.out.println("Hola");

  }
}
