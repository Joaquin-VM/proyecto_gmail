package com.gmail.service.impl;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsFiltro;
import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.CorreoFactory;
import com.gmail.service.ICorreoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoService implements ICorreoService {

  private final CorreoDAO dao = new CorreoDAO();
  private final UsuarioDAO usuarioDAO = new UsuarioDAO();
  private final FiltroDAO filtroDAO = new FiltroDAO();
  private final EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

  @Override
  public AbsCorreo crear(CorreoDTO correo) throws OperationException {

    try {

      if (usuarioDAO.getUsuario(correo.getIdUsuario()) == null) {
        throw new CorreoException(
            "Error: No existe Usuario con id = " + correo.getIdUsuario() + ".");
      }

      correo.setFechaHora(LocalDateTime.now());

      return dao.addCorreo(CorreoFactory.buildCorreo(correo));

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo modificar(CorreoDTO correoModificado)
      throws OperationException {

    try {

      CorreoDTO correoGuardado = new CorreoDTO(
          obtenerEnviado(correoModificado.getIdCorreo()));

      if (correoGuardado == null) {
        throw new CorreoException(1, correoModificado.getIdCorreo());
      }

      if (correoGuardado.getConfirmado()) {
        throw new CorreoException(3);
      }

      if (correoGuardado.getBorrado()) {
        throw new CorreoException(2);
      }

      if (correoModificado.getAsunto() != null) {
        correoGuardado.setAsunto(correoModificado.getAsunto());
      }

      if (correoModificado.getCuerpo() != null) {
        correoGuardado.setCuerpo(correoModificado.getCuerpo());
      }

      correoModificado.setFechaHora(LocalDateTime.now());

      if (!dao.updateCorreo(CorreoFactory.buildCorreo(correoGuardado))) {
        throw new CorreoException(4);
      }

      return CorreoFactory.buildCorreo(correoGuardado);

    } catch (CorreoException | OperationException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo eliminarEnviado(int idCorreo)
      throws OperationException {

    try {

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

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo eliminarRecibido(int idCorreo, int idUsuario)
      throws OperationException {

    try {

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

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo obtenerEnviado(int idCorreo)
      throws OperationException {

    try {

      AbsCorreo correoGuardado = dao.getCorreo(idCorreo);

      if (correoGuardado == null) {
        throw new CorreoException(1, idCorreo);
      }

      if (correoGuardado.getConfirmado()) {

        correoGuardado
            .setDirCorreosReceptores(dao.dirCorreosReceptores(correoGuardado.getIdCorreo()));

      } else {

        throw new OperationException(
            "El Correo id " + idCorreo + " corresponde a un correo que fue creado pero no enviado.");

      }

      return correoGuardado;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo obtenerRecibido(int idCorreo, int idUsuarioReceptor)
      throws OperationException {

    try {

      AbsCorreo correoGuardado = dao.getCorreoRecibido(idCorreo, idUsuarioReceptor);

      if (usuarioDAO.getUsuario(idUsuarioReceptor) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuarioReceptor);
      }

      if (correoGuardado == null) {
        throw new CorreoException(1, idCorreo);
      }

      correoGuardado
          .setDirCorreosReceptores(dao.dirCorreosReceptores(correoGuardado.getIdCorreo()));

      return correoGuardado;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerRecibidos(int idUsuario)
      throws OperationException {

    try {
      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosGuardados = dao.getCorreosRecibidos(idUsuario, false);

      return correosGuardados;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }


  }

  @Override
  public List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosGuardados = dao.getCorreosCreados(idUsuario, borrado);
      List<AbsCorreo> correosEnviados = new ArrayList<>();

      for (AbsCorreo correo : correosGuardados) {

        if (correo.getConfirmado()) {

          correo.setDirCorreosReceptores(dao.dirCorreosReceptores(correo.getIdCorreo()));
          correosEnviados.add(correo);

        }

      }

      return correosEnviados;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo enviar(int idCorreo, int idUsuario)
      throws OperationException {

    try {

      AbsCorreo correoGuardado = dao.getCorreo(idCorreo);
      AbsCorreo correoPorEnviar = (AbsCorreo) correoGuardado.clone();
      correoPorEnviar.setDestacado(false);
      correoPorEnviar.setImportante(false);
      correoPorEnviar.setLeido(false);
      Boolean yaFueEnviado = false;

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

      List<AbsFiltro> filtrosRecibidos = filtroDAO.listarFiltrosUsuario(idUsuario);
      List<AbsFiltro> filtrosEnviados = filtroDAO
          .listarFiltrosUsuario(correoGuardado.getIdUsuario());
      List<AbsFiltro> filtrosEnviadosCoincidentes = new ArrayList<>();
      List<AbsFiltro> filtrosRecibidosCoincidentes = new ArrayList<>();

      for (AbsFiltro filtro : filtrosEnviados) {

        //ASUNTO
        if (filtro.getAsunto() != null && correoGuardado.getAsunto() != null) {
          if (correoGuardado.getAsunto().toLowerCase().contains(filtro.getAsunto().toLowerCase())) {
            filtrosEnviadosCoincidentes.add(filtro);
            continue;
          }
        }

        //CUERPO
        if (filtro.getContiene() != null && correoGuardado.getCuerpo() != null) {
          if (correoGuardado.getCuerpo().toLowerCase()
              .contains(filtro.getContiene().toLowerCase())) {
            filtrosEnviadosCoincidentes.add(filtro);
            continue;
          }
        }

        //IDRECEPTOR
        if (filtro.getIdReceptor() == idUsuario) {
          filtrosEnviadosCoincidentes.add(filtro);
          continue;
        }

      }

      for (AbsFiltro filtro : filtrosRecibidos) {

        //ASUNTO
        if (filtro.getAsunto() != null && correoGuardado.getAsunto() != null) {
          if (correoGuardado.getAsunto().toLowerCase().contains(filtro.getAsunto().toLowerCase())) {
            filtrosRecibidosCoincidentes.add(filtro);
            continue;
          }
        }

        //CUERPO
        if (filtro.getContiene() != null && correoGuardado.getCuerpo() != null) {
          if (correoGuardado.getCuerpo().toLowerCase()
              .contains(filtro.getContiene().toLowerCase())) {
            filtrosRecibidosCoincidentes.add(filtro);
            continue;
          }
        }

        //IDEMISOR
        if (filtro.getIdEmisor() == correoGuardado.getIdUsuario()) {
          filtrosRecibidosCoincidentes.add(filtro);
          continue;
        }
      }

      for (AbsFiltro filtro : filtrosEnviadosCoincidentes) {

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

          if (!dao.enviarCorreo(correoPorEnviar, filtro.getIdUsuarioReenviar())) {

            throw new CorreoException(6, filtro.getIdUsuarioReenviar());

          }

          yaFueEnviado = true;

        }

      }

      for (AbsFiltro filtro : filtrosRecibidosCoincidentes) {

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

        if (filtro.getIdUsuarioReenviar() != 0) {

          if (!dao.updateCorreo(correoGuardado)) {
            throw new CorreoException(4);
          }

          if (!dao.enviarCorreo(correoPorEnviar, idUsuario)) {
            throw new CorreoException(6, idUsuario);
          }

          yaFueEnviado = true;

          reeEnviar(idCorreo, idUsuario, filtro.getIdUsuarioReenviar());

        }

      }

      if (!yaFueEnviado) {

        if (!dao.updateCorreo(correoGuardado)) {
          throw new CorreoException(4);
        }

        if (!dao.enviarCorreo(correoPorEnviar, idUsuario)) {
          throw new CorreoException(6, idUsuario);
        }

      }

      return correoGuardado;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo enviar(int idCorreo, int[] idUsuarios)
      throws OperationException {

    try {
      AbsCorreo correoGuardado = dao.getCorreo(idCorreo);

      for (int i : idUsuarios) {

        this.enviar(idCorreo, i);

      }

      return correoGuardado;

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo reeEnviar(int idCorreo, int idUsuarioEmisor, int idUsuarioReceptor)
      throws OperationException {

    try {

      AbsCorreo correoGuardado = obtenerRecibido(idCorreo, idUsuarioEmisor);
      AbsUsuario creador = usuarioDAO.getUsuario(correoGuardado.getIdUsuario());
      AbsUsuario reenviador = usuarioDAO.getUsuario(idUsuarioEmisor);

      correoGuardado.setConfirmado(true);
      correoGuardado.setBorrado(false);
      correoGuardado.setLeido(false);
      correoGuardado.setDestacado(false);
      correoGuardado.setImportante(false);
      correoGuardado.setIdUsuario(idUsuarioEmisor);

      correoGuardado.setCuerpo(
          "\"Reenvio de correo\" \n De: " + creador.getCorreo() + "-->Para: " + reenviador
              .getCorreo()
              + "\n" +
              "Enviado en la fecha: " + correoGuardado.getFechaHora() + "\n" + correoGuardado
              .getCuerpo());

      correoGuardado = crear(new CorreoDTO(correoGuardado));

      return enviar(correoGuardado.getIdCorreo(), idUsuarioReceptor);

    } catch (SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerImportantes(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
      List<AbsCorreo> correosEnviados = this.obtenerEnviados(idUsuario, false);
      List<AbsCorreo> correosImportantes = new ArrayList<>();

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

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerDestacados(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
      List<AbsCorreo> correosEnviados = this.obtenerEnviados(idUsuario, false);
      List<AbsCorreo> correosDestacados = new ArrayList<>();

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

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerBorrados(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, true);
      List<AbsCorreo> correosEnviados = this.obtenerEnviados(idUsuario, true);
      List<AbsCorreo> correosBorrados = new ArrayList<>();

      for (AbsCorreo correo : correosRecibidos) {
        correosBorrados.add(correo);
      }

      for (AbsCorreo correo : correosEnviados) {
        correosBorrados.add(correo);
      }

      return correosBorrados;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerLeidos(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
      List<AbsCorreo> correosLeidos = new ArrayList<>();

      for (AbsCorreo correo : correosRecibidos) {

        if (correo.getLeido()) {
          correosLeidos.add(correo);
        }

      }

      return correosLeidos;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerNoLeidos(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
      List<AbsCorreo> correosNoLeidos = new ArrayList<>();

      for (AbsCorreo correo : correosRecibidos) {

        if (!correo.getLeido()) {
          correosNoLeidos.add(correo);
        }

      }

      return correosNoLeidos;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public List<AbsCorreo> obtenerSpam(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new CorreoException("Error: No existe Usuario con id = " + idUsuario);
      }

      List<AbsCorreo> correosRecibidos = dao.getCorreosRecibidos(idUsuario, false);
      List<AbsCorreo> correosEnviados = obtenerEnviados(idUsuario, false);
      List<AbsCorreo> correosSpam = new ArrayList<>();

      for (AbsCorreo correo : correosRecibidos) {

        if (correo.getSpam()) {
          correosSpam.add(correo);
        }

      }

      for (AbsCorreo correo : correosEnviados) {

        if (correo.getSpam()) {
          correosSpam.add(correo);
        }

      }

      return correosSpam;

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsCorreo leerCorreo(int idCorreo, int idUsuario)
      throws OperationException {

    try {

      AbsCorreo correoGuardado = dao.getCorreoRecibido(idCorreo, idUsuario);

      if (correoGuardado == null) {
        throw new CorreoException(1, idCorreo);
      }

      if (correoGuardado.getBorrado()) {
        throw new CorreoException(2);
      }

      correoGuardado
          .setDirCorreosReceptores(dao.dirCorreosReceptores(correoGuardado.getIdCorreo()));

      if (correoGuardado.getLeido()) {

        return correoGuardado;

      } else {

        correoGuardado.setLeido(true);

        if (!dao.updateCorreoRecibido(correoGuardado, idUsuario)) {
          throw new CorreoException(4);
        }

        return correoGuardado;

      }

    } catch (CorreoException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

}
