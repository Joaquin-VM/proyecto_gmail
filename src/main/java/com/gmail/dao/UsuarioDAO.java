package com.gmail.dao;

/*
-Leer usuario.
-Crear usuario.
-Modificar usuario.
-Borrar usuario.
* */

import com.gmail.conf.JDBCUtil;
import com.gmail.model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class UsuarioDAO {

  public static Usuario addUsuario(Usuario usuario) {

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

  public static Usuario getUsuario(int idUsuario) {

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE id_usuario = ?";

    Usuario usuario = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, idUsuario);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        usuario = new Usuario();
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

  public static Usuario getUsuario(String correo) {

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE correo = ?";

    Usuario usuario = null;

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, correo);

      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        usuario = new Usuario();
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

  public static boolean updateUsuario(Usuario usuario) {
    String UPDATE_USUARIO_SQL = "UPDATE usuario" +
        "SET nombre_usuario = ?" +
        "apellido = ?" +
        "contrasenia = ?" +
        "telefono = ?" +
        "sexo = ?" +
        "WHERE id_usuario = ?;";

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
      System.out.println(e);
      return false;
    }

    return true;

  }

  public static boolean deleteUsuario(int idUsuario) {

    String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id_usuario = ?";

    try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
        JDBCUtil.getUsuario(), JDBCUtil.getClave());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL)) {

      preparedStatement.setInt(1, idUsuario);

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
