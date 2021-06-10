package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiltroDAO {

  public static AbsFiltro addFiltro(AbsFiltro filtro) {

    String INSERT_FILTRO_SQL =
        "INSERT INTO filtro (id_usuario, emisor, receptor, asunto, contiene, leido, "
            + "destacar, importante, eliminar, spam, id_etiqueta, id_usuario_reenviar) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager
        .getConnection(JDBCUtil.getURL(), JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILTRO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, filtro.getIdUsuario());
      preparedStatement.setString(2, filtro.getEmisor());
      preparedStatement.setString(3, filtro.getReceptor());
      preparedStatement.setString(4, filtro.getAsunto());
      preparedStatement.setString(5, filtro.getContiene());
      preparedStatement.setShort(6, (short) (filtro.getLeido() ? 1 : 0));
      preparedStatement.setShort(7, (short) (filtro.getDestacar() ? 1 : 0));
      preparedStatement.setShort(8, (short) (filtro.getImportante() ? 1 : 0));
      preparedStatement.setShort(9, (short) (filtro.getEliminar() ? 1 : 0));
      preparedStatement.setShort(10, (short) (filtro.getSpam() ? 1 : 0));
      preparedStatement.setInt(11, filtro.getIdEtiqueta());
      preparedStatement.setInt(12, filtro.getIdUsuarioReenviar());

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        filtro.setIdFiltro(rs.getInt(1));
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return filtro;
  }

  public static AbsFiltro getFiltro(int idFiltro) {

    String QUERY = "SELECT id_filtro, id_usuario, emisor, receptor, asunto, "
        + "contiene, leido, destacar, importante, eliminar, spam,"
        + " id_etiqueta, id_usuario_reenviar FROM filtro WHERE id_filtro = ?";

    AbsFiltro filtro = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idFiltro);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        filtro = FiltroFactory.buildFiltro();
        filtro.setIdFiltro(rs.getInt("id_filtro"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setEmisor(rs.getString("emisor"))
            .setReceptor(rs.getString("receptor"))
            .setAsunto(rs.getString("asunto"))
            .setContiene(rs.getString("contiene")).setLeido(rs.getShort("leido") == 1)
            .setDestacar(rs.getShort("destacar") == 1)
            .setImportante(rs.getShort(("importante")) == 1)
            .setEliminar(rs.getShort("eliminar") == 1).setSpam(rs.getShort("spam") == 1)
            .setIdEtiqueta(rs.getInt("id_etiqueta"))
            .setIdUsuarioReenviar(rs.getInt("id_usuario_reenviar"));
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return filtro;

  }

  public static List<AbsFiltro> listarFiltrosUsuario(int idUsuario) {

    String QUERY = "SELECT * FROM filtro WHERE id_usuario = ?";
    AbsFiltro filtro = null;
    List<AbsFiltro> listaFiltros = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        filtro = FiltroFactory.buildFiltro();
        filtro.setIdFiltro(rs.getInt("id_filtro"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setEmisor(rs.getString("emisor"))
            .setReceptor(rs.getString("receptor"))
            .setAsunto(rs.getString("asunto"))
            .setContiene(rs.getString("contiene")).setLeido(rs.getShort("leido") == 1)
            .setDestacar(rs.getShort("destacar") == 1)
            .setImportante(rs.getShort(("importante")) == 1)
            .setEliminar(rs.getShort("eliminar") == 1).setSpam(rs.getShort("spam") == 1)
            .setIdEtiqueta(rs.getInt("id_etiqueta"))
            .setIdUsuarioReenviar(rs.getInt("id_usuario_reenviar"));
        listaFiltros.add(filtro);
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return listaFiltros;
  }

  public static boolean updateFiltro(AbsFiltro filtro) {

    String UPDATE_FILTRO_SQL = "UPDATE filtro " +
        "SET id_usuario = ?" +
        " emisor = ?" +
        " receptor = ?" +
        " asunto = ?" +
        " contiene = ?" +
        " WHERE id_etiqueta = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILTRO_SQL)) {

      preparedStatement.setInt(1, filtro.getIdUsuario());
      preparedStatement.setString(2, filtro.getEmisor());
      preparedStatement.setString(3, filtro.getReceptor());
      preparedStatement.setString(4, filtro.getAsunto());
      preparedStatement.setString(5, filtro.getContiene());
      preparedStatement.setShort(6, (short) (filtro.getLeido() ? 1 : 0));
      preparedStatement.setShort(7, (short) (filtro.getDestacar() ? 1 : 0));
      preparedStatement.setShort(8, (short) (filtro.getImportante() ? 1 : 0));
      preparedStatement.setShort(9, (short) (filtro.getEliminar() ? 1 : 0));
      preparedStatement.setShort(10, (short) (filtro.getSpam() ? 1 : 0));
      preparedStatement.setInt(11, filtro.getIdEtiqueta());
      preparedStatement.setInt(12, filtro.getIdUsuarioReenviar());

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean deleteFiltro(int idFiltro) {

    String DELETE_FILTRO_SQL = "DELETE FROM filtro WHERE id_filtro = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILTRO_SQL)) {

      preparedStatement.setInt(1, idFiltro);

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

}
