package com.gmail.dao;

import com.gmail.conf.DBCPDataSourceFactory;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import com.gmail.model.impl.CorreoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoDAO {

  public AbsCorreo addCorreo(AbsCorreo correo) throws SQLDBException {

    String INSERT_CORREO_SQL = "INSERT INTO correo" +
        "(id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante)"
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
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

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        correo.setIdCorreo(rs.getInt(1));
      }

    } catch (SQLException e) {
      throw new SQLDBException("Error al agregar el correo.");
    }

    return correo;
  }

  public AbsCorreo getCorreo(int idCorreo) throws SQLDBException {

    String QUERY =
        "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante"
            +
            " FROM correo WHERE id_correo = ?";

    AbsCorreo correo = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idCorreo);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
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
      throw new SQLDBException("Error al obtener el correo con el id " + idCorreo + ".");
    }

    return correo;

  }

  public AbsCorreo getCorreoRecibido(int idCorreo, int idUsuario) throws SQLDBException {

    String QUERY =
        "SELECT c.id_correo, c.id_usuario, c.asunto, c.cuerpo, c.fecha_hora, r.borrado, r.leido, r.destacado, r.importante"
            + " FROM correo c INNER JOIN recibidos r ON c.id_correo = r.id_correo " +
            "WHERE c.id_correo = ? AND r.id_usuario_2 =  ? ";

    AbsCorreo correo = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idCorreo);
      preparedStatement.setInt(2, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
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
      throw new SQLDBException(
          "Error al obtener el correo con el id " + idCorreo + " recibido por el usuario "
              + idUsuario + ".");
    }

    return correo;

  }

  public List<AbsCorreo> getCorreosRecibidos(int idUsuario, boolean borrado) throws SQLDBException {

    String QUERY =
        "SELECT c.id_correo, c.id_usuario, c.asunto, c.cuerpo, c.fecha_hora, c.confirmado, r.borrado, r.leido, r.destacado, r.importante"
            + " FROM correo c INNER JOIN recibidos r ON c.id_correo = r.id_correo " +
            " WHERE r.borrado = ? AND r.id_usuario_2 =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setShort(1, (short) (borrado ? 1 : 0));
      preparedStatement.setInt(2, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        AbsCorreo correo = CorreoFactory.buildCorreo();
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
      throw new SQLDBException(
          "Error al obtener la lista de correos recibidos por el usuario con el id " + idUsuario
              + ".");
    }

    return correos;

  }

  public List<AbsCorreo> getCorreosEnviados(int idUsuario, boolean borrado) throws SQLDBException {

    String QUERY =
        "SELECT id_correo, id_usuario, asunto, cuerpo, fecha_hora, confirmado, borrado, leido, destacado, importante"
            + " FROM correo c WHERE c.borrado = ? AND c.id_usuario =  ? ";

    List<AbsCorreo> correos = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setShort(1, (short) (borrado ? 1 : 0));
      preparedStatement.setInt(2, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        AbsCorreo correo = CorreoFactory.buildCorreo();
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
      throw new SQLDBException(
          "Error al obtener la lista de correos enviados por el usuario con el id " + idUsuario
              + ".");
    }

    return correos;

  }

  public boolean updateCorreo(AbsCorreo correo) throws SQLDBException {
    String UPDATE_CORREO_SQL = "UPDATE correo " +
        "SET id_usuario = ?, asunto = ?,  cuerpo = ?,  fecha_hora = ?,  confirmado = ?,  borrado = ?,"
        + " leido = ?,  destacado = ?,  importante  = ?  WHERE id_correo = ?;";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
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

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al actualizar el correo con el id " + correo.getIdCorreo() + ".");
    }

    return true;

  }

  public boolean deleteCorreo(int idCorreo, int idUsuario) throws SQLDBException {

    String DELETE_CORREO_SQL = "UPDATE recibidos SET borrado = ? WHERE id_usuario_2 = ? AND id_correo = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CORREO_SQL)) {

      preparedStatement.setShort(1, (short) 1);
      preparedStatement.setInt(2, idUsuario);
      preparedStatement.setInt(3, idCorreo);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al eliminar el correo con el id " + idCorreo + ".");
    }

    return true;

  }

  public boolean deleteCorreo(int idCorreo) throws SQLDBException {

    String UPDATE_CORREO_SQL = "UPDATE correo SET borrado = ? WHERE id_correo = ?;";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setShort(1, (short) 1);
      preparedStatement.setInt(2, idCorreo);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al eliminar el correo con el id " + idCorreo + ".");
    }

    return true;

  }

  public boolean enviarCorreo(AbsCorreo correo, int id_receptor) throws SQLDBException {

    String INSERT_ENVIAR_SQL = "INSERT INTO recibidos" +
        "(id_usuario_2, id_correo, borrado, leido, destacado, importante)" +
        "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(INSERT_ENVIAR_SQL, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, id_receptor);
      preparedStatement.setInt(2, correo.getIdCorreo());
      preparedStatement.setShort(3, (short) (correo.getBorrado() ? 1 : 0));
      preparedStatement.setShort(4, (short) (correo.getLeido() ? 1 : 0));
      preparedStatement.setShort(5, (short) (correo.getDestacado() ? 1 : 0));
      preparedStatement.setShort(6, (short) (correo.getImportante() ? 1 : 0));

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al enviar el correo con el id " + correo.getIdCorreo() + " al usuario con el id "
              + id_receptor + ".");
    }

    return true;
  }

  public boolean updateCorreoRecibido(AbsCorreo correo, int idUsuario) throws SQLDBException {
    String UPDATE_CORREO_SQL = "UPDATE recibidos " +
        "SET borrado = ?, leido = ?,  destacado = ?,  importante  = ?  WHERE id_correo = ? AND id_usuario_2 = ?;";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CORREO_SQL)) {

      preparedStatement.setShort(1, (short) (correo.getBorrado() ? 1 : 0));
      preparedStatement.setShort(2, (short) (correo.getLeido() ? 1 : 0));
      preparedStatement.setShort(3, (short) (correo.getDestacado() ? 1 : 0));
      preparedStatement.setShort(4, (short) (correo.getImportante() ? 1 : 0));
      preparedStatement.setInt(5, correo.getIdCorreo());
      preparedStatement.setInt(6, idUsuario);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

//      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al actualizar el correo con el id " + correo.getIdCorreo()
          + " recibido por el usuario con el id " + idUsuario + ".");
    }

    return true;

  }

  public boolean enviarCorreo(AbsCorreo correo, int[] id_receptores) throws SQLDBException {

    for (int i : id_receptores) {

      if (!(enviarCorreo(correo, i))) {
        throw new SQLDBException("Fallo el envio, receptor: " + id_receptores[i]);
      }

    }

    return true;
  }

  public List<String> correosQueRecibieron(int idCorreo) throws SQLDBException {

    String QUERY = "SELECT u.correo FROM usuario u INNER JOIN "
        + "recibidos r ON u.id_usuario = r.id_usuario_2 WHERE "
        + "id_correo = ?;";

    List<String> listaCorreos = new ArrayList<>();

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idCorreo);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        listaCorreos.add(rs.getString("correo"));
      }

    } catch (SQLException e) {
      throw new SQLDBException("El correo con id " + idCorreo + " no existe.");
    }

    return listaCorreos;

  }

}
