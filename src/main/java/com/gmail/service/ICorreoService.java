package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.excepciones.CorreoExcepcion;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.List;

public interface ICorreoService {

    AbsCorreo crear(CorreoDTO correo) throws CorreoExcepcion;

    AbsCorreo modificar(CorreoDTO correo) throws CorreoExcepcion ;

    AbsCorreo eliminarEnviado(int idCorreo) throws CorreoExcepcion;

    AbsCorreo eliminarRecibido(int idCorreo, int idUsuario) throws CorreoExcepcion;

    AbsCorreo obtenerEnviado(int idCorreo) throws CorreoExcepcion;

    AbsCorreo obtenerRecibido(int idCorreo, int idUsuario) throws CorreoExcepcion;

    List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado) throws CorreoExcepcion;

    List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado) throws CorreoExcepcion;

    AbsCorreo enviar(int idCorreo, int idUsuario) throws CorreoExcepcion;

}
