package com.gmail.dao;

/*
-Leer usuario.
-Crear usuario.
-Modificar usuario.
-Borrar usuario.
* */

import com.gmail.conf.JDBCUtil;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsUsuario;
import com.gmail.model.UsuarioFactory;
import java.sql.*;

public class UsuarioDAO {

  public static AbsUsuario addUsuario(AbsUsuario usuario) {

    String INSERT_USUARIO_SQL = "INSERT INTO usuario" +
        "(nombre_usuario, apellido, correo, contrasenia, telefono, fecha_nacimiento, sexo)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, usuario.getNombre());
      preparedStatement.setString(2, usuario.getApellido());
      preparedStatement.setString(3, usuario.getCorreo());
      preparedStatement.setString(4, usuario.getContrasenia());
      preparedStatement.setString(5, usuario.getTelefono());
      preparedStatement.setDate(6, Date.valueOf(usuario.getFechaNacimiento()));
      preparedStatement.setString(7, usuario.getSexo());

      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        usuario.setIdUsuario(rs.getInt(1));
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return usuario;
  }

  public static AbsUsuario getUsuario(int idUsuario) throws SQLError {

    if (!existeUsuario(idUsuario)) {
      return null;
    }

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE id_usuario = ?";

    AbsUsuario usuario = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        usuario = UsuarioFactory.buildUsuario();
        usuario.setIdUsuario(rs.getInt("id_usuario")).setNombre(rs.getString("nombre_usuario"))
            .setApellido(rs.getString("apellido")).setCorreo(rs.getString("correo"))
            .setContrasenia(rs.getString("contrasenia")).setTelefono(rs.getString("telefono"))
            .setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
            .setSexo(rs.getString("sexo"));
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return usuario;

  }

  public static AbsUsuario getUsuario(String correo) {

    if (!existeUsuario(correo)) {
      return null;
    }

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE correo = ?";

    AbsUsuario usuario = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, correo);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        usuario = UsuarioFactory.buildUsuario();
        usuario.setIdUsuario(rs.getInt("id_usuario")).setNombre(rs.getString("nombre_usuario"))
            .setApellido(rs.getString("apellido")).setCorreo(rs.getString("correo"))
            .setContrasenia(rs.getString("contrasenia")).setTelefono(rs.getString("telefono"))
            .setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
            .setSexo(rs.getString("sexo"));
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return usuario;

  }

  public static boolean updateUsuario(UsuarioDTO usuario) throws SQLError{

    if (!existeUsuario(usuario.getIdUsuario())) {
      return false;
    }

    String UPDATE_USUARIO_SQL = "UPDATE usuario " +
        "SET nombre_usuario = ?, apellido = ?, contrasenia = ?, " +
        "telefono = ?, sexo = ? WHERE id_usuario = ?;";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL)) {

      preparedStatement.setString(1, usuario.getNombre());
      preparedStatement.setString(2, usuario.getApellido());
      preparedStatement.setString(3, usuario.getContrasenia());
      preparedStatement.setString(4, usuario.getTelefono());
      preparedStatement.setString(5, usuario.getSexo());
      preparedStatement.setInt(6, usuario.getIdUsuario());

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      e.getStackTrace();
      return false;
    }

    return true;

  }

  public static boolean deleteUsuario(int idUsuario) throws SQLError {

    if(!existeUsuario(idUsuario)){
      return false;
    }

    String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id_usuario = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL)) {

      preparedStatement.setInt(1, idUsuario);

      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      e.getStackTrace();
      return false;
    }

    return true;

  }

  public static boolean existeUsuario(int idUsuario) throws SQLError {

    String QUERY = "SELECT id_usuario FROM usuario WHERE id_usuario = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        return idUsuario == rs.getInt("id_usuario");
      }


    } catch (SQLException e) {
      e.getStackTrace();
    }

    return false;

  }

  public static boolean existeUsuario(String correoUsuario) {

    String QUERY = "SELECT correo FROM usuario WHERE correo = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setString(1, correoUsuario);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        return correoUsuario.equals("correo");
      }


    } catch (SQLException e) {
      System.out.println(e);
    }

    return false;

  }

}
