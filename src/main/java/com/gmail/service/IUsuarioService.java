package com.gmail.service;
import com.gmail.dto.UsuarioDTO;
import com.gmail.model.AbsUsuario;
import java.util.List;

public interface IUsuarioService {

  AbsUsuario crear(UsuarioDTO usuario);

  AbsUsuario modificar(int idUsuario, UsuarioDTO usuario);

  void eliminar(int idUsuario);

  AbsUsuario obtenerUno(int idUsuario);

  List<AbsUsuario> obtenerLista(int idUsuario);

}
