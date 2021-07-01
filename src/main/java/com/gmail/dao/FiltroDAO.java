package com.gmail.dao;

import com.gmail.conf.DBCPDataSourceFactory;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsFiltro;
import com.gmail.model.impl.FiltroFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FiltroDAO {

  public static AbsFiltro addFiltro(AbsFiltro filtro) throws SQLDBException {

    String INSERT_FILTRO_SQL =
        "INSERT INTO filtro (id_usuario, emisor, receptor, asunto, contiene, leido, "
            + "destacar, importante, eliminar, spam, id_etiqueta, id_usuario_reenviar) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILTRO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, filtro.getIdUsuario());
      preparedStatement.setInt(2, filtro.getIdEmisor());
      preparedStatement.setInt(3, filtro.getIdReceptor());
      preparedStatement.setString(4, filtro.getAsunto());
      preparedStatement.setString(5, filtro.getContiene());
      preparedStatement.setShort(6, (short) (filtro.getLeido() ? 1 : 0));
      preparedStatement.setShort(7, (short) (filtro.getDestacar() ? 1 : 0));
      preparedStatement.setShort(8, (short) (filtro.getImportante() ? 1 : 0));
      preparedStatement.setShort(9, (short) (filtro.getEliminar() ? 1 : 0));

      if (filtro.getSpam() == null) {
        preparedStatement.setShort(10, (short) 0);
      } else {
        preparedStatement.setInt(10, filtro.getSpam() ? 1 : 0);
      }

      if (filtro.getIdEtiqueta() != 0) {
        preparedStatement.setInt(11, filtro.getIdEtiqueta());
      } else {
        preparedStatement.setObject(11, null);
      }

      preparedStatement.setInt(12, filtro.getIdUsuarioReenviar());

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        filtro.setIdFiltro(rs.getInt(1));
      }

    } catch (SQLException e) {
      throw new SQLDBException("Error al crear el filtro.");
    }

    return filtro;
  }

  public AbsFiltro getFiltro(int idFiltro) throws SQLDBException {

    String QUERY = "SELECT id_filtro, id_usuario, emisor, receptor, asunto, "
        + "contiene, leido, destacar, importante, eliminar, spam,"
        + " id_etiqueta, id_usuario_reenviar FROM filtro WHERE id_filtro = ?";

    AbsFiltro filtro = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idFiltro);

      System.out.println("Viendo prepared " + preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        filtro = FiltroFactory.buildFiltro();
        filtro.setIdFiltro(rs.getInt("id_filtro"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setIdEmisor(rs.getInt("emisor"))
            .setIdReceptor(rs.getInt("receptor"))
            .setAsunto(rs.getString("asunto"))
            .setContiene(rs.getString("contiene")).setLeido(rs.getShort("leido") == 1)
            .setDestacar(rs.getShort("destacar") == 1)
            .setImportante(rs.getShort(("importante")) == 1)
            .setEliminar(rs.getShort("eliminar") == 1).setSpam(rs.getShort("spam") == 1)
            .setIdEtiqueta(rs.getInt("id_etiqueta"))
            .setIdUsuarioReenviar(rs.getInt("id_usuario_reenviar"));
      }

    } catch (SQLException e) {
      throw new SQLDBException("Error al obtener el filtro con el id " + idFiltro + ".");
    }

    return filtro;

  }

  public List<AbsFiltro> listarFiltrosUsuario(int idUsuario) throws SQLDBException {

    String QUERY = "SELECT * FROM filtro WHERE id_usuario = ?";
    List<AbsFiltro> listaFiltros = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        AbsFiltro filtro = FiltroFactory.buildFiltro();
        filtro.setIdFiltro(rs.getInt("id_filtro"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setIdEmisor(rs.getInt("emisor"))
            .setIdReceptor(rs.getInt("receptor"))
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
      throw new SQLDBException(
          "Error al obtener el listado de filtros del usuario con id " + idUsuario + ".");
    }

    return listaFiltros;
  }

  public boolean updateFiltro(AbsFiltro filtro) throws SQLDBException {

    System.out.println(filtro);

    String UPDATE_FILTRO_SQL = "UPDATE filtro " +
        "SET" +
        " emisor = ?,   receptor = ?, asunto = ?," +
        " contiene = ?, leido = ?, destacar = ?, importante = ?, eliminar = ?,"
        + " spam = ?, id_etiqueta = ?, id_usuario_reenviar = ? " +
        " WHERE id_filtro = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILTRO_SQL)) {

      if (filtro.getIdEmisor() == 0) {
        preparedStatement.setObject(1, null);
      } else {
        preparedStatement.setInt(1, filtro.getIdEmisor());
      }

      if (filtro.getIdReceptor() == 0) {
        preparedStatement.setObject(2, null);
      } else {
        preparedStatement.setInt(2, filtro.getIdReceptor());
      }

      preparedStatement.setString(3, filtro.getAsunto());
      preparedStatement.setString(4, filtro.getContiene());
      preparedStatement.setShort(5, (short) (filtro.getLeido() ? 1 : 0));
      preparedStatement.setShort(6, (short) (filtro.getDestacar() ? 1 : 0));
      preparedStatement.setShort(7, (short) (filtro.getImportante() ? 1 : 0));
      preparedStatement.setShort(8, (short) (filtro.getEliminar() ? 1 : 0));
      preparedStatement.setShort(9, (short) (filtro.getSpam() ? 1 : 0));

      if (filtro.getIdEtiqueta() == 0) {
        preparedStatement.setObject(10, null);
      } else {
        preparedStatement.setInt(10, filtro.getIdEtiqueta());
      }

      if (filtro.getIdUsuarioReenviar() == 0) {
        preparedStatement.setObject(11, null);
      } else {
        preparedStatement.setInt(11, filtro.getIdUsuarioReenviar());
      }

      if (filtro.getIdFiltro() == 0) {
        preparedStatement.setObject(12, null);
      } else {
        preparedStatement.setInt(12, filtro.getIdFiltro());
      }

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al actualizar el filtro con id " + filtro.getIdFiltro() + ".");
    }

    return true;

  }

  public boolean deleteFiltro(int idFiltro) throws SQLDBException {

    String DELETE_FILTRO_SQL = "DELETE FROM filtro WHERE id_filtro = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILTRO_SQL)) {

      preparedStatement.setInt(1, idFiltro);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al actualizar el filtro con id " + idFiltro + ".");
    }

    return true;

  }

}
