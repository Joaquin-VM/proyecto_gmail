package com.gmail.service;

import com.gmail.dto.FiltroDTO;
import com.gmail.exception.FiltroException;
import com.gmail.exception.SQLDBException;
import com.gmail.model.AbsFiltro;
import java.util.List;

public interface IFiltroService {

  AbsFiltro crear(FiltroDTO filtro) throws FiltroException, SQLDBException;

  AbsFiltro modificar(FiltroDTO filtro) throws FiltroException, SQLDBException;

  AbsFiltro eliminarFiltro(int idFiltro) throws FiltroException, SQLDBException;

  AbsFiltro obtenerUno(int idFiltro) throws FiltroException, SQLDBException;

  List<AbsFiltro> listarFiltroPorUsuario(int idUsuario) throws FiltroException, SQLDBException;


}
