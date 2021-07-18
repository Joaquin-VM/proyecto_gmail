package com.gmail.service.impl;

import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.UsuarioFactory;
import com.gmail.service.IUsuarioService;

public class UsuarioService implements IUsuarioService {

  private final UsuarioDAO dao = new UsuarioDAO();

  @Override
  public AbsUsuario crear(UsuarioDTO dto)
      throws ValidationException, NotFoundException, OperationException {

    if (!validarDatos(dto)) {
      throw new ValidationException("Los datos ingresados de usuario son invalidos.");
    }

    if (existeUsuario(dto.getCorreo())) {
      throw new ValidationException(
          "El usuario a agregar tiene un correo que ya ha sido elegido por otro usuario.");
    }

    try {

      return dao.addUsuario(UsuarioFactory.buildUsuario(dto));

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsUsuario obtenerUno(int idUsuario) throws NotFoundException, OperationException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("El usuario a obtener no existe.");
    }

    try {

      return dao.getUsuario(idUsuario);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsUsuario obtenerUno(String correo)
      throws NotFoundException, OperationException {

    if (!existeUsuario(correo)) {
      throw new NotFoundException("El usuario a obtener no existe.");
    }

    try {

      return dao.getUsuario(correo);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsUsuario modificar(UsuarioDTO usuarioModificado)
      throws OperationException {

    try {

      UsuarioDTO usuarioGuardado = new UsuarioDTO(dao.getUsuario(usuarioModificado.getIdUsuario()));

      if (usuarioModificado.getNombre() != null) {
        usuarioGuardado.setNombre(usuarioModificado.getNombre());
      }

      if (usuarioModificado.getApellido() != null) {
        usuarioGuardado.setApellido(usuarioModificado.getApellido());
      }

      if (usuarioModificado.getContrasenia() != null) {
        usuarioGuardado.setContrasenia(usuarioModificado.getContrasenia());
      }

      if (usuarioModificado.getSexo() != null) {
        usuarioGuardado.setSexo(usuarioModificado.getSexo());
      }

      if (usuarioModificado.getTelefono() != null) {
        usuarioGuardado.setTelefono(usuarioModificado.getTelefono());
      }

      if (!validarDatos(usuarioGuardado)) {
        throw new ValidationException("Los datos ingresados de usuario son invalidos.");
      }

      return dao.updateUsuario(UsuarioFactory.buildUsuario(usuarioGuardado));

    } catch (ValidationException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsUsuario eliminar(int idUsuario) throws NotFoundException, OperationException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("El usuario a eliminar no existe.");
    }

    try {

      AbsUsuario usuario = dao.getUsuario(idUsuario);

      dao.deleteUsuario(idUsuario);

      return usuario;

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsUsuario eliminar(String correoUsuario) throws NotFoundException, OperationException {

    if (!existeUsuario(correoUsuario)) {
      throw new NotFoundException("El usuario a eliminar no existe.");
    }

    try {

      AbsUsuario usuario = dao.getUsuario(correoUsuario);

      dao.deleteUsuario(correoUsuario);

      return usuario;

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  private boolean existeUsuario(int idUsuario) throws NotFoundException {

    if (idUsuario <= 0) {
      throw new NotFoundException("El usuario con id " + idUsuario + " no existe.");
    }

    try {

      return dao.getUsuario(idUsuario) != null;

    } catch (SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }

  }

  private boolean existeUsuario(String correo) throws NotFoundException {

    if (correo.isBlank()) {
      throw new NotFoundException("El correo ingresado esta vacio.");
    }

    try {

      return dao.getUsuario(correo) != null;

    } catch (SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }


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
