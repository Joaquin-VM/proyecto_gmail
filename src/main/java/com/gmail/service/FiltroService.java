package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.exception.CorreoError;
import com.gmail.exception.FiltroError;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsFiltro;
import com.gmail.model.CorreoFactory;
import com.gmail.model.FiltroFactory;

import java.time.LocalDateTime;

public class FiltroService {


    public AbsFiltro crear(FiltroDTO filtro) throws FiltroError, SQLError {

        filtro = cargarNulls(filtro);

        if (UsuarioDAO.getUsuario(filtro.getIdUsuario()) == null) {
            throw new FiltroError("Error: No existe Usuario con id = " + filtro.getIdUsuario());
        }

        return FiltroDAO.addFiltro(FiltroFactory.buildFiltro(filtro));
    }


    public AbsFiltro modificar(FiltroDTO filtro) throws FiltroError {

        filtro = cargarNulls(filtro);

        AbsFiltro filtroGuardado = FiltroDAO.getFiltro(filtro.getIdFiltro());

        if (filtroGuardado == null) {
            throw new FiltroError(1, filtro.getIdFiltro());
        }


        if (!CorreoDAO.updateCorreo(CorreoFactory.buildCorreo(correo))) {
            throw new CorreoError(4);
        }

        return correoGuardado;
    }


    public AbsFiltro eliminarEnviado(int filtro) throws CorreoError {

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if (correoGuardado == null) {
            throw new CorreoError(1, idCorreo);
        }

        if (correoGuardado.getBorrado()) {
            throw new CorreoError(2);
        }

        if (!CorreoDAO.deleteCorreo(idCorreo)) {
            throw new CorreoError(5);
        }

        return correoGuardado;

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
