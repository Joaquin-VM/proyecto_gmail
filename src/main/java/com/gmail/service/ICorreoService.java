package com.gmail.service;

import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import java.util.List;

public interface ICorreoService {

  AbsCorreo crear(CorreoDTO correo) throws SQLDBException, CorreoException, OperationException;

  AbsCorreo modificar(CorreoDTO correo) throws CorreoException, SQLDBException, OperationException;

  AbsCorreo eliminarEnviado(int idCorreo)
      throws CorreoException, SQLDBException, OperationException;

  AbsCorreo eliminarRecibido(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  AbsCorreo obtenerEnviado(int idCorreo) throws CorreoException, SQLDBException, OperationException;

  AbsCorreo obtenerRecibido(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  List<AbsCorreo> obtenerRecibidos(int idUsuario)
      throws OperationException;

  List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
      throws CorreoException, SQLDBException, OperationException;

  AbsCorreo enviar(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, CloneNotSupportedException, OperationException;

  AbsCorreo enviar(int idCorreo, int[] idUsuario)
      throws CorreoException, CloneNotSupportedException, SQLDBException, OperationException;

  AbsCorreo reeEnviar(int idCorreo, int idUsuarioEmisor, int idUsuarioReceptor)
      throws CorreoException, CloneNotSupportedException, OperationException;

  List<AbsCorreo> obtenerImportantes(int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  List<AbsCorreo> obtenerDestacados(int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  List<AbsCorreo> obtenerBorrados(int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  List<AbsCorreo> obtenerLeidos(int idUsuario)
      throws OperationException;

  List<AbsCorreo> obtenerNoLeidos(int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

  List<AbsCorreo> obtenerSpam(int idUsuario)
      throws OperationException;

  AbsCorreo leerCorreo(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, OperationException;

}
