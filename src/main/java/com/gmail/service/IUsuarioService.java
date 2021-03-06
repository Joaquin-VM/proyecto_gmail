package com.gmail.service;

import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario)
      throws ValidationException, SQLDBException, NotFoundException, OperationException;

  AbsUsuario obtenerUno(int idUsuario) throws SQLDBException, NotFoundException, OperationException;

  AbsUsuario obtenerUno(String correo) throws SQLDBException, NotFoundException, OperationException;

  AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws ValidationException, java.sql.SQLException, NotFoundException, OperationException;

  AbsUsuario eliminar(int idUsuario) throws SQLDBException, NotFoundException, OperationException;

  AbsUsuario eliminar(String correoUsuario) throws NotFoundException, OperationException;
}
