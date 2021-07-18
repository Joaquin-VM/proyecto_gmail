package com.gmail.conf;

import static spark.Spark.port;

public class Web {

  private static final String host = "localhost";
  private static final int puerto = 6584;

  public static void arrancarServidor(){

    port(puerto);

    System.out.println("Servidor escuchando en " + host + ":" + puerto + " ...");

  }

}
