package com.gmail.conf;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DBCPDataSourceFactory {

  private static final String usuario = "jvega420";
  private static final String clave = "pwd420";
  private static final String host = "bd1.mooo.com";
  private static final String puerto = "3306";
  private static final String baseDeDatos = "jvega420_3";
  private static final String cantidadConexionesMax = "max-connections=10";
  private static String URL =
      "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos + "?" + cantidadConexionesMax
          + "useSSL=false";

  public static DataSource getMySQLDataSource() throws SQLException {

    MysqlDataSource mysqlDS = new MysqlDataSource();

    mysqlDS.setUser(usuario);
    mysqlDS.setPassword(clave);
    mysqlDS.setURL(URL);
    mysqlDS.setAllowMultiQueries(true);
    return mysqlDS;

  }

}
