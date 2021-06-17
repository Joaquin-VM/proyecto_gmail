package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsFiltro;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoService implements ICorreoService {

  CorreoDAO dao = new CorreoDAO();
  UsuarioDAO usuarioDAO = new UsuarioDAO();
  FiltroDAO filtroDAO = new FiltroDAO();
  EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

  @Override
  public AbsCorreo crear(CorreoDTO correo) throws CorreoException, SQLDBException {

    correo = cargarNulls(correo);

    if (usuarioDAO.getUsuario(correo.getIdUsuario()) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + correo.getIdUsuario());
    }

    correo.setFechaHora(LocalDateTime.now());

    return dao.addCorreo(CorreoFactory.buildCorreo(correo));
  }

  @Override
  public AbsCorreo modificar(CorreoDTO correo) throws CorreoException, SQLDBException {

    correo = cargarNulls(correo);

    AbsCorreo correoGuardado = dao.getCorreo(correo.getIdCorreo());

    if (correoGuardado == null) {
      throw new CorreoException(1, correo.getIdCorreo());
    }

    if (correoGuardado.getConfirmado()) {
      throw new CorreoException(3);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }

    correo.setFechaHora(LocalDateTime.now());

    if (!dao.updateCorreo(CorreoFactory.buildCorreo(correo))) {
      throw new CorreoException(4);
    }

    return correoGuardado;
  }

  @Override
  public AbsCorreo eliminarEnviado(int idCorreo) throws CorreoException, SQLDBException {

    AbsCorreo correoGuardado = dao.getCorreo(idCorreo);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }

    if (!dao.deleteCorreo(idCorreo)) {
      throw new CorreoException(5);
    }

    return correoGuardado;

  }

  @Override
  public AbsCorreo eliminarRecibido(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException {

    AbsCorreo correoGuardado = dao.getCorreoRecibido(idCorreo, idUsuario);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }

    if (!dao.deleteCorreo(idCorreo, idUsuario)) {
      throw new CorreoException(5);
    }

    return correoGuardado;

  }

  @Override
  public AbsCorreo obtenerEnviado(int idCorreo) throws CorreoException, SQLDBException {

    AbsCorreo correoGuardado = dao.getCorreo(idCorreo);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    return correoGuardado;
  }

  @Override
  public AbsCorreo obtenerRecibido(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException {

    AbsCorreo correoGuardado = dao.getCorreoRecibido(idCorreo, idUsuario);

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    return correoGuardado;
  }

  @Override
  public List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosGuardados = dao.getCorreosRecibidos(idUsuario, borrado);

    return correosGuardados;

  }

  @Override
  public List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosGuardados = dao.getCorreosRecibidos(idUsuario, borrado);

    return correosGuardados;
  }

  @Override
  public AbsCorreo enviar(int idCorreo, int idUsuario)
      throws CorreoException, SQLDBException, CloneNotSupportedException {

    AbsCorreo correoGuardado = dao.getCorreo(idCorreo);
    AbsCorreo correoPorEnviar = (AbsCorreo) correoGuardado.clone();
    correoPorEnviar.setDestacado(false);
    correoPorEnviar.setImportante(false);
    correoPorEnviar.setLeido(false);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    correoGuardado.setFechaHora(LocalDateTime.now());
    correoGuardado.setConfirmado(true);

    List<AbsFiltro> filtroRecibido = filtroDAO.listarFiltrosUsuario(idUsuario);
    List<AbsFiltro> filtroEnvio = filtroDAO.listarFiltrosUsuario(correoGuardado.getIdUsuario());
    List<AbsFiltro> filtroEnviadoCoincidente = new ArrayList<AbsFiltro>();
    List<AbsFiltro> filtroRecibidoCoincidente = new ArrayList<AbsFiltro>();

    for (AbsFiltro filtro : filtroEnvio) {

      //ASUNTO
      if ((filtro.getAsunto() != null) && (correoGuardado.getAsunto() != null)) {
        if (correoGuardado.getAsunto().toLowerCase().contains(filtro.getAsunto().toLowerCase())) {
          filtroEnviadoCoincidente.add(filtro);
          continue;
        }
      }

      //CUERPO
      if ((filtro.getContiene() != null) && (correoGuardado.getCuerpo() != null)) {
        if (correoGuardado.getCuerpo().toLowerCase().contains(filtro.getContiene().toLowerCase())) {
          filtroEnviadoCoincidente.add(filtro);
          continue;
        }
      }
      //IDRECEPTOR
      if (filtro.getIdReceptor() == idUsuario) {
        filtroEnviadoCoincidente.add(filtro);
        continue;
      }

    }
    for (AbsFiltro filtro : filtroRecibido) {

      //ASUNTO
      if (!(filtro.getAsunto() == null) && !(correoGuardado.getAsunto() == null)) {
        if (correoGuardado.getAsunto().toLowerCase().contains(filtro.getAsunto().toLowerCase())) {
          filtroEnviadoCoincidente.add(filtro);
          continue;
        }
      }

      //CUERPO
      if (!(filtro.getContiene() == null) && !(correoGuardado.getCuerpo() == null)) {
        if (correoGuardado.getCuerpo().toLowerCase().contains(filtro.getContiene().toLowerCase())) {
          filtroEnviadoCoincidente.add(filtro);
          continue;
        }
      }
      //IDEMISOR
      if (filtro.getIdEmisor() == correoGuardado.getIdUsuario()) {
        filtroEnviadoCoincidente.add(filtro);
        continue;
      }
    }

    for (AbsFiltro filtro : filtroEnviadoCoincidente) {

      if (filtro.getDestacar()) {
        correoGuardado.setDestacado(true);
      }
      if (filtro.getImportante()) {
        correoGuardado.setImportante(true);
      }
      if (filtro.getLeido()) {
        correoGuardado.setLeido(true);
      }
      if (filtro.getEliminar()) {
        correoGuardado.setBorrado(true);
      }
      if (filtro.getIdEtiqueta() != 0) {
        etiquetaDAO.agregarEtiquetaACorreo(correoGuardado.getIdCorreo(), filtro.getIdEtiqueta());
      }
      if (filtro.getIdUsuarioReenviar() != 0) {
        if (dao.enviarCorreo(correoPorEnviar, filtro.getIdUsuarioReenviar())) {
        } else {
          throw new CorreoException(6, filtro.getIdUsuarioReenviar());
        }
      }
    }

    for (AbsFiltro filtro : filtroRecibidoCoincidente) {

      if (filtro.getDestacar()) {
        correoPorEnviar.setDestacado(true);
      }
      if (filtro.getImportante()) {
        correoPorEnviar.setImportante(true);
      }
      if (filtro.getLeido()) {
        correoPorEnviar.setLeido(true);
      }
      if (filtro.getEliminar()) {
        correoPorEnviar.setBorrado(true);
      }
      if (!(filtro.getIdUsuarioReenviar() == 0)) {
        reeEnviar(idCorreo, idUsuario, filtro.getIdUsuarioReenviar());
      }
    }
    if (!dao.updateCorreo(correoGuardado)) {
      throw new CorreoException(4);
    }
    if (dao.enviarCorreo(correoPorEnviar, idUsuario)) {
    } else {
      throw new CorreoException(6, idUsuario);
    }

    return correoGuardado;
  }

  @Override
  public AbsCorreo enviar(int idCorreo, int[] idUsuario)
      throws CorreoException, SQLDBException, CloneNotSupportedException {

    AbsCorreo correoGuardado = dao.getCorreo(idCorreo);
    AbsCorreo correoPorEnviar = (AbsCorreo) correoGuardado.clone();
    correoPorEnviar.setDestacado(false);
    correoPorEnviar.setImportante(false);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    if (correoGuardado.getConfirmado()) {
      throw new CorreoException(3);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }
    for (int id : idUsuario) {
      if (usuarioDAO.getUsuario(id) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + id);
      }
    }

    correoGuardado.setFechaHora(LocalDateTime.now());
    correoGuardado.setConfirmado(true);

    int x = dao.enviarCorreo(correoPorEnviar, idUsuario);
    if (x == 0) {
      if (!dao.updateCorreo(correoGuardado)) {
        throw new CorreoException(4);
      }
    } else {
      throw new CorreoException(7, x);
    }

    return correoGuardado;
  }

  public AbsCorreo reeEnviar(int idCorreo, int idUsuarioEmisor, int idUsuarioReceptor)
      throws CorreoException, SQLDBException, CloneNotSupportedException {

    AbsCorreo correoGuardado = obtenerRecibido(idCorreo, idUsuarioEmisor);
    AbsUsuario emisor = usuarioDAO.getUsuario(correoGuardado.getIdUsuario());
    AbsUsuario receptor = usuarioDAO.getUsuario(idUsuarioEmisor);

    correoGuardado.setBorrado(false);
    correoGuardado.setLeido(false);
    correoGuardado.setDestacado(false);
    correoGuardado.setImportante(false);
    correoGuardado.setIdUsuario(idUsuarioEmisor);

    correoGuardado.setCuerpo(
        "\"Reenvio de correo\" \n De: " + emisor.getCorreo() + "-->Para: " + receptor.getCorreo()
            + "\n" +
            "Enviado en la fecha: " + correoGuardado.getFechaHora() + "\n" + correoGuardado
            .getCuerpo());

    CorreoDTO correoDTO = new CorreoDTO(correoGuardado);

    correoGuardado = crear(correoDTO);

    enviar(correoGuardado.getIdCorreo(), idUsuarioReceptor);

    return correoGuardado;
  }

  @Override
  public List<AbsCorreo> obtenerImportantes(int idUsuario)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
    List<AbsCorreo> correosEnviados = dao.getCorreosEnviados(idUsuario, false);
    List<AbsCorreo> correosImportantes = new ArrayList<AbsCorreo>();

    for (AbsCorreo correo : correosRecibidos) {

      if (correo.getImportante()) {
        correosImportantes.add(correo);
      }

    }

    for (AbsCorreo correo : correosEnviados) {

      if (correo.getImportante()) {
        correosImportantes.add(correo);
      }

    }

    return correosImportantes;
  }

  @Override
  public List<AbsCorreo> obtenerDestacados(int idUsuario)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
    List<AbsCorreo> correosEnviados = dao.getCorreosEnviados(idUsuario, false);
    List<AbsCorreo> correosDestacados = new ArrayList<AbsCorreo>();

    for (AbsCorreo correo : correosRecibidos) {

      if (correo.getDestacado()) {
        correosDestacados.add(correo);
      }

    }

    for (AbsCorreo correo : correosEnviados) {

      if (correo.getDestacado()) {
        correosDestacados.add(correo);
      }

    }

    return correosDestacados;
  }

  @Override
  public List<AbsCorreo> obtenerBorrados(int idUsuario)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, true);
    List<AbsCorreo> correosEnviados = dao.getCorreosEnviados(idUsuario, true);
    List<AbsCorreo> correosBorrados = new ArrayList<AbsCorreo>();

    for (AbsCorreo correo : correosRecibidos) {
      correosBorrados.add(correo);
    }

    for (AbsCorreo correo : correosEnviados) {
      correosBorrados.add(correo);
    }

    return correosBorrados;
  }

  @Override
  public List<AbsCorreo> obtenerNoLeidos(int idUsuario)
      throws CorreoException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
    }

    List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
    List<AbsCorreo> correosNoLeidos = new ArrayList<AbsCorreo>();

    for (AbsCorreo correo : correosRecibidos) {

      if (!correo.getLeido()) {
        correosNoLeidos.add(correo);
      }

    }

    return correosNoLeidos;
  }

  @Override
  public AbsCorreo leeCorreo(int idCorreo, int idUsuario) throws CorreoException, SQLDBException {

    AbsCorreo correoGuardado = dao.getCorreoRecibido(idCorreo, idUsuario);

    if (correoGuardado == null) {
      throw new CorreoException(1, idCorreo);
    }

    if (correoGuardado.getBorrado()) {
      throw new CorreoException(2);
    }

    if (correoGuardado.getLeido()) {
      return correoGuardado;
    } else {
      correoGuardado.setLeido(true);
      if (!dao.updateCorreoRecibido(correoGuardado, idUsuario)) {
        throw new CorreoException(4);
      }
      return correoGuardado;
    }

  }

  private CorreoDTO cargarNulls(CorreoDTO correo) {

    if (correo.getBorrado() == null) {
      correo.setBorrado(false);
    }

    if (correo.getConfirmado() == null) {
      correo.setConfirmado(false);
    }

    if (correo.getLeido() == null) {
      correo.setLeido(false);
    }

    if (correo.getDestacado() == null) {
      correo.setDestacado(false);
    }

    if (correo.getImportante() == null) {
      correo.setImportante(false);
    }

    return correo;

  }


}
