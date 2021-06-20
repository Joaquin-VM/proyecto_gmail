package com.gmail.service;

import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import com.gmail.service.impl.UsuarioService;
import java.time.LocalDateTime;
import java.util.List;

public class MostrarService {

  UsuarioService usuarioService = new UsuarioService();

  public void mostrarCorreos(List<AbsCorreo> lista, boolean esEnviado$)
      throws SQLDBException, NotFoundException {

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
      String asunt = new String("");
      String aux = new String();
      int ar;
      ;
      if (esEnviado$) {
        for (int i = 0; i < c.getUsuariosQueRecibieron().size(); ++i) {
          if (i == 2) {
            aux = aux + (c.getUsuariosQueRecibieron().size() - 2) + " mas...";
            break;
          }
          aux = c.getUsuariosQueRecibieron().get(i);
          ar = aux.indexOf('@');
          aux = aux.substring(0, ar);
          if (i < c.getUsuariosQueRecibieron().size() - 1) {
            aux = aux + " , ";
          }
          asunt = asunt + aux;
        }
      } else {
        asunt = usuarioService.obtenerUno(c.getIdUsuario()).getCorreo();
        ar = asunt.indexOf('@');
        asunt = asunt.substring(0, ar);
      }

      System.out.printf("%-15s", asunt);
      System.out.printf("%-5s", "|");
      asunt = c.getAsunto();
      if (asunt.length() > 35) {
        System.out.printf("%-40s", asunt.substring(0, 35));
      } else {
        System.out.printf("%-40s", asunt);
      }
      if (c.getFechaHora().getYear() == LocalDateTime.now().getYear()) {
        System.out.print(
            c.getFechaHora().getDayOfMonth() + " " + c.getFechaHora().getMonth().toString()
                .substring(0, 3) + ".");
      } else {
        System.out.print(
            c.getFechaHora().getDayOfMonth() + " " + c.getFechaHora().getMonth().toString()
                .substring(0, 3) + ". " + c.getFechaHora().getYear());
      }
      System.out.println();
      System.out.println(
          "-----------------------------------------------------------------------------------------------------------");
    }
    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");
  }


  public void abrirCorreo(AbsCorreo c) throws SQLDBException, NotFoundException {

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
    System.out.println("Enviado por: " + usuarioService.obtenerUno(c.getIdUsuario()).getCorreo());
    System.out.println(
        "Enviado el: " + c.getFechaHora().getDayOfMonth() + " " + c.getFechaHora().getMonth() + " "
            + c
            .getFechaHora().getYear() + " a las " + c.getFechaHora().getHour() + ":" + ((c
            .getFechaHora().getMinute()) < 10 ? ("0" + c.getFechaHora().getMinute())
            : c.getFechaHora().getMinute()));
    System.out.print("Recibido por: ");
    for (int i = 0; i < c.getUsuariosQueRecibieron().size(); ++i) {
      System.out.print(c.getUsuariosQueRecibieron().get(i));
      if (i < c.getUsuariosQueRecibieron().size() - 1) {
        System.out.println(", ");
      }
    }
    System.out.println();
    System.out.println(c.getCuerpo());
    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////");

  }

}
