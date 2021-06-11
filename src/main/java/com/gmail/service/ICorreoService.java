package com.gmail.service;

import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;

import java.util.List;

public interface ICorreoService {

    AbsCorreo crear(AbsCorreo correo);

    AbsCorreo modificar(AbsCorreo correo);

    void eliminar(int idCorreo);

    void eliminar(int idCorreo, int idUsuario);

    AbsCorreo obtenerUno(int idCorreo);

    List<AbsCorreo> obtenerRecibidos(int idUsuario, boolean borrado);

    List<AbsCorreo> obtenerEnviados(int idUsuario, boolean borrado);

}
