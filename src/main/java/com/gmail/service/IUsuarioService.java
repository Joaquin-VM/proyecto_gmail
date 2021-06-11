package com.gmail.service;
import com.gmail.model.AbsUsuario;
import java.util.List;

public interface IUsuarioService {

  AbsUsuario crear(AbsUsuario usuario);

  AbsUsuario modificar(int idUsuario, AbsUsuario usuario);

  void eliminar(int idUsuario);

  AbsUsuario obtenerUno(int idUsuario);

  List<AbsUsuario> obtenerLista(int idUsuario);

}
