package com.gmail.service;

import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.SQLError;
import com.gmail.exception.ValidationError;
import com.gmail.model.AbsUsuario;
import com.gmail.model.UsuarioFactory;

public class UsuarioService implements IUsuarioService {

  private UsuarioDAO dao = new UsuarioDAO();

  @Override
  public AbsUsuario crear(UsuarioDTO dto) throws ValidationError {

    if (!validarDatos(dto)) {
      throw new ValidationError("Los datos ingresados son invalidos");
    }

    return dao.addUsuario(UsuarioFactory.buildUsuario(dto));

  }

  @Override
  public AbsUsuario modificar(int idUsuario, UsuarioDTO usuarioModificado)
      throws ValidationError, SQLError {

    if (!validarDatos(usuarioModificado)) {
      throw new ValidationError("Los datos ingresados son invalidos");
    }

    UsuarioDAO.updateUsuario(usuarioModificado);

    return dao.getUsuario(idUsuario);

  }

  @Override
  public boolean eliminar(int idUsuario) throws SQLError {
    return dao.deleteUsuario(idUsuario);
  }

  @Override
  public AbsUsuario obtenerUno(int idUsuario) throws SQLError {
    return dao.getUsuario(idUsuario);
  }

  private boolean validarDatos(UsuarioDTO dto) {

    if (dto == null) {
      return false;
    }

    if (dto.getIdUsuario() < 1) {
      return false;
    }

    if (dto.getNombre().isBlank()) {
      return false;
    }

    if (dto.getApellido().isBlank()) {
      return false;
    }

    if (dto.getCorreo().isBlank()) {
      return false;
    }

    if (dto.getContrasenia().isBlank()) {
      return false;
    }

    if (dto.getTelefono().isBlank()) {
      return false;
    }

    if (dto.getSexo().isBlank()) {
      return false;
    }

    return true;

  }

}
