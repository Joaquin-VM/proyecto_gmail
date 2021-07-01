package com.gmail.dao;

import com.gmail.conf.DBCPDataSourceFactory;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.impl.EtiquetaFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAO {

  public AbsEtiqueta addEtiqueta(AbsEtiqueta etiqueta) throws SQLDBException {

    String INSERT_ETIQUETA_SQL = "INSERT INTO etiqueta" +
        "(nombre_etiqueta, id_usuario) VALUES (?, ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETIQUETA_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, etiqueta.getNombreEtiqueta());
      preparedStatement.setInt(2, etiqueta.getIdUsuario());

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        etiqueta.setIdEtiqueta(rs.getInt(1));
      }

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al agregar la etiqueta de nombre " + etiqueta.getNombreEtiqueta() + ".");
    }

    return etiqueta;
  }

  public AbsEtiqueta getEtiqueta(int idEtiqueta) throws SQLDBException {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta WHERE id_etiqueta = ?";

    AbsEtiqueta etiqueta = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idEtiqueta);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        etiqueta = EtiquetaFactory.buildEtiqueta();
        etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"));
        etiqueta.setNombreEtiqueta(rs.getString("nombre_etiqueta"));
        etiqueta.setIdUsuario(rs.getInt("id_usuario"));
      }


    } catch (SQLException e) {
      throw new SQLDBException("Error al obtener la etiqueta con el id " + idEtiqueta + ".");
    }

    return etiqueta;

  }

  public List<AbsEtiqueta> getEtiquetasCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLDBException {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta "
        + "WHERE nombre_etiqueta LIKE '%?%' AND id_usuario = ?";

    List<AbsEtiqueta> listaCoincidentes = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setString(1, nombreEtiqueta);
      preparedStatement.setInt(2, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        AbsEtiqueta etiqueta = EtiquetaFactory.buildEtiqueta();
        etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"));
        etiqueta.setNombreEtiqueta(rs.getString("nombre_etiqueta"));
        etiqueta.setIdEtiqueta(rs.getInt("id_usuario"));
        listaCoincidentes.add(etiqueta);
      }


    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al obtener la lista de etiquetas con el nombre " +
              nombreEtiqueta + ".");
    }

    return listaCoincidentes;

  }

  public List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) throws SQLDBException {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta WHERE id_usuario = ?";
    List<AbsEtiqueta> listaEtiquetas = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        AbsEtiqueta etiqueta = EtiquetaFactory.buildEtiqueta();
        etiqueta = etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"))
            .setNombreEtiqueta(rs.getString("nombre_etiqueta"))
            .setIdUsuario(rs.getInt("id_usuario"));
        listaEtiquetas.add(etiqueta);
      }

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al obtener el listado de etiquetas del usuario con id " + idUsuario + ".");
    }

    return listaEtiquetas;
  }

  public boolean updateEtiqueta(AbsEtiqueta etiqueta) throws SQLDBException {

    String UPDATE_ETIQUETA_SQL = "UPDATE etiqueta SET nombre_etiqueta = ? WHERE id_etiqueta = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ETIQUETA_SQL)) {

      preparedStatement.setString(1, etiqueta.getNombreEtiqueta());
      preparedStatement.setInt(2, etiqueta.getIdEtiqueta());

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al modificar la etiqueta con id " + etiqueta.getIdEtiqueta() + ".");
    }

    return true;

  }

  public boolean deleteEtiqueta(int idEtiqueta) throws SQLDBException {

    String DELETE_ETIQUETA_SQL = "DELETE FROM etiqueta WHERE id_etiqueta = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ETIQUETA_SQL)) {

      preparedStatement.setInt(1, idEtiqueta);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al eliminar la etiqueta con id " + idEtiqueta + ".");
    }

    return true;

  }

  public boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLDBException {

    String INSERT_CLASIFICAR_SQL = "INSERT INTO clasificar" +
        "(id_correo, id_etiqueta) VALUES (?, ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(INSERT_CLASIFICAR_SQL, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);
      preparedStatement.setInt(2, idEtiqueta);

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al agregar al correo con id " + idCorreo + " la etiqueta con id " + idEtiqueta
              + ".");
    }

    return true;
  }

  public List<AbsEtiqueta> obtenerEtiquetasDeCorreo(int idCorreo) throws SQLDBException {

    String QUERY = "SELECT id_etiqueta FROM clasificar WHERE id_correo = ? ORDER BY id_etiqueta";
    AbsEtiqueta etiqueta = null;
    List<AbsEtiqueta> listaCoincidentes = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idCorreo);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        etiqueta = EtiquetaFactory.buildEtiqueta();
        etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"));
        etiqueta.setNombreEtiqueta(rs.getString("nombre_etiqueta"));
        etiqueta.setIdEtiqueta(rs.getInt("id_usuario"));
        listaCoincidentes.add(etiqueta);
      }

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al obtener el listado de etiquetas del correo con id " + idCorreo + ".");
    }

    return listaCoincidentes;

  }

  public boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLDBException {

    String DELETE_ETIQUETA_SQL = "DELETE FROM clasificar WHERE id_etiqueta = ? AND id_correo = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ETIQUETA_SQL)) {

      preparedStatement.setInt(1, idEtiqueta);
      preparedStatement.setInt(2, idCorreo);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al quitar al correo con id " + idCorreo + " la etiqueta con id " + idEtiqueta
              + ".");
    }

    return true;

  }

}





