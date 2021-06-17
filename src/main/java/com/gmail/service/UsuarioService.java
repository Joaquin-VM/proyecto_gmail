package com.gmail.service;

import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;
import com.gmail.model.UsuarioFactory;

public class UsuarioService implements IUsuarioService {

  private UsuarioDAO dao = new UsuarioDAO();

  @Override
  public AbsUsuario crear(UsuarioDTO dto) throws ValidationException, SQLDBException {

    if (!validarDatos(dto)) {
      throw new ValidationException("Los datos ingresados de usuario son invalidos.");
    }

    if (existeUsuario(dto.getCorreo())) {
      throw new SQLDBException(
          "El usuario a agregar tiene un correo que ya ha sido elegido por otro usuario.");
    }

    return dao.addUsuario(UsuarioFactory.buildUsuario(dto));

  }

  @Override
  public AbsUsuario obtenerUno(int idUsuario) throws SQLDBException {

    if (!existeUsuario(idUsuario)) {
      throw new SQLDBException("El usuario a obtener no existe.");
    }

    return dao.getUsuario(idUsuario);

  }

  @Override
  public AbsUsuario obtenerUno(String correo) throws SQLDBException {

    if (!existeUsuario(correo)) {
      throw new SQLDBException("El usuario a obtener no existe.");
    }

    return dao.getUsuario(correo);

  }

  @Override
  public AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws ValidationException, SQLDBException {

    if (!existeUsuario(usuarioModificado.getIdUsuario())) {
      throw new SQLDBException("El usuario a modificar no existe.");
    }

    if (!validarDatos(usuarioModificado)) {
      throw new ValidationException("Los datos ingresados de usuario son invalidos.");
    }

    return dao.updateUsuario(UsuarioFactory.buildUsuario(usuarioModificado));

  }

  @Override
  public boolean eliminar(int idUsuario) throws SQLDBException {

    if (!existeUsuario(idUsuario)) {
      throw new SQLDBException("El usuario a eliminar no existe.");
    }

    return dao.deleteUsuario(idUsuario);

  }


  private boolean existeUsuario(int idUsuario) throws SQLDBException {

    if (idUsuario <= 0) {
      throw new SQLDBException("El usuario a eliminar no existe.");
    }

    return dao.getUsuario(idUsuario) != null;

  }

  private boolean existeUsuario(String correo) throws SQLDBException {

    if (correo.isBlank()) {
      throw new SQLDBException("El usuario a eliminar no existe.");
    }

    return dao.getUsuario(correo) != null;

  }

  private boolean validarDatos(UsuarioDTO dto) {

    if (dto == null) {
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
