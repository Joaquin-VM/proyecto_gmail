package com.gmail.service;

import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.FiltroDTO;
import com.gmail.exception.FiltroError;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsFiltro;
import com.gmail.model.FiltroFactory;

import java.util.List;

public interface IFiltroService {

    AbsFiltro crear(FiltroDTO filtro) throws FiltroError, SQLError;


    AbsFiltro modificar(FiltroDTO filtro) throws FiltroError ,SQLError;


    AbsFiltro eliminarEnviado(int idFiltro) throws FiltroError;

    AbsFiltro obtenerUno(int idFiltro) throws FiltroError, SQLError;

    List<AbsFiltro> listarFiltroPorUsuario(int idUsuario) throws FiltroError, SQLError;



}
