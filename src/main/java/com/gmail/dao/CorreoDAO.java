package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.AbsCorreo;
import com.gmail.model.Correo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoDAO {


  public static AbsCorreo addCorreo(AbsCorreo correo) {

    String INSERT_CORREO_SQL = "INSERT INTO correo" +
        "(id_usuario, asunto, cuerpo, fecha_hora, confirmado)" +
        "VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CORREO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, correo.getIdUsuario());
      preparedStatement.setString(2, correo.getAsunto());
      preparedStatement.setString(3, correo.getCuerpo());
      preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setInt(5, correo.getConfirmado() ? 1 : 0);

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        correo.setIdCorreo(rs.getInt(1));
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return correo;
  }

  public static AbsCorreo getCorreo(int idCorreo) {

    String QUERY = "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado" +
        " FROM correo WHERE id_correo = ?";

    Correo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = new Correo();
        correo.setIdCorreo(rs.getInt("id_correo"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(rs.getInt("confirmado") == 1);
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return correo;

  }

  public static List<AbsCorreo> getCorreosRecibidos(int idUsuario, boolean borrado) {

    String QUERY = "SELECT * FROM correo c " +
        "INNER JOIN recibidos r ON c.id_correo = r.id_correo" +
        "WHERE r.borrado = ? AND r.id_usuario_2 =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();
    Correo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, borrado ? 1 : 0);
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = new Correo();
        correo.setIdCorreo(rs.getInt("id_correo"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(true);
        correos.add(correo);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correos;

  }

  public static List<AbsCorreo> getCorreosEnviados(int idUsuario, boolean borrado) {

    String QUERY = "SELECT * FROM correo c " +
            "WHERE c.borrado = ? AND c.id_usuario =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();
    Correo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
            JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
                 Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, borrado ? 1 : 0);
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = new Correo();
        correo.setIdCorreo(rs.getInt("id_correo"))
                .setIdUsuario(rs.getInt("id_usuario"))
                .setAsunto(rs.getString("asunto"))
                .setCuerpo(rs.getString("cuerpo"))
                .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
                .setConfirmado(true);
        correos.add(correo);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correos;

  }

  public static boolean updateCorreo(AbsCorreo correo) {
    String UPDATE_CORREO_SQL = "UPDATE correo" +
        "SET asunto = ?" +
        "cuerpo = ?" +
        "fecha_hora = ?" +
        "confirmado = ?" +
        "WHERE id_correo = ?;";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setString(1, correo.getAsunto());
      preparedStatement.setString(2, correo.getCuerpo());
      preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setInt(4, correo.getConfirmado() ? 1 : 0);
      preparedStatement.setInt(5, correo.getIdCorreo());

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }


  public static boolean deleteCorreo(int idCorreo, int idUsuario) {

    String BORRAR_CORREO_SQL = "UPDATE recibidos " +
        "SET borrado = ? " +
        "WHERE id_usuario = ? AND id_correo = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(BORRAR_CORREO_SQL)) {

      preparedStatement.setInt(1, 1);
      preparedStatement.setInt(2, idUsuario);
      preparedStatement.setInt(3, idCorreo);

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean deleteCorreo(int idCorreo) {

    String UPDATE_CORREO_SQL = "UPDATE correo" +
        "borrado = ?" +
        "WHERE id_correo = ?;";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setInt(1, 1);
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


  public static boolean enviarCorreo(int id_correo, int id_receptor) {

    String INSERT_ENVIAR_SQL = "INSERT INTO recibido" +
        "(id_usuario_2, id_correo, fecha_hora)" +
        "VALUES (?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENVIAR_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, id_receptor);
      preparedStatement.setInt(2, id_correo);
      preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;
  }

  public static int enviarCorreo(int id_correo, int[] id_receptores) {
    int cantE = 0;
    for (int i : id_receptores) {

      if (!(enviarCorreo(id_correo, i))) {
        System.out.println("Fallo el envio, receptor:" + i);
        continue;
      }

      cantE++;

    }

    return cantE;
  }

}
