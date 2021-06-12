package com.gmail.service;

import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.SQLError;
import com.gmail.exception.ValidationError;
import com.gmail.model.AbsUsuario;
import java.sql.SQLException;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario) throws ValidationError;

  AbsUsuario modificar(int idUsuario, UsuarioDTO usuarioModificado)
      throws ValidationError, SQLException;

  boolean eliminar(int idUsuario) throws SQLError;

  AbsUsuario obtenerUno(int idUsuario) throws SQLError;

}
