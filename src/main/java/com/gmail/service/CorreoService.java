package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.excepciones.CorreoExcepcion;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CorreoService {

    AbsCorreo crear(CorreoDTO correo) throws Exception {

        correo = cargarNulls(correo);

        if(UsuarioDAO.getUsuario(correo.getIdUsuario()) == null){
            throw new Exception("Error: No existe Usuario con id = "+correo.getIdUsuario());
        }

        correo.setFechaHora(LocalDateTime.now());

        return CorreoDAO.addCorreo(correo);
    }

    AbsCorreo modificar(CorreoDTO correo) throws CorreoExcepcion {

        correo = cargarNulls(correo);

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(correo.getIdCorreo());

        if(correoGuardado == null){
            throw new CorreoExcepcion(1,correo.getIdCorreo());
        }

        if(correoGuardado.getConfirmado()) {
            throw new CorreoExcepcion(3);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoExcepcion(2);
        }

        if(!CorreoDAO.updateCorreo(correo)){
            throw new CorreoExcepcion(4);
        }

        correo.setFechaHora(LocalDateTime.now());

        return CorreoDAO.getCorreo(correo.getIdCorreo());
    }

    AbsCorreo eliminar(int idCorreo) throws CorreoExcepcion{

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new CorreoExcepcion(1 , idCorreo);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoExcepcion(2);
        }

        if(!CorreoDAO.deleteCorreo(idCorreo)){
            throw new CorreoExcepcion(5);
        }


        return correoGuardado;

    }

    AbsCorreo eliminar(int idCorreo, int idUsuario) throws CorreoExcepcion{

        AbsCorreo correoGuardado = CorreoDAO.getCorreoRecibido(idCorreo, idUsuario);

        if(correoGuardado == null){
            throw new CorreoExcepcion(1 , idCorreo);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoExcepcion(2);
        }

        if(!CorreoDAO.deleteCorreo(idCorreo, idUsuario)){
            throw new CorreoExcepcion(5);
        }


        return correoGuardado;

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

    private CorreoDTO cargarNulls(CorreoDTO correo) {

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
