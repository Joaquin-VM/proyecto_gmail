package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.excepciones.CorreoExcepcion;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CorreoService {

    AbsCorreo crear(AbsCorreo correo) throws Exception {

        correo = cargarNulls(correo);

        if(UsuarioDAO.getUsuario(correo.getIdUsuario()) == null){
            throw new Exception("Error: No existe Usuario con id = "+correo.getIdUsuario());
        }

        correo.setFechaHora(LocalDateTime.now());

        return CorreoDAO.addCorreo(correo);
    }

    AbsCorreo modificar(AbsCorreo correo) throws Exception {

        correo = cargarNulls(correo);

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(correo.getIdCorreo());

        if(correoGuardado == null){
            throw new Exception("Error: No existe correo con id = " + correo.getIdCorreo());
        }

        if (correoGuardado.getBorrado()){
            throw new Exception("Error: El correo esta en papelera");
        }

        if(correoGuardado.getConfirmado()) {
            throw new Exception("Error: El correo esta enviado");
        }

        if(!CorreoDAO.updateCorreo(correo)){
            throw new Exception("Error: No pudo modificarse");
        }

        correo.setFechaHora(LocalDateTime.now());

        return CorreoDAO.getCorreo(correo.getIdCorreo());
    }

    AbsCorreo eliminar(int idCorreo) throws Exception{

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new Exception("Error: No existe correo con id = " + idCorreo);
        }

        if (correoGuardado.getBorrado()){
            throw new Exception("Error: El correo esta en papelera");
        }

        if(!CorreoDAO.deleteCorreo(idCorreo)){
            throw new Exception("Error: No pudo eliminarse");
        }


        return correoGuardado;

    }

    void eliminar(int idCorreo, int idUsuario){



    }

    AbsCorreo obtenerUno(int idCorreo){


    return CorreoDAO.getCorreo(idCorreo);
    }

    List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado){


        return CorreoDAO.getCorreosRecibidos(idUsuario,borrado);
    }

    List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado){


        return CorreoDAO.getCorreosEnviados(idUsuario,borrado);
    }

    private AbsCorreo cargarNulls(AbsCorreo correo) {

        if (correo.getBorrado()==null)
            correo.setBorrado(false);

        if(correo.getConfirmado()==null)
            correo.setConfirmado(false);

        if(correo.getLeido()==null)
            correo.setLeido(false);

        if(correo.getDestacado()==null)
            correo.setDestacado(false);

        if(correo.getImportante()==null)
            correo.setImportante(false);

        return correo;
    }






}
