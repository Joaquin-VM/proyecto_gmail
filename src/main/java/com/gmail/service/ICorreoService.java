package com.gmail.service;

import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsUsuario;

import java.util.List;

public interface ICorreoService {

    AbsCorreo crear(AbsCorreo correo);

    AbsCorreo modificar(int id, AbsCorreo correo);

    void eliminar(int idCorreo);

    void eliminar(int idCorreo, int idUsuario);

    AbsCorreo obtenerUno(int id);

    List<AbsCorreo> obtenerLista(int id);

}
