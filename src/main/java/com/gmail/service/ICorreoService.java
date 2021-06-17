package com.gmail.service;

import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import java.util.List;

public interface ICorreoService {

  AbsCorreo crear(CorreoDTO correo) throws SQLDBException, CorreoException;

  AbsCorreo modificar(CorreoDTO correo) throws CorreoException, SQLDBException;

  AbsCorreo eliminarEnviado(int idCorreo) throws CorreoException, SQLDBException;

  AbsCorreo eliminarRecibido(int idCorreo, int idUsuario) throws CorreoException, SQLDBException;

  AbsCorreo obtenerEnviado(int idCorreo) throws CorreoException, SQLDBException;

  AbsCorreo obtenerRecibido(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException;

  List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado)
      throws CorreoException, SQLDBException;

  List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
      throws CorreoException, SQLDBException;


  AbsCorreo enviar(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, CloneNotSupportedException;

  AbsCorreo enviar(int idCorreo, int[] idUsuario)
      throws CorreoException, CloneNotSupportedException, SQLDBException;

  List<AbsCorreo> obtenerImportantes(int idUsuario)
      throws CorreoException, SQLDBException;

  List<AbsCorreo> obtenerDestacados(int idUsuario)
      throws CorreoException, SQLDBException;

  List<AbsCorreo> obtenerBorrados(int idUsuario)
      throws CorreoException, SQLDBException;

  List<AbsCorreo> obtenerNoLeidos(int idUsuario)
      throws CorreoException, SQLDBException;

  AbsCorreo leeCorreo(int idCorreo, int idUsuario) throws CorreoException, SQLDBException;

}
