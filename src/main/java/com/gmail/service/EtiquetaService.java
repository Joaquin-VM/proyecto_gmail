package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.SQLError;
import com.gmail.exception.ValidationError;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.EtiquetaFactory;
import java.util.List;

public class EtiquetaService implements IEtiquetaService {

  private EtiquetaDAO dao = new EtiquetaDAO();

  @Override
  public AbsEtiqueta crear(EtiquetaDTO dto) throws ValidationError, SQLError {

    if (!validarDatos(dto)) {
      throw new ValidationError("Los datos ingresados de etiqueta son invalidos.");
    }

    return dao.addEtiqueta(EtiquetaFactory.buildEtiqueta(dto));

  }

  @Override
  public AbsEtiqueta obtenerUna(int idEtiqueta) throws SQLError {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new SQLError("La etiqueta a obtener no existe.");
    }

    return dao.getEtiqueta(idEtiqueta);

  }

  @Override
  public List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLError, ValidationError {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    if (nombreEtiqueta.isBlank()) {
      throw new ValidationError("El nombre de la etiqueta no puede estar vacio.");
    }

    if (!existeUsuario(idUsuario)) {
      throw new SQLError("No existe usuario con el id ingresado.");
    }

    return dao.getEtiquetasCoincidentes(nombreEtiqueta, idUsuario);

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) throws SQLError {

    if (!existeUsuario(idUsuario)) {
      throw new SQLError("No existe usuario con el id ingresado.");
    }

    return dao.listarEtiquetasUsuario(idUsuario);

  }

  @Override
  public boolean modificar(int idEtiqueta, EtiquetaDTO etiquetaModificada)
      throws ValidationError, SQLError {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new SQLError("La etiqueta a modificar no existe.");
    }

    if (!validarDatos(etiquetaModificada)) {
      throw new ValidationError("Los datos ingresados de etiqueta son invalidos.");
    }

    return dao.updateEtiqueta(EtiquetaFactory.buildEtiqueta(etiquetaModificada));

  }

  @Override
  public boolean eliminar(int idEtiqueta) throws SQLError, ValidationError {

    if (!existeEtiqueta(idEtiqueta)) {
      throw new SQLError("La etiqueta a eliminar no existe.");
    }

    return dao.deleteEtiqueta(idEtiqueta);

  }

  @Override
  public boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLError {

    if (!existeCorreo(idCorreo)) {
      throw new SQLError("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new SQLError("No existe etiqueta con el id ingresado.");
    }

    return agregarEtiquetaACorreo(idCorreo, idEtiqueta);

  }

  @Override
  public boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLError {

    if (!existeEtiquetaEnCorreo(idCorreo, idEtiqueta)) {
      throw new SQLError("No existe la etiqueta de id " + idEtiqueta +
          " en el correo con id " + idCorreo + ".");
    }

    if (!existeCorreo(idCorreo)) {
      throw new SQLError("No existe correo con el id ingresado.");
    }

    if (!existeEtiqueta(idEtiqueta)) {
      throw new SQLError("No existe etiqueta con el id ingresado.");
    }

    return quitarEtiquetaACorreo(idCorreo, idEtiqueta);

  }

  @Override
  public List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo) throws SQLError {

    if(existeCorreo(idCorreo)){
      throw new SQLError("El correo con el id ingresado no existe.");
    }

    return dao.obtenerEtiquetasDeCorreo(idCorreo);

  }

  private boolean existeEtiqueta(int idEtiqueta) throws SQLError {
    if (idEtiqueta <= 0) {
      return false;
    }

    return dao.getEtiqueta(idEtiqueta) != null;
  }

  private boolean existeUsuario(int idUsuario) throws SQLError {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    if (idUsuario <= 0) {
      return false;
    }

    return usuarioDAO.getUsuario(idUsuario) != null;

  }

  private boolean existeCorreo(int idCorreo) throws SQLError {

    CorreoDAO correoDAO = new CorreoDAO();

    if (idCorreo <= 0) {
      return false;
    }

    return correoDAO.getCorreo(idCorreo) != null;

  }

  private boolean existeEtiquetaEnCorreo(int idCorreo, int idEtiqueta) throws SQLError {

    for (AbsEtiqueta i : dao.obtenerEtiquetasDeCorreo(idCorreo)) {
      if (idEtiqueta == i.getIdEtiqueta()) {
        return true;
      }
    }

    return false;

  }

  private boolean validarDatos(EtiquetaDTO dto) throws SQLError {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    if (dto.getNombreEtiqueta().isBlank()) {
      return false;
    }

    if (existeUsuario(dto.getIdUsuario())) {
      return false;
    }

    return true;

  }

}
