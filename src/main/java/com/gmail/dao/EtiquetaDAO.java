package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.Correo;
import com.gmail.model.Etiqueta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAO {

  public static AbsEtiqueta addEtiqueta(AbsEtiqueta etiqueta) {

    String INSERT_ETIQUETA_SQL = "INSERT INTO etiqueta" +
        "(nombre_etiqueta, id_usuario) VALUES (?, ?)";

    try (Connection connection = DriverManager
        .getConnection(JDBCUtil.getURL(), JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETIQUETA_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, etiqueta.getNombreEtiqueta());
      preparedStatement.setInt(2, etiqueta.getIdUsuario());

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        etiqueta.setIdEtiqueta(rs.getInt(1));
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return etiqueta;
  }

  public static AbsEtiqueta getEtiqueta(int idEtiqueta) {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta WHERE id_usuario = ?";

    Etiqueta etiqueta = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idEtiqueta);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        etiqueta = new Etiqueta();
        etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"));
        etiqueta.setNombreEtiqueta(rs.getString("nombre_etiqueta"));
        etiqueta.setIdEtiqueta(rs.getInt("id_usuario"));
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return etiqueta;

  }

  public static List<AbsEtiqueta> getEtiqueta(String nombreEtiqueta, int idUsuario) {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta "
        + "WHERE nombre_etiqueta LIKE '% ? %' AND id_usuario = ?";

    Etiqueta etiqueta = null;
    List<AbsEtiqueta> listaEtiquetas = new ArrayList<>();;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, nombreEtiqueta);
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        etiqueta = new Etiqueta();
        etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"));
        etiqueta.setNombreEtiqueta(rs.getString("nombre_etiqueta"));
        etiqueta.setIdEtiqueta(rs.getInt("id_usuario"));
        listaEtiquetas.add(etiqueta);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return listaEtiquetas;

  }

  public static List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) {

    String QUERY = "SELECT id_etiqueta, nombre_etiqueta, id_usuario FROM etiqueta WHERE id_usuario = ?";
    Etiqueta etiqueta = null;
    List<AbsEtiqueta> listaEtiquetas = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        etiqueta = new Etiqueta();
        etiqueta = etiqueta.setIdEtiqueta(rs.getInt("id_etiqueta"))
            .setNombreEtiqueta(rs.getString("nombre_etiqueta"))
            .setIdUsuario(rs.getInt("id_usuario"));
        listaEtiquetas.add(etiqueta);
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return listaEtiquetas;
  }

  public static boolean updateEtiqueta(AbsEtiqueta etiqueta) {
    String UPDATE_ETIQUETA_SQL = "UPDATE etiqueta SET nombre_etiqueta = ? WHERE id_etiqueta = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ETIQUETA_SQL)) {

      preparedStatement.setString(1, etiqueta.getNombreEtiqueta());
      preparedStatement.setInt(2, etiqueta.getIdEtiqueta());

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean deleteEtiqueta(int idEtiqueta) {

    String DELETE_ETIQUETA_SQL = "DELETE FROM etiqueta WHERE id_etiqueta = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ETIQUETA_SQL)) {

      preparedStatement.setInt(1, idEtiqueta);

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean agregarCorreoEtiqueta(int idCorreo, int idEtiqueta) {

    String INSERT_CLASIFICAR_SQL = "INSERT INTO clasificar" +
            "(id_correo, id_etiqueta) VALUES (?, ?)";

    try (Connection connection = DriverManager
            .getConnection(JDBCUtil.getURL(), JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASIFICAR_SQL,
                 Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);
      preparedStatement.setInt(2, idEtiqueta);

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;
  }

  public static boolean quitarEtiqueta(int idCorreo, int idEtiqueta) {

    String DELETE_ETIQUETA_SQL = "DELETE FROM clasificar WHERE id_etiqueta = ? AND id_correo = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
            JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ETIQUETA_SQL)) {

      preparedStatement.setInt(1, idEtiqueta);
      preparedStatement.setInt(2, idCorreo);

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





