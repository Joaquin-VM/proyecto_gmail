package com.gmail.service.impl;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.impl.EtiquetaFactory;
import com.gmail.service.IEtiquetaService;
import java.util.List;

public class EtiquetaService implements IEtiquetaService {

  private EtiquetaDAO dao = new EtiquetaDAO();

  @Override
  public AbsEtiqueta crear(EtiquetaDTO dto)
      throws ValidationException, SQLDBException, NotFoundException {

    if (!validarDatos(dto)) {
      throw new ValidationException("Los datos ingresados de etiqueta son invalidos.");
    }

    return dao.addEtiqueta(EtiquetaFactory.buildEtiqueta(dto));

  }

  @Override
  public AbsEtiqueta obtenerUna(int idEtiqueta) throws SQLDBException, NotFoundException {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("La etiqueta a obtener no existe.");
    }

    return dao.getEtiqueta(idEtiqueta);

  }

  @Override
  public List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLDBException, ValidationException, NotFoundException {

    if (nombreEtiqueta.isBlank()) {
      throw new ValidationException("El nombre de la etiqueta no puede estar vacio.");
    }

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("No existe usuario con el id ingresado.");
    }

    return dao.getEtiquetasCoincidentes(nombreEtiqueta, idUsuario);

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario)
      throws SQLDBException, NotFoundException {

    if (!existeUsuario(idUsuario)) {
      throw new NotFoundException("No existe usuario con el id ingresado.");
    }

    return dao.listarEtiquetasUsuario(idUsuario);

  }

  @Override
  public boolean modificar(EtiquetaDTO etiquetaModificada)
      throws ValidationException, SQLDBException, NotFoundException {

    if (!existeEtiqueta(etiquetaModificada.getIdEtiqueta())) {
      throw new NotFoundException("La etiqueta a modificar no existe.");
    }

    if (!validarDatos(etiquetaModificada)) {
      throw new ValidationException("Los datos ingresados de etiqueta son invalidos.");
    }

    return dao.updateEtiqueta(EtiquetaFactory.buildEtiqueta(etiquetaModificada));

  }

  @Override
  public boolean eliminar(int idEtiqueta)
      throws SQLDBException, NotFoundException {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("La etiqueta a eliminar no existe.");
    }

    return dao.deleteEtiqueta(idEtiqueta);

  }

  @Override
  public boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws NotFoundException, SQLDBException {

    if (!existeCorreo(idCorreo)) {
      throw new NotFoundException("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    return dao.agregarEtiquetaACorreo(idCorreo, idEtiqueta);

  }

  @Override
  public boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws SQLDBException, NotFoundException {

    if (!existeEtiquetaEnCorreo(idCorreo, idEtiqueta)) {
      throw new NotFoundException("No existe la etiqueta de id " + idEtiqueta +
          " en el correo con id " + idCorreo + ".");
    }

    if (!existeCorreo(idCorreo)) {
      throw new NotFoundException("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    return quitarEtiquetaACorreo(idCorreo, idEtiqueta);

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo)
      throws SQLDBException, NotFoundException {

    if (existeCorreo(idCorreo)) {
      throw new NotFoundException("El correo con el id ingresado no existe.");
    }

    return dao.obtenerEtiquetasDeCorreo(idCorreo);

  }

  private boolean existeEtiqueta(int idEtiqueta) throws SQLDBException, NotFoundException {

    if (idEtiqueta <= 0) {
      throw new NotFoundException("No existe etiqueta con el id ingresado.");
    }

    return dao.getEtiqueta(idEtiqueta) != null;
  }

  private boolean existeUsuario(int idUsuario) throws SQLDBException, NotFoundException {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    if (idUsuario <= 0) {
      throw new NotFoundException("El usuario con el id ingresado no existe.");
    }

    return usuarioDAO.getUsuario(idUsuario) != null;

  }

  private boolean existeCorreo(int idCorreo) throws SQLDBException, NotFoundException {

    CorreoDAO correoDAO = new CorreoDAO();

    if (idCorreo <= 0) {
      throw new NotFoundException("El correo con el id ingresado no existe.");
    }

    return correoDAO.getCorreo(idCorreo) != null;

  }

  private boolean existeEtiquetaEnCorreo(int idCorreo, int idEtiqueta) throws SQLDBException {

    for (AbsEtiqueta i : dao.obtenerEtiquetasDeCorreo(idCorreo)) {
      if (idEtiqueta == i.getIdEtiqueta()) {
        return true;
      }
    }

    return false;

  }

  private boolean validarDatos(EtiquetaDTO dto)
      throws SQLDBException, NotFoundException, ValidationException {

    if (dto.getNombreEtiqueta().isBlank()) {
      throw new ValidationException("El nombre de etiqueta ingresado es invalido.");
    }

    if (!existeUsuario(dto.getIdUsuario())) {
      throw new NotFoundException("El usuario con el id ingresado no existe.");
    }

    return true;

  }

}
