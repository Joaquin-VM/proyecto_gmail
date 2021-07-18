package com.gmail.service;

import com.gmail.dto.FiltroDTO;
import com.gmail.exception.FiltroException;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsFiltro;
import java.util.List;

public interface IFiltroService {

  AbsFiltro crear(FiltroDTO filtro)
      throws FiltroException, SQLDBException, OperationException, NotFoundException;

  AbsFiltro modificar(FiltroDTO filtro)
      throws FiltroException, SQLDBException, NotFoundException, OperationException;

  AbsFiltro eliminarFiltro(int idFiltro) throws FiltroException, SQLDBException, OperationException;

  AbsFiltro obtenerUno(int idFiltro) throws FiltroException, SQLDBException, NotFoundException;

  List<AbsFiltro> listarFiltrosUsuario(int idUsuario)
      throws FiltroException, SQLDBException, OperationException, NotFoundException;

}
