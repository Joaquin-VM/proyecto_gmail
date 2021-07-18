package com.gmail.service.impl;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.impl.EtiquetaFactory;
import com.gmail.service.IEtiquetaService;
import java.util.List;

public class EtiquetaService implements IEtiquetaService {

  private final EtiquetaDAO dao = new EtiquetaDAO();

  @Override
  public AbsEtiqueta crear(EtiquetaDTO dto)
      throws ValidationException, NotFoundException, OperationException {

    if (!validarDatos(dto)) {
      throw new ValidationException("Los datos ingresados de etiqueta son invalidos.");
    }

    try {

      return dao.addEtiqueta(EtiquetaFactory.buildEtiqueta(dto));

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsEtiqueta obtenerUna(int idEtiqueta)
      throws NotFoundException, OperationException {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("La etiqueta a obtener no existe.");
    }

    try {

      return dao.getEtiqueta(idEtiqueta);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsEtiqueta> obtenerCoincidentes(String texto, int idUsuario)
      throws ValidationException, NotFoundException, OperationException {

    if (texto.isBlank()) {
      throw new ValidationException("El texto no puede estar vacio.");
    }

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("No existe usuario con el id ingresado.");
    }

    try {

      return dao.getEtiquetasCoincidentes(texto, idUsuario);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario)
      throws NotFoundException, OperationException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("No existe usuario con el id ingresado.");
    }

    try {

      return dao.listarEtiquetasUsuario(idUsuario);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsEtiqueta modificar(EtiquetaDTO etiquetaModificada)
      throws ValidationException, NotFoundException, OperationException {

    if (!existeEtiqueta(etiquetaModificada.getIdEtiqueta())) {
      throw new NotFoundException("La etiqueta a modificar no existe.");
    }

    if (!validarDatos(etiquetaModificada)) {
      throw new ValidationException("Los datos ingresados de etiqueta son invalidos.");
    }

    try {

      EtiquetaDTO etiquetaGuardada = new EtiquetaDTO(
          obtenerUna(etiquetaModificada.getIdEtiqueta()));

      if (etiquetaModificada.getNombreEtiqueta() != null) {
        etiquetaGuardada.setNombreEtiqueta(etiquetaModificada.getNombreEtiqueta());
      }

      dao.updateEtiqueta(EtiquetaFactory.buildEtiqueta(etiquetaGuardada));

      return EtiquetaFactory.buildEtiqueta(etiquetaGuardada);

    } catch (NotFoundException | OperationException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsEtiqueta eliminar(int idEtiqueta)
      throws NotFoundException, OperationException {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("La etiqueta a eliminar no existe.");
    }

    try {

      AbsEtiqueta etiquetaAEliminar = obtenerUna(idEtiqueta);

      dao.deleteEtiqueta(idEtiqueta);

      return etiquetaAEliminar;

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsEtiqueta agregarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws NotFoundException, OperationException {

    if (!existeCorreo(idCorreo)) {
      throw new NotFoundException("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    try {

      dao.agregarEtiquetaACorreo(idCorreo, idEtiqueta);

      return dao.getEtiqueta(idEtiqueta);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsEtiqueta quitarEtiquetaACorreo(int idCorreo, int idEtiqueta, int idUsuario)
      throws NotFoundException, OperationException {

    if (!existeEtiquetaEnCorreo(idCorreo, idEtiqueta, idUsuario)) {
      throw new NotFoundException("No existe la etiqueta de id " + idEtiqueta +
          " en el correo con id " + idCorreo + ".");
    }

    if (!existeCorreo(idCorreo)) {
      throw new NotFoundException("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    try {

      final EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

      etiquetaDAO.quitarEtiquetaACorreo(idCorreo, idEtiqueta);

      return dao.getEtiqueta(idEtiqueta);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo, int idUsuario)
      throws NotFoundException, OperationException {

    if (!existeCorreo(idCorreo)) {
      throw new NotFoundException("El correo con el id ingresado no existe.");
    }

    try {

      return dao.obtenerEtiquetasDeCorreo(idCorreo, idUsuario);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  private boolean existeEtiqueta(int idEtiqueta) throws NotFoundException {

    if (idEtiqueta <= 0) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    try {

      return dao.getEtiqueta(idEtiqueta) != null;

    } catch (SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }

  }

  private boolean existeUsuario(int idUsuario) throws NotFoundException {

    if (idUsuario <= 0) {
      throw new NotFoundException("El usuario con el id ingresado no existe.");
    }

    try {

      final UsuarioDAO usuarioDAO = new UsuarioDAO();

      return usuarioDAO.getUsuario(idUsuario) != null;

    } catch (SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }

  }

  private boolean existeCorreo(int idCorreo) throws NotFoundException {

    if (idCorreo <= 0) {
      throw new NotFoundException("El correo con el id ingresado no existe.");
    }

    try {

      final CorreoDAO correoDAO = new CorreoDAO();
      return correoDAO.getCorreo(idCorreo) != null;

    } catch (SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }

  }

  private boolean existeEtiquetaEnCorreo(int idCorreo, int idEtiqueta, int idUsuario)
      throws OperationException {

    try {

      for (AbsEtiqueta i : dao.obtenerEtiquetasDeCorreo(idCorreo, idUsuario)) {
        if (i.getIdEtiqueta() == idEtiqueta) {
          return true;
        }
      }

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

    return false;

  }

  private boolean validarDatos(EtiquetaDTO dto)
      throws NotFoundException, ValidationException {

    if (dto.getNombreEtiqueta().isBlank()) {
      throw new ValidationException("El nombre de etiqueta ingresado es invalido.");
    }

    if (!existeUsuario(dto.getIdUsuario())) {
      throw new NotFoundException("El usuario con el id ingresado no existe.");
    }

    return true;

  }

}
