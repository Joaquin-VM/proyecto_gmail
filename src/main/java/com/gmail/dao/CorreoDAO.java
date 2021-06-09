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
            if(correo.getConfirmado())
                preparedStatement.setInt(4, 1);
            else
                preparedStatement.setInt(4, 0);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next())
                correo.setIdCorreo(rs.getInt(1));


        } catch (SQLException e) {
            System.out.println(e);
        }

        return correo;
    }

    public static Correo getCorreo(int idCorreo){

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

            while(rs.next()){
                correo = new Correo();
                correo.setIdCorreo(rs.getInt("id_correo"));
                correo.setAsunto(rs.getString("asunto"));
                correo.setCuerpo(rs.getString("cuerpo"));
                correo.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());

                if(rs.getInt("confirmado")==1)
                    correo.setConfirmado(true);
                else
                    correo.setConfirmado(false);
            }


        }catch(SQLException e){
            System.out.println(e);
        }


        return correo;

    }

    public static List<Correo> getCorreosRecibidos(int idUsuario){

        String QUERY = "SELECT * FROM correo c " +
                "INNER JOIN enviar e ON c.id_correo = e.id_correo" +
                "INNER JOIN borrar b ON b.id_correo = c.id_correo" +
                "WHERE c.confirmado = 1 AND b.borrado = 0 AND e.id_usuario_2 =  ? ";

        List<Correo> correos = null;
        Correo correo = null;

        try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
                JDBCUtil.getUsuario(), JDBCUtil.getClave());
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, idUsuario);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                correos = new ArrayList<>();
                correo = new Correo();
                correo.setIdCorreo(rs.getInt("id_correo"));
                correo.setAsunto(rs.getString("asunto"));
                correo.setCuerpo(rs.getString("cuerpo"));
                correo.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                correo.setConfirmado(true);
                correos.add(correo);
            }


        }catch(SQLException e){
            System.out.println(e);
        }


        return correos;

    }

    public static boolean updateCorreo(Correo correo){
        String UPDATE_CORREO_SQL="UPDATE correo" +
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
            if(correo.getConfirmado())
                preparedStatement.setInt(4, 1);
            else
                preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, correo.getIdCorreo());

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();


        }catch (SQLException e){
            System.out.println(e);
            return false;
        }

        return true;

    }

    public static boolean deleteCorreo(int idCorreo,int idUsuario){

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

            ResultSet rs = preparedStatement.executeQuery();


        }catch (SQLException e){
            System.out.println(e);
            return false;
        }

        return true;

    }
    public static boolean deleteCorreo(int idCorreo){

        String DELETE_CORREO_SQL = "DELETE FROM correo WHERE id_correo = ?";


        try (Connection connection = DriverManager.getConnection(JDBCUtil.getURL(),
                JDBCUtil.getUsuario(), JDBCUtil.getClave());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CORREO_SQL)) {

            preparedStatement.setInt(1, idCorreo);

            System.out.println(preparedStatement);

            int result = preparedStatement.executeUpdate();

            System.out.println("Numero de registros afectados: " + result);

            ResultSet rs = preparedStatement.executeQuery();


        }catch (SQLException e){
            System.out.println(e);
            return false;
        }

        return true;

    }
}
