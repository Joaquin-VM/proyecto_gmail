package com.gmail.service;

import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario) throws ValidationException, SQLDBException, NotFoundException;

  AbsUsuario obtenerUno(int idUsuario) throws SQLDBException, NotFoundException;

  AbsUsuario obtenerUno(String correo) throws SQLDBException, NotFoundException;

  AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws ValidationException, java.sql.SQLException, NotFoundException;

  boolean eliminar(int idUsuario) throws SQLDBException, NotFoundException;

}
