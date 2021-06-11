package com.gmail.service;

import com.gmail.dto.UsuarioDTO;
import com.gmail.model.AbsUsuario;
import java.util.List;

public interface IUsuarioService {

  AbsUsuario crear(AbsUsuario usuario);

  AbsUsuario modificar(int id, AbsUsuario usuario);

  void eliminar(int id);

  AbsUsuario obtenerUno(int id);

  List<AbsUsuario> obtenerLista(int id);


}
