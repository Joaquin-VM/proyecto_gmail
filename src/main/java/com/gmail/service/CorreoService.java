package com.gmail.service;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.exception.CorreoError;
import com.gmail.exception.SQLError;
import com.gmail.model.AbsCorreo;
import com.gmail.model.CorreoFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CorreoService implements ICorreoService{


    @Override
    public AbsCorreo crear(CorreoDTO correo) throws CorreoError, SQLError {

        correo = cargarNulls(correo);

        if(UsuarioDAO.getUsuario(correo.getIdUsuario()) == null){
            throw new CorreoError("Error: No existe Usuario con id = "+correo.getIdUsuario());
        }

        correo.setFechaHora(LocalDateTime.now());

        return CorreoDAO.addCorreo(CorreoFactory.buildCorreo(correo));
    }

    @Override
    public AbsCorreo modificar(CorreoDTO correo) throws CorreoError {

        correo = cargarNulls(correo);

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(correo.getIdCorreo());

        if(correoGuardado == null){
            throw new CorreoError(1,correo.getIdCorreo());
        }

        if(correoGuardado.getConfirmado()) {
            throw new CorreoError(3);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoError(2);
        }

        if(!CorreoDAO.updateCorreo(CorreoFactory.buildCorreo(correo))){
            throw new CorreoError(4);
        }

        correo.setFechaHora(LocalDateTime.now());

        return correoGuardado;
    }

    @Override
    public AbsCorreo eliminarEnviado(int idCorreo) throws CorreoError{

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new CorreoError(1 , idCorreo);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoError(2);
        }

        if(!CorreoDAO.deleteCorreo(idCorreo)){
            throw new CorreoError(5);
        }


        return correoGuardado;

    }

    @Override
    public AbsCorreo eliminarRecibido(int idCorreo, int idUsuario) throws CorreoError{

        AbsCorreo correoGuardado = CorreoDAO.getCorreoRecibido(idCorreo, idUsuario);

        if(correoGuardado == null){
            throw new CorreoError(1 , idCorreo);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoError(2);
        }

        if(!CorreoDAO.deleteCorreo(idCorreo, idUsuario)){
            throw new CorreoError(5);
        }


        return correoGuardado;

    }

    @Override
    public AbsCorreo obtenerEnviado(int idCorreo) throws CorreoError{

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new CorreoError(1,idCorreo);
        }

        return correoGuardado;
    }

    @Override
    public AbsCorreo obtenerRecibido(int idCorreo, int idUsuario) throws CorreoError, SQLError {

        AbsCorreo correoGuardado = CorreoDAO.getCorreoRecibido(idCorreo, idUsuario);

        if(UsuarioDAO.getUsuario(idUsuario) == null){
            throw new CorreoError("Error: No existe Usuario con id = "+idUsuario);
        }

        if(correoGuardado == null){
            throw new CorreoError(1,idCorreo);
        }

        return correoGuardado;
    }

    @Override
    public List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado)
        throws CorreoError, SQLError {

        if(UsuarioDAO.getUsuario(idUsuario) == null){
            throw new CorreoError("Error: No existe Usuario con id = "+idUsuario);
        }

        List<AbsCorreo> correosGuardados = CorreoDAO.getCorreosRecibidos(idUsuario, borrado);

        return correosGuardados;

    }

    @Override
    public List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado)
        throws CorreoError, SQLError {

        if(UsuarioDAO.getUsuario(idUsuario) == null){
            throw new CorreoError("Error: No existe Usuario con id = "+idUsuario);
        }

        List<AbsCorreo> correosGuardados = CorreoDAO.getCorreosRecibidos(idUsuario, borrado);

        return correosGuardados;
    }

    @Override
    public AbsCorreo enviar(int idCorreo, int idUsuario) throws CorreoError, SQLError {

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new CorreoError(1, idCorreo);
        }

        if(correoGuardado.getConfirmado()) {
            throw new CorreoError(3);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoError(2);
        }

        if(UsuarioDAO.getUsuario(idUsuario) == null){
            throw new CorreoError("Error: No existe Usuario con id = " + idUsuario);
        }

        correoGuardado.setFechaHora(LocalDateTime.now());
        correoGuardado.setConfirmado(true);

        if(CorreoDAO.enviarCorreo(idCorreo, idUsuario)){
            if(!CorreoDAO.updateCorreo(correoGuardado))
                throw new CorreoError(4);
        }else{
            throw new CorreoError(6, idUsuario);
        }


        return correoGuardado;
    }

    public AbsCorreo enviar(int idCorreo, int[] idUsuario) throws CorreoError {

        AbsCorreo correoGuardado = CorreoDAO.getCorreo(idCorreo);

        if(correoGuardado == null){
            throw new CorreoError(1, idCorreo);
        }

        if(correoGuardado.getConfirmado()) {
            throw new CorreoError(3);
        }

        if (correoGuardado.getBorrado()){
            throw new CorreoError(2);
        }

        correoGuardado.setFechaHora(LocalDateTime.now());
        correoGuardado.setConfirmado(true);

        int x=CorreoDAO.enviarCorreo(idCorreo, idUsuario);
        if(x==0){
            if(!CorreoDAO.updateCorreo(correoGuardado))
                throw new CorreoError(4);
        }else{
            throw new CorreoError(7, x);
        }


        return correoGuardado;
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
