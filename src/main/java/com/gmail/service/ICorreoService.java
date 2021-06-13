package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoError;
import com.gmail.exception.CorreoError;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ICorreoService {

    AbsCorreo crear(CorreoDTO correo) throws SQLError, CorreoError;

    AbsCorreo modificar(CorreoDTO correo) throws  CorreoError;

    AbsCorreo eliminarEnviado(int idCorreo) throws CorreoError;

    AbsCorreo eliminarRecibido(int idCorreo, int idUsuario) throws CorreoError;

    AbsCorreo obtenerEnviado(int idCorreo) throws CorreoError;

    AbsCorreo obtenerRecibido(int idCorreo, int idUsuario)
        throws CorreoError, SQLError;

    List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado)
        throws CorreoError, SQLError;

    List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
        throws CorreoError, SQLError;


    AbsCorreo enviar(int idCorreo, int idUsuario) throws CorreoError, SQLError ;

    AbsCorreo enviar(int idCorreo, int[] idUsuario) throws CorreoError;

    List<AbsCorreo> obtenerImportantes(int idUsuario)
            throws CorreoError, SQLError;

    List<AbsCorreo> obtenerDestacados(int idUsuario)
            throws CorreoError, SQLError;

    List<AbsCorreo> obtenerBorrados(int idUsuario)
            throws CorreoError, SQLError;
    List<AbsCorreo> obtenerNoLeidos(int idUsuario)
            throws CorreoError, SQLError;

    AbsCorreo leeCorreo(int idCorreo, int idUsuario) throws CorreoError;

}
