package com.gmail.service.impl;

import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.FiltroDTO;
import com.gmail.exception.FiltroException;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsFiltro;
import com.gmail.model.impl.FiltroFactory;
import com.gmail.service.IFiltroService;
import java.util.List;

public class FiltroService implements IFiltroService {

  private final FiltroDAO dao = new FiltroDAO();
  private final UsuarioDAO usuarioDAO = new UsuarioDAO();

  @Override
  public AbsFiltro crear(FiltroDTO filtro)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(filtro.getIdUsuario()) == null) {
        throw new FiltroException(
            "Error: No existe Usuario con id = " + filtro.getIdUsuario() + ".");
      }

      return dao.addFiltro(FiltroFactory.buildFiltro(filtro));

    } catch (FiltroException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsFiltro obtenerUno(int idFiltro)
      throws NotFoundException {

    try {

      AbsFiltro filtroGuardado = dao.getFiltro(idFiltro);

      if (filtroGuardado == null) {

        throw new FiltroException(1, idFiltro);

      }

      return filtroGuardado;

    } catch (FiltroException | SQLDBException e) {

      throw new NotFoundException(e.getMessage());

    }

  }

  @Override
  public AbsFiltro modificar(FiltroDTO filtroModificado)
      throws OperationException {

    try {

      FiltroDTO filtroGuardado = new FiltroDTO(dao.getFiltro(filtroModificado.getIdFiltro()));

      if (filtroGuardado == null) {
        throw new FiltroException(1, filtroGuardado.getIdFiltro());
      }

      if (filtroModificado.getIdEmisor() != 0) {
        filtroGuardado.setIdEmisor(filtroModificado.getIdEmisor());
      }

      if (filtroModificado.getIdReceptor() != 0) {
        filtroGuardado.setIdReceptor(filtroModificado.getIdReceptor());
      }

      if (filtroModificado.getAsunto() != null) {
        filtroGuardado.setAsunto(filtroModificado.getAsunto());
      }

      if (filtroModificado.getContiene() != null) {
        filtroGuardado.setContiene(filtroModificado.getContiene());
      }

      if (filtroModificado.getLeido() != null) {
        filtroGuardado.setLeido(filtroModificado.getLeido());
      }

      if (filtroModificado.getDestacar() != null) {
        filtroGuardado.setDestacar(filtroModificado.getDestacar());
      }

      if (filtroModificado.getImportante() != null) {
        filtroGuardado.setImportante(filtroModificado.getImportante());
      }

      if (filtroModificado.getEliminar() != null) {
        filtroGuardado.setEliminar(filtroModificado.getEliminar());
      }

      if (filtroModificado.getSpam() != null) {
        filtroGuardado.setSpam(filtroModificado.getSpam());
      }

      if (filtroModificado.getIdEtiqueta() != 0) {
        filtroGuardado.setIdEtiqueta(filtroModificado.getIdEtiqueta());
      }

      if (filtroModificado.getIdUsuarioReenviar() != 0) {
        filtroGuardado.setIdUsuarioReenviar(filtroModificado.getIdUsuarioReenviar());
      }

      if (usuarioDAO.getUsuario(filtroGuardado.getIdUsuario()) == null) {
        throw new NotFoundException(
            "Error: No existe Usuario con id = " + filtroGuardado.getIdUsuario() + ".");
      }

      if (!dao.updateFiltro(FiltroFactory.buildFiltro(filtroGuardado))) {
        throw new FiltroException(4);
      }

      return FiltroFactory.buildFiltro(filtroGuardado);

    } catch (FiltroException | NotFoundException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

  @Override
  public AbsFiltro eliminarFiltro(int idFiltro) throws OperationException {

    try {

      AbsFiltro filtroGuardado = dao.getFiltro(idFiltro);

      if (filtroGuardado == null) {
        throw new FiltroException(1, idFiltro);
      }

      if (!dao.deleteFiltro(idFiltro)) {
        throw new FiltroException(5);
      }

      return filtroGuardado;

    } catch (FiltroException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }


  }

  @Override
  public List<AbsFiltro> listarFiltrosUsuario(int idUsuario)
      throws OperationException {

    try {

      if (usuarioDAO.getUsuario(idUsuario) == null) {
        throw new FiltroException("Error: No existe Usuario con id = " + idUsuario);
      }

      return dao.listarFiltrosUsuario(idUsuario);

    } catch (FiltroException | SQLDBException e) {

      throw new OperationException(e.getMessage());

    }

  }

}
