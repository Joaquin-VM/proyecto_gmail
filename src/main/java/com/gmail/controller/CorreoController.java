package com.gmail.controller;

import com.gmail.dto.CorreoDTO;
import com.gmail.exception.OperationException;
import com.gmail.utils.json.response.StandardResponse;
import com.gmail.utils.json.response.StatusResponse;
import com.gmail.service.impl.CorreoService;
import com.google.gson.Gson;
import spark.Route;

public class CorreoController {

  private static final CorreoService correoService = new CorreoService();

  public static Route crearCorreo = (req, res) -> {
    res.type("application/json");

    CorreoDTO correoDTO = new Gson().fromJson(req.body(), CorreoDTO.class);

    try {

      correoDTO = new CorreoDTO(correoService.crear(correoDTO));
      return new Gson().toJson(
          new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(correoDTO)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreoRecibido = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService.leerCorreo(idCorreo, idUsuario))));
    } catch (OperationException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

  public static Route leerCorreoEnviado = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService.obtenerEnviado(idCorreo))));
    } catch (OperationException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

  public static Route leerCorreosEnviados = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));
    boolean estaBorrado = Boolean.valueOf(req.queryParams("borrado"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService.obtenerEnviados(idUsuario, estaBorrado))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosRecibidos = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerRecibidos(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosImportantes = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerImportantes(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosDestacados = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerDestacados(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosBorrados = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerBorrados(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosLeidos = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerLeidos(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosNoLeidos = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerNoLeidos(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerCorreosSpam = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService
                      .obtenerSpam(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route enviarCorreoAUno = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
    int idUsuarioReceptor = Integer.parseInt(req.queryParams("id-usuario-receptor"));

    try {

      CorreoDTO correoEnviado = new CorreoDTO(correoService.enviar(idCorreo, idUsuarioReceptor));
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(correoEnviado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route enviarCorreoAMuchos = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));

    String[] parametros = req.queryParamsValues("id-usuario-receptor").clone();

    int[] idUsuariosReceptores = new int[parametros.length];

    for (int i = 0; i < idUsuariosReceptores.length; ++i) {
      idUsuariosReceptores[i] = Integer.parseInt(parametros[i]);
    }

    try {

      CorreoDTO correoEnviado = new CorreoDTO(
          correoService.enviar(idCorreo, idUsuariosReceptores));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(correoEnviado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route reenviarCorreo = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
    int idUsuarioEmisor = Integer.parseInt(req.queryParams("id-usuario-emisor"));
    int idUsuarioReceptor = Integer.parseInt(req.queryParams("id-usuario-receptor"));

    try {

      CorreoDTO correoReenviado = new CorreoDTO(correoService
          .reeEnviar(idCorreo, idUsuarioEmisor, idUsuarioReceptor));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(correoReenviado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route modificarCorreo = (req, res) -> {
    res.type("application/json");

    try {

      CorreoDTO correoModificado = new Gson().fromJson(req.body(), CorreoDTO.class);

      correoModificado = new CorreoDTO(correoService.modificar(correoModificado));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(correoModificado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
          new Gson().toJson(e.getMessage())));

    }

  };

  public static Route eliminarCorreoEnviado = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService.eliminarEnviado(idCorreo))));
    } catch (OperationException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

  public static Route eliminarCorreoRecibido = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
    int idUsuarioReceptor = Integer.parseInt(req.queryParams("id-usuario-receptor"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(
                  correoService.eliminarRecibido(idCorreo, idUsuarioReceptor))));
    } catch (OperationException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

}
