package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.exception.CorreoError;
import com.gmail.exception.FiltroError;
import com.gmail.exception.SQLError;
import com.gmail.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroService implements IFiltroService{


    @Override
    public AbsFiltro crear(FiltroDTO filtro) throws FiltroError, SQLError {

        filtro = cargarNulls(filtro);

        FiltroDAO fdao=new FiltroDAO();
        UsuarioDAO udao= new UsuarioDAO();

        if (udao.getUsuario(filtro.getIdUsuario()) == null) {
            throw new FiltroError("Error: No existe Usuario con id = " + filtro.getIdUsuario());
        }

        return fdao.addFiltro(FiltroFactory.buildFiltro(filtro));
    }

    @Override
    public AbsFiltro modificar(FiltroDTO filtro) throws FiltroError ,SQLError{

        filtro = cargarNulls(filtro);

        FiltroDAO fdao=new FiltroDAO();
        UsuarioDAO udao= new UsuarioDAO();

        AbsFiltro filtroGuardado = fdao.getFiltro(filtro.getIdFiltro());

        if (filtroGuardado == null) {
            throw new FiltroError(1, filtro.getIdFiltro());
        }

        if (udao.getUsuario(filtro.getIdUsuario())==null) {
            throw new FiltroError("Error: No existe Usuario con id = " + filtro.getIdUsuario());
        }

        if (!fdao.updateFiltro(FiltroFactory.buildFiltro(filtro))) {
            throw new FiltroError(4);
        }

        return filtroGuardado;
    }

    @Override
    public AbsFiltro eliminarEnviado(int idFiltro) throws FiltroError {

        FiltroDAO fdao=new FiltroDAO();
        UsuarioDAO udao= new UsuarioDAO();

        AbsFiltro filtroGuardado = fdao.getFiltro(idFiltro);

        if (filtroGuardado == null) {
            throw new FiltroError(1, idFiltro);
        }

        if (!fdao.deleteFiltro(idFiltro)) {
            throw new FiltroError(5);
        }

        return filtroGuardado;

    }

    @Override
    public AbsFiltro obtenerUno(int idFiltro) throws FiltroError, SQLError {

        FiltroDAO fdao=new FiltroDAO();

        AbsFiltro filtroGuardado = fdao.getFiltro(idFiltro);

        if (filtroGuardado == null) {
            throw new FiltroError(1, idFiltro);
        }

        return filtroGuardado;

    }

    @Override
    public List<AbsFiltro> listarFiltroPorUsuario(int idUsuario) throws FiltroError, SQLError {

        FiltroDAO fdao=new FiltroDAO();
        UsuarioDAO udao= new UsuarioDAO();

        if (udao.getUsuario(idUsuario) == null) {
            throw new FiltroError("Error: No existe Usuario con id = " + idUsuario);
        }

        return fdao.listarFiltrosUsuario(idUsuario);

    }



    private FiltroDTO cargarNulls(FiltroDTO  filtro) {

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
