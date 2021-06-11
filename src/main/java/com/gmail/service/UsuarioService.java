package com.gmail.service;

import com.gmail.dao.UsuarioDAO;
import com.gmail.model.AbsUsuario;
import java.util.List;

public class UsuarioService implements IUsuarioService {

  @Override
  public AbsUsuario crear(AbsUsuario usuario) {
    if(!validarDatos(usuario)){
      return null;
    }
    return UsuarioDAO.addUsuario(usuario);
  }

  @Override
  public AbsUsuario modificar(int id, AbsUsuario usuarioModificado) {

    if(!validarDatos(usuarioModificado)){
      return null;
    }

    if(!UsuarioDAO.updateUsuario(usuarioModificado)){
      return null;
    }

    return UsuarioDAO.getUsuario(usuarioModificado.getIdUsuario());

  }

  @Override
  public void eliminar(int id) {

  }

  @Override
  public AbsUsuario obtenerUno(int id) {
    return null;
  }

  @Override
  public List<AbsUsuario> obtenerLista(int id) {
    return null;
  }

  private boolean validarDatos(AbsUsuario usuario) {

    if (usuario == null) {
      return false;
    }

    if (usuario.getNombre().isBlank()) {
      return false;
    }

    if (usuario.getApellido().isBlank()) {
      return false;
    }

    if (usuario.getCorreo().isBlank()) {
      return false;
    }

    if (usuario.getContrasenia().isBlank()) {
      return false;
    }

    if (usuario.getTelefono().isBlank()) {
      return false;
    }

    if (usuario.getSexo().isBlank()) {
      return false;
    }

    return true;

  }

}
