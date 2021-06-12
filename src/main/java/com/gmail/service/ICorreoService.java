package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.excepciones.CorreoExcepcion;
import com.gmail.exception.CorreoError;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.List;

public interface ICorreoService {

    AbsCorreo crear(CorreoDTO correo) throws CorreoExcepcion, SQLError, correoError, CorreoError;

    AbsCorreo modificar(CorreoDTO correo) throws CorreoExcepcion, correoError, CorreoError;

    AbsCorreo eliminarEnviado(int idCorreo) throws CorreoExcepcion, CorreoError;

    AbsCorreo eliminarRecibido(int idCorreo, int idUsuario) throws CorreoExcepcion, CorreoError;

    AbsCorreo obtenerEnviado(int idCorreo) throws CorreoExcepcion, CorreoError;

    AbsCorreo obtenerRecibido(int idCorreo, int idUsuario)
        throws CorreoExcepcion, CorreoError, SQLError;

    List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado)
        throws CorreoExcepcion, CorreoError, SQLError;

    List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
        throws CorreoExcepcion, CorreoError, SQLError;

    AbsCorreo enviar(int idCorreo, int idUsuario) throws CorreoExcepcion, CorreoError, SQLError;

}
