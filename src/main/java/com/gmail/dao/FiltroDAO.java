package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.AbsFiltro;
import com.gmail.model.Filtro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FiltroDAO {

  public static Filtro addFiltro(Filtro filtro) {
    String INSERT_FILTRO_SQL = "INSERT INTO filtro emisor, receptor, asunto, contiene VALUES(?, ?, ?, ?)";
    try (Connection connection = DriverManager
        .getConnection(JDBCUtil.getURL(), JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILTRO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {



    } catch (SQLException e) {
      System.out.println(e);
    }

    return filtro;
  }

}
