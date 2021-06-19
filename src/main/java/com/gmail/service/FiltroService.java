package com.gmail.service;

import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.FiltroDTO;
import com.gmail.exception.FiltroException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsFiltro;
import com.gmail.model.impl.FiltroFactory;
import java.util.List;

public class FiltroService implements IFiltroService {

  FiltroDAO dao = new FiltroDAO();
  UsuarioDAO usuarioDAO = new UsuarioDAO();
  EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

  @Override
  public AbsFiltro crear(FiltroDTO filtro) throws FiltroException, SQLDBException {

    filtro = cargarNulls(filtro);

    if (usuarioDAO.getUsuario(filtro.getIdUsuario()) == null) {
      throw new FiltroException("Error: No existe Usuario con id = " + filtro.getIdUsuario());
    }
    if (usuarioDAO.getUsuario(filtro.getIdEmisor()) == null && filtro.getIdEmisor() != 0) {
      throw new FiltroException("Error: No existe Usuario con id = " + filtro.getIdEmisor());
    }
    if (usuarioDAO.getUsuario(filtro.getIdReceptor()) == null && filtro.getIdReceptor() != 0) {
      throw new FiltroException("Error: No existe Usuario con id = " + filtro.getIdReceptor());
    }
    if (usuarioDAO.getUsuario(filtro.getIdUsuarioReenviar()) == null && !(
        filtro.getIdUsuarioReenviar() == 0)) {
      throw new FiltroException(
          "Error: No existe Usuario con id = " + filtro.getIdUsuarioReenviar());
    }
    if (etiquetaDAO.getEtiqueta(filtro.getIdEtiqueta()) == null && !(filtro.getIdEtiqueta() == 0)) {
      throw new FiltroException("Error: No existe etiqueta con id = " + filtro.getIdEtiqueta());
    }

    return dao.addFiltro(FiltroFactory.buildFiltro(filtro));
  }

  @Override
  public AbsFiltro modificar(FiltroDTO filtro) throws FiltroException, SQLDBException {

    filtro = cargarNulls(filtro);

    AbsFiltro filtroGuardado = dao.getFiltro(filtro.getIdFiltro());

    if (filtroGuardado == null) {
      throw new FiltroException(1, filtro.getIdFiltro());
    }

    if (usuarioDAO.getUsuario(filtro.getIdUsuario()) == null) {
      throw new FiltroException("Error: No existe Usuario con id = " + filtro.getIdUsuario());
    }

    if (!dao.updateFiltro(FiltroFactory.buildFiltro(filtro))) {
      throw new FiltroException(4);
    }

    return filtroGuardado;
  }

  @Override
  public AbsFiltro eliminarEnviado(int idFiltro) throws FiltroException, SQLDBException {

    AbsFiltro filtroGuardado = dao.getFiltro(idFiltro);

    if (filtroGuardado == null) {
      throw new FiltroException(1, idFiltro);
    }

    if (!dao.deleteFiltro(idFiltro)) {
      throw new FiltroException(5);
    }

    return filtroGuardado;

  }

  @Override
  public AbsFiltro obtenerUno(int idFiltro) throws FiltroException, SQLDBException {

    AbsFiltro filtroGuardado = dao.getFiltro(idFiltro);

    if (filtroGuardado == null) {
      throw new FiltroException(1, idFiltro);
    }

    return filtroGuardado;

  }

  @Override
  public List<AbsFiltro> listarFiltroPorUsuario(int idUsuario)
      throws FiltroException, SQLDBException {

    if (usuarioDAO.getUsuario(idUsuario) == null) {
      throw new FiltroException("Error: No existe Usuario con id = " + idUsuario);
    }

    return dao.listarFiltrosUsuario(idUsuario);

  }

  private FiltroDTO cargarNulls(FiltroDTO filtro) {

    if (filtro.getLeido() == null) {
      filtro.setLeido(false);
    }

    if (filtro.getDestacar() == null) {
      filtro.setDestacar(false);
    }
    if (filtro.getImportante() == null) {
      filtro.setImportante(false);
    }
    if (filtro.getEliminar() == null) {
      filtro.setEliminar(false);
    }
    return filtro;

  }


}
