package com.gmail.dao;

import com.gmail.conf.DBCPDataSourceFactory;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.UsuarioFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

  public AbsUsuario addUsuario(AbsUsuario usuario) throws SQLDBException {

    String INSERT_USUARIO_SQL = "INSERT INTO usuario" +
        "(nombre_usuario, apellido, correo, contrasenia, telefono, fecha_nacimiento, sexo)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL,
            Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, usuario.getNombre());
      preparedStatement.setString(2, usuario.getApellido());
      preparedStatement.setString(3, usuario.getCorreo());
      preparedStatement.setString(4, usuario.getContrasenia());
      preparedStatement.setString(5, usuario.getTelefono());
      preparedStatement.setDate(6, Date.valueOf(usuario.getFechaNacimiento()));
      preparedStatement.setString(7, usuario.getSexo());

//      System.out.println(preparedStatement);

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        usuario.setIdUsuario(rs.getInt(1));
      }

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al agregar el usuario de correo " + usuario.getCorreo() + ".");
    }

    return usuario;

  }

  public AbsUsuario getUsuario(int idUsuario) throws SQLDBException {

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE id_usuario = ?";

    AbsUsuario usuario = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setInt(1, idUsuario);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        usuario = UsuarioFactory.buildUsuario();
        usuario.setIdUsuario(rs.getInt("id_usuario")).setNombre(rs.getString("nombre_usuario"))
            .setApellido(rs.getString("apellido")).setCorreo(rs.getString("correo"))
            .setContrasenia(rs.getString("contrasenia")).setTelefono(rs.getString("telefono"))
            .setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
            .setSexo(rs.getString("sexo"));
      }


    } catch (SQLException e) {
      throw new SQLDBException("Error al obtener el usuario con el id " + idUsuario + ".");
    }

    return usuario;

  }

  public AbsUsuario getUsuario(String correo) throws SQLDBException {

    String QUERY = "SELECT id_usuario, nombre_usuario, apellido, correo, contrasenia, telefono," +
        "fecha_nacimiento, sexo FROM usuario WHERE correo = ?";

    AbsUsuario usuario = null;

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

      preparedStatement.setString(1, correo);

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        usuario = UsuarioFactory.buildUsuario();
        usuario.setIdUsuario(rs.getInt("id_usuario")).setNombre(rs.getString("nombre_usuario"))
            .setApellido(rs.getString("apellido")).setCorreo(rs.getString("correo"))
            .setContrasenia(rs.getString("contrasenia")).setTelefono(rs.getString("telefono"))
            .setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
            .setSexo(rs.getString("sexo"));
      }


    } catch (SQLException e) {
      throw new SQLDBException("Error al obtener el usuario con el correo " + correo + ".");
    }

    return usuario;

  }

  public AbsUsuario updateUsuario(AbsUsuario usuario) throws SQLDBException {

    String UPDATE_USUARIO_SQL = "UPDATE usuario " +
        "SET nombre_usuario = ?, apellido = ?, contrasenia = ?, " +
        "telefono = ?, sexo = ? WHERE id_usuario = ?;";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL)) {

      preparedStatement.setString(1, usuario.getNombre());
      preparedStatement.setString(2, usuario.getApellido());
      preparedStatement.setString(3, usuario.getContrasenia());
      preparedStatement.setString(4, usuario.getTelefono());
      preparedStatement.setString(5, usuario.getSexo());
      preparedStatement.setInt(6, usuario.getIdUsuario());

//      System.out.println(preparedStatement);

      ResultSet rs = preparedStatement.getGeneratedKeys();

      if (rs.next()) {
        usuario.setIdUsuario(rs.getInt(1));
      }

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException(
          "Error al modificar el usuario con id " + usuario.getIdUsuario() + ".");
    }

    return usuario;

  }

  public boolean deleteUsuario(int idUsuario) throws SQLDBException {

    String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id_usuario = ?";

    try (Connection connection = DBCPDataSourceFactory.getMySQLDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL)) {

      preparedStatement.setInt(1, idUsuario);

//      System.out.println(preparedStatement);

      int filasAfectadas = preparedStatement.executeUpdate();

      System.out.println("Numero de filas afectadas: " + filasAfectadas);

    } catch (SQLException e) {
      throw new SQLDBException("Error al eliminar el usuario con id " + idUsuario + ".");
    }

    return true;

  }

}
