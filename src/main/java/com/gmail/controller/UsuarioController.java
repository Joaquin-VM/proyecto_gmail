package com.gmail.controller;

import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.ValidationException;
import com.gmail.utils.json.response.StandardResponse;
import com.gmail.utils.json.response.StatusResponse;
import com.gmail.service.impl.UsuarioService;
import com.google.gson.Gson;
import spark.Route;

public class UsuarioController {

  private static final UsuarioService usuarioService = new UsuarioService();

  public static Route crearUsuario = (req, res) -> {
    res.type("application/json");

    UsuarioDTO usuarioDTO = new Gson().fromJson(req.body(), UsuarioDTO.class);

    try {

      usuarioDTO = new UsuarioDTO(usuarioService.crear(usuarioDTO));
      return new Gson().toJson(
          new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(usuarioDTO)));

    } catch (ValidationException | NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerUsuarioPorId = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(usuarioService.obtenerUno(idUsuario))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerUsuarioPorCorreo = (req, res) -> {
    res.type("application/json");

    String dirCorreoUsuario = req.queryParams("dir-correo");

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(usuarioService.obtenerUno(dirCorreoUsuario))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route modificarUsuario = (req, res) -> {
    res.type("application/json");

    try {

      UsuarioDTO usuarioModificado = new Gson().fromJson(req.body(), UsuarioDTO.class);
      usuarioModificado = new UsuarioDTO(usuarioService.modificar(usuarioModificado));
      return new Gson().toJson(
          new StandardResponse(StatusResponse.SUCCESS,
              new Gson().toJsonTree(usuarioModificado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
          new Gson().toJson(e.getMessage())));

    }

  };

  public static Route eliminarUsuarioPorId = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      UsuarioDTO usuarioEliminado = new UsuarioDTO(usuarioService.eliminar(idUsuario));
      return new Gson()
          .toJson(
              new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(usuarioEliminado)));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route eliminarUsuarioPorCorreo = (req, res) -> {
    res.type("application/json");

    String dirCorreoUsuario = req.queryParams("dir-correo");

    try {

      UsuarioDTO usuarioEliminado = new UsuarioDTO(usuarioService.eliminar(dirCorreoUsuario));
      return new Gson()
          .toJson(
              new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(usuarioEliminado)));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

}
