package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.Correo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoDAO {


  public static Correo addCorreo(Correo correo) {

    String INSERT_CORREO_SQL = "INSERT INTO correo" +
        "(asunto, cuerpo, fecha_hora, confirmado)" +
        "VALUES (?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CORREO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, correo.getAsunto());
      preparedStatement.setString(2, correo.getCuerpo());
      preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setInt(4, correo.getConfirmado() ? 1 : 0);

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

  public static Correo getCorreo(int idCorreo) {

    String QUERY = "SELECT id_correo, asunto, cuerpo, fecha_hora, confirmado" +
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
        correo.setIdCorreo(rs.getInt("id_correo")).setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(rs.getInt("confirmado") == 1);
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return correo;

  }

  public static List<Correo> getCorreosRecibidos(int idUsuario, boolean borrado) {

    String QUERY = "SELECT * FROM correo c " +
        "INNER JOIN enviar e ON c.id_correo = e.id_correo" +
        "INNER JOIN borrar b ON b.id_correo = c.id_correo" +
        "WHERE c.confirmado = 1 AND b.borrado = ? AND e.id_usuario_2 =  ? ";

    List<Correo> correos = null;
    Correo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, borrado  ? 1 : 0);
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();


      while (rs.next()) {
        correos = new ArrayList<>();
        correo = new Correo();
        correo.setIdCorreo(rs.getInt("id_correo")).setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime()).setConfirmado(true);
        correos.add(correo);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correos;

  }

  public static boolean updateCorreo(Correo correo) {
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
      preparedStatement.setTimestamp(3, Timestamp.valueOf(correo.getFechaHora()));
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

    String BORRAR_CORREO_SQL = "UPDATE borrar " +
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

    String DELETE_CORREO_SQL = "DELETE FROM correo WHERE id_correo = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CORREO_SQL)) {

      preparedStatement.setInt(1, idCorreo);

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean borrado$Correo(int id_correo,int id_usuario) {
    String INSERT_BORRAR_SQL = "INSERT INTO borrar" +
            "(id_usuario, id_correo)" +
            "VALUES (?, ?)";
    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
            JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORRAR_SQL,
                 Statement.RETURN_GENERATED_KEYS)){

      preparedStatement.setInt(1, id_usuario);
      preparedStatement.setInt(2, id_correo);

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();


    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    return true;
  }


  public static boolean enviarCorreo(int id_correo,int id_emisor, int id_receptor) {

    String INSERT_ENVIAR_SQL = "INSERT INTO enviar" +
            "(id_usuario_1, id_usuario_2, id_correo, fecha_hora)" +
            "VALUES (?, ?, ?, ?)";


    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
            JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENVIAR_SQL,
                 Statement.RETURN_GENERATED_KEYS)){

      preparedStatement.setInt(1, id_emisor);
      preparedStatement.setInt(2, id_receptor);
      preparedStatement.setInt(3, id_correo);
      preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs1 = preparedStatement.getGeneratedKeys();

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }

    borrado$Correo(id_correo,id_receptor);
    borrado$Correo(id_correo,id_emisor);

    return true;
  }
  public static int enviarCorreo(int id_correo,int id_emisor, int[] id_receptor) {
    int cantE=0;
    for (int i:id_receptor) {

      if (!(enviarCorreo(id_correo,id_emisor,i))){
        System.out.println("Fallo el envio, receptor:"+i);
        continue;
      }


      cantE++;

    }

    return cantE;
  }

}
