package com.gmail.service;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.ValidationError;
import com.gmail.model.AbsUsuario;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario) throws ValidationError;

  AbsUsuario modificar(int idUsuario, UsuarioDTO usuario) throws ValidationError;

  boolean eliminar(int idUsuario);

  AbsUsuario obtenerUno(int idUsuario);

}
