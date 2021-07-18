package com.gmail.controller;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.ValidationException;
import com.gmail.utils.json.response.StandardResponse;
import com.gmail.utils.json.response.StatusResponse;
import com.gmail.service.impl.EtiquetaService;
import com.google.gson.Gson;
import spark.Route;

public class EtiquetaController {

  public static final EtiquetaService etiquetaService = new EtiquetaService();

  public static Route crearEtiqueta = (req, res) -> {
    res.type("application/json");

    EtiquetaDTO etiquetaCreada = new Gson().fromJson(req.body(), EtiquetaDTO.class);

    try {

      etiquetaCreada = new EtiquetaDTO(etiquetaService.crear(etiquetaCreada));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(etiquetaCreada)));

    } catch (ValidationException | NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerEtiquetaPorId = (req, res) -> {
    res.type("application/json");

    int idEtiqueta = Integer.parseInt(req.queryParams("id-etiqueta"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(etiquetaService.obtenerUna(idEtiqueta))));
    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerEtiquetasUsuario = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(etiquetaService.listarEtiquetasUsuario(idUsuario))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerEtiquetasCorreo = (req, res) -> {
    res.type("application/json");

    int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(etiquetaService.listarEtiquetasDeCorreo(idCorreo, idUsuario))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerEtiquetasCoincidentes = (req, res) -> {
    res.type("application/json");

    String texto = req.queryParams("texto");
    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(etiquetaService.obtenerCoincidentes(texto, idUsuario))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route modificarEtiqueta = (req, res) -> {
    res.type("application/json");

    try {

      EtiquetaDTO etiquetaModificada = new Gson().fromJson(req.body(), EtiquetaDTO.class);

      etiquetaModificada = new EtiquetaDTO(etiquetaService.modificar(etiquetaModificada));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(etiquetaModificada)));

    } catch (ValidationException | NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
          new Gson().toJson(e.getMessage())));

    }

  };

  public static Route eliminarEtiqueta = (req, res) -> {
    res.type("application/json");

    int idEtiqueta = Integer.parseInt(req.queryParams("id-etiqueta"));

    try {
      EtiquetaDTO etiquetaEliminada = new EtiquetaDTO(etiquetaService.eliminar(idEtiqueta));
      return new Gson()
          .toJson(
              new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(etiquetaEliminada)));

    } catch (NotFoundException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

  public static Route agregarEtiquetaACorreo = (req, res) -> {
    res.type("application/json");

    try {

      int idEtiqueta = Integer.parseInt(req.queryParams("id-etiqueta"));
      int idCorreo = Integer.parseInt(req.queryParams("id-correo"));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(
              new EtiquetaDTO(etiquetaService.agregarEtiquetaACorreo(idCorreo, idEtiqueta)))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route quitarEtiquetaACorreo = (req, res) -> {
    res.type("application/json");

    try {

      int idCorreo = Integer.parseInt(req.queryParams("id-correo"));
      int idEtiqueta = Integer.parseInt(req.queryParams("id-etiqueta"));
      int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(
              new EtiquetaDTO(
                  etiquetaService.quitarEtiquetaACorreo(idCorreo, idEtiqueta, idUsuario)))));

    } catch (NotFoundException | OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

}
