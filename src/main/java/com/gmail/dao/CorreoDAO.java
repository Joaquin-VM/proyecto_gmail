package com.gmail.dao;

import com.gmail.conf.JDBCUtil;
import com.gmail.model.AbsCorreo;

import com.gmail.model.CorreoFactory;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoDAO {

  public static AbsCorreo addCorreo(AbsCorreo correo) {

    String INSERT_CORREO_SQL = "INSERT INTO correo" +
        "(id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante)"
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CORREO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, correo.getIdUsuario());
      preparedStatement.setString(2, correo.getAsunto());
      preparedStatement.setString(3, correo.getCuerpo());
      preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setShort(5, (short) (correo.getConfirmado() ? 1 : 0));
      preparedStatement.setShort(6, (short) (correo.getBorrado() ? 1 : 0));
      preparedStatement.setShort(7, (short) (correo.getLeido() ? 1 : 0));
      preparedStatement.setShort(8, (short) (correo.getDestacado() ? 1 : 0));
      preparedStatement.setShort(9, (short) (correo.getImportante() ? 1 : 0));

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

    String QUERY =
        "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante"
            +
            " FROM correo WHERE id_correo = ?";

    AbsCorreo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = CorreoFactory.buildCorreo();
        correo.setIdCorreo(rs.getInt("id_correo"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(rs.getShort("confirmado") == 1).setBorrado(rs.getShort("borrado") == 1)
            .setLeido(rs.getShort("leido") == 1).setDestacado(rs.getShort("destacado") == 1)
            .setImportante(rs.getShort("importante") == 1);
      }

    } catch (SQLException e) {
      System.out.println(e);
    }

    return correo;

  }

  public static AbsCorreo getCorreoRecibido(int idCorreo, int idUsuario) {

    String QUERY =
            "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, r.borrado, r.leido, r.destacado, r.importante"
                    + " FROM correo c INNER JOIN recibidos r ON c.id_correo = r.id_correo" +
                    "WHERE c.id_correo = ? AND r.id_usuario_2 =  ? ";

    AbsCorreo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
            JDBCUtil.getUsuario(), JDBCUtil.getClave());
         PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
                 Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = CorreoFactory.buildCorreo();
        correo.setIdCorreo(rs.getInt("id_correo"))
                .setIdUsuario(rs.getInt("id_usuario"))
                .setAsunto(rs.getString("asunto"))
                .setCuerpo(rs.getString("cuerpo"))
                .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
                .setBorrado(rs.getShort("borrado") == 1)
                .setLeido(rs.getShort("leido") == 1).setDestacado(rs.getShort("destacado") == 1)
                .setImportante(rs.getShort("importante") == 1);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correo;

  }

  public static List<AbsCorreo> getCorreosRecibidos(int idUsuario, boolean borrado) {

    String QUERY =
        "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante"
            + " FROM correo c INNER JOIN recibidos r ON c.id_correo = r.id_correo" +
            "WHERE r.borrado = ? AND r.id_usuario_2 =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();
    AbsCorreo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setShort(1, (short) (borrado ? 1 : 0));
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = CorreoFactory.buildCorreo();
        correo.setIdCorreo(rs.getInt("id_correo"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(rs.getShort("confirmado") == 1).setBorrado(rs.getShort("borrado") == 1)
            .setLeido(rs.getShort("leido") == 1).setDestacado(rs.getShort("destacado") == 1)
            .setImportante(rs.getShort("importante") == 1);
        correos.add(correo);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correos;

  }

  public static List<AbsCorreo> getCorreosEnviados(int idUsuario, boolean borrado) {

    String QUERY =
        "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante"
            + " FROM correo c WHERE c.borrado = ? AND c.id_usuario =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();
    AbsCorreo correo = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setShort(1, (short) (borrado ? 1 : 0));
      preparedStatement.setInt(2, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        correo = CorreoFactory.buildCorreo();
        correo.setIdCorreo(rs.getInt("id_correo"))
            .setIdUsuario(rs.getInt("id_usuario"))
            .setAsunto(rs.getString("asunto"))
            .setCuerpo(rs.getString("cuerpo"))
            .setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime())
            .setConfirmado(rs.getShort("confirmado") == 1).setBorrado(rs.getShort("borrado") == 1)
            .setLeido(rs.getShort("leido") == 1).setDestacado(rs.getShort("destacado") == 1)
            .setImportante(rs.getShort("importante") == 1);
        correos.add(correo);
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return correos;

  }

  public static boolean updateCorreo(AbsCorreo correo) {
    String UPDATE_CORREO_SQL = "UPDATE correo " +
        "SET id_usuario = ?, asunto = ?,  cuerpo = ?,  fecha_hora = ?,  confirmado = ?,  borrado = ?,"
        + " leido = ?,  destacado = ?,  importante  = ?  WHERE id_correo = ?;";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setInt(1, correo.getIdUsuario());
      preparedStatement.setString(2, correo.getAsunto());
      preparedStatement.setString(3, correo.getCuerpo());
      preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setShort(5, (short) (correo.getConfirmado() ? 1 : 0));
      preparedStatement.setShort(6, (short) (correo.getBorrado() ? 1 : 0));
      preparedStatement.setShort(7, (short) (correo.getLeido() ? 1 : 0));
      preparedStatement.setShort(8, (short) (correo.getDestacado() ? 1 : 0));
      preparedStatement.setShort(9, (short) (correo.getImportante() ? 1 : 0));
      preparedStatement.setInt(10, correo.getIdCorreo());

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

    String BORRAR_CORREO_SQL = "UPDATE correo SET borrado = ? WHERE id_usuario = ? AND id_correo = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(BORRAR_CORREO_SQL)) {

      preparedStatement.setShort(1, (short) 1);
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

    String UPDATE_CORREO_SQL = "UPDATE correo SET borrado = ? WHERE id_correo = ?;";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setShort(1, (short) 1);
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

    String INSERT_ENVIAR_SQL = "INSERT INTO recibidos" +
        "(id_usuario_2, id_correo)" +
        "VALUES (?, ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENVIAR_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, id_receptor);
      preparedStatement.setInt(2, id_correo);

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
        System.out.println("Fallo el envio, receptor: " + i);
        continue;
      }

      cantE++;

    }

    return cantE;
  }

}
