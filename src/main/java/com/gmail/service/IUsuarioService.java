package com.gmail.service;

import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario) throws ValidationException, SQLDBException;

  AbsUsuario obtenerUno(int idUsuario) throws SQLDBException;

  AbsUsuario obtenerUno(String correo) throws SQLDBException;

  AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws ValidationException, java.sql.SQLException;

  boolean eliminar(int idUsuario) throws SQLDBException;

}
