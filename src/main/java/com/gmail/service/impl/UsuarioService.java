package com.gmail.service.impl;

import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.UsuarioFactory;
import com.gmail.service.IUsuarioService;

public class UsuarioService implements IUsuarioService {

  private UsuarioDAO dao = new UsuarioDAO();

  @Override
  public AbsUsuario crear(UsuarioDTO dto)
      throws ValidationException, SQLDBException, NotFoundException {

    if (!validarDatos(dto)) {
      throw new ValidationException("Los datos ingresados de usuario son invalidos.");
    }

    if (existeUsuario(dto.getCorreo())) {
      throw new ValidationException(
          "El usuario a agregar tiene un correo que ya ha sido elegido por otro usuario.");
    }

    return dao.addUsuario(UsuarioFactory.buildUsuario(dto));

  }

  @Override
  public AbsUsuario obtenerUno(int idUsuario) throws SQLDBException, NotFoundException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("El usuario a obtener no existe.");
    }

    return dao.getUsuario(idUsuario);

  }

  @Override
  public AbsUsuario obtenerUno(String correo) throws SQLDBException, NotFoundException {

    if (!existeUsuario(correo)) {
      throw new NotFoundException("El usuario a obtener no existe.");
    }

    return dao.getUsuario(correo);

  }

  @Override
  public AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws ValidationException, SQLDBException, NotFoundException {

    if (!existeUsuario(usuarioModificado.getIdUsuario())) {
      throw new NotFoundException("El usuario a modificar no existe.");
    }

    if (!validarDatos(usuarioModificado)) {
      throw new ValidationException("Los datos ingresados de usuario son invalidos.");
    }

    return dao.updateUsuario(UsuarioFactory.buildUsuario(usuarioModificado));

  }

  @Override
  public boolean eliminar(int idUsuario) throws NotFoundException, SQLDBException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("El usuario a eliminar no existe.");
    }

    return dao.deleteUsuario(idUsuario);

  }


  private boolean existeUsuario(int idUsuario) throws SQLDBException, NotFoundException {

    if (idUsuario <= 0) {
      throw new NotFoundException("El usuario a eliminar no existe.");
    }

    return dao.getUsuario(idUsuario) != null;

  }

  private boolean existeUsuario(String correo) throws SQLDBException, NotFoundException {

    if (correo.isBlank()) {
      throw new NotFoundException("El usuario a eliminar no existe.");
    }

    return dao.getUsuario(correo) != null;

  }

  private boolean validarDatos(UsuarioDTO dto) throws ValidationException {

    if (dto == null) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: null.");
    }

    if (dto.getNombre().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: nombre.");
    }

    if (dto.getApellido().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: apellido.");
    }

    if (dto.getCorreo().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: correo.");
    }

    if (dto.getContrasenia().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: contrasenia.");
    }

    if (dto.getTelefono().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: telefono.");
    }

    if (dto.getSexo().isBlank()) {
      throw new ValidationException("Los datos del usuario ingresado son invalidos: sexo.");
    }

    return true;

  }

}
