package com.gmail.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

  private static final String usuario = "jvega420";
  private static final String clave = "pwd420";
  private static final String host = "bd1.mooo.com";
  private static final String puerto = "3306";
  private static final String baseDeDatos = "jvega420_3";

  public static String getURL() {
    return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
  }

  public static String getUsuario() {
    return usuario;
  }

  public static String getClave() {
    return clave;
  }

}
