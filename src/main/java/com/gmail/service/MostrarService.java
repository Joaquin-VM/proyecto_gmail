package com.gmail.service;

import com.gmail.dao.UsuarioDAO;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import java.util.List;

public class MostrarService {

  UsuarioDAO usuarioDAO = new UsuarioDAO();

  public void mostrarCorreos(List<AbsCorreo> lista) throws SQLDBException {

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println(
        "-----------------------------------------------------------------------------------------------------------");
    for (AbsCorreo c : lista) {
        if (c.getLeido()) {
            System.out.printf("%-2s", "");
        } else {
            System.out.printf("%-2s", "SL");
        }
      System.out.printf("%-2s", "|");
        if (c.getDestacado()) {
            System.out.printf("%-2s", "D");
        } else {
            System.out.printf("%-2s", "");
        }
      System.out.printf("%-2s", "|");
        if (c.getImportante()) {
            System.out.printf("%-2s", "I");
        } else {
            System.out.printf("%-2s", "");
        }
      System.out.printf("%-2s", "|");
      String asunt = usuarioDAO.getUsuario(c.getIdUsuario()).getCorreo();
      int ar = asunt.indexOf('@');
      System.out.printf("%-15s", asunt.substring(0, ar));
      System.out.printf("%-5s", "|");
      asunt = c.getAsunto();
        if (asunt.length() > 35) {
            System.out.printf("%-40s", asunt.substring(0, 35));
        } else {
            System.out.printf("%-40s", asunt);
        }
      System.out.println();
      System.out.println(
          "-----------------------------------------------------------------------------------------------------------");
    }
    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");
  }


  public void abrirCorreo(AbsCorreo c) throws SQLDBException {
    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");
    String asunt = c.getAsunto();
    System.out.print("ASUNTO:");
      if (asunt.length() > 40) {
          System.out.println(asunt.substring(0, 40));
          System.out.println(asunt.substring(40));
      } else {
          System.out.println(asunt);
      }
    System.out.println("----------------------------------");
    System.out.println("Enviado por: " + usuarioDAO.getUsuario(c.getIdUsuario()).getCorreo());
    System.out.println(c.getCuerpo());
    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");


  }

}
