package com.gmail.controller;

import com.gmail.dto.FiltroDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.utils.json.response.StandardResponse;
import com.gmail.utils.json.response.StatusResponse;
import com.gmail.service.impl.FiltroService;
import com.google.gson.Gson;
import spark.Route;

public class FIltroController {

  public static final FiltroService filtroService = new FiltroService();

  public static Route crearFiltro = (req, res) -> {
    res.type("application/json");

    try {

      FiltroDTO filtroCreado = new Gson().fromJson(req.body(), FiltroDTO.class);

      filtroCreado = new FiltroDTO(filtroService.crear(filtroCreado));

      return new Gson().toJson(
          new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(filtroCreado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerFiltroPorId = (req, res) -> {
    res.type("application/json");

    int idFiltro = Integer.parseInt(req.queryParams("id-filtro"));

    try {
      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree(filtroService.obtenerUno(idFiltro))));
    } catch (NotFoundException e) {
      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
    }

  };

  public static Route modificarFiltro = (req, res) -> {
    res.type("application/json");

    try {

      FiltroDTO filtroModificado = new Gson().fromJson(req.body(), FiltroDTO.class);

      filtroModificado = new FiltroDTO(filtroService.modificar(filtroModificado));

      return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson().toJsonTree(filtroModificado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
          new Gson().toJson(e.getMessage())));

    }

  };

  public static Route eliminarFiltro = (req, res) -> {
    res.type("application/json");

    int idFiltro = Integer.parseInt(req.queryParams("id-filtro"));

    try {

      FiltroDTO filtroEliminado = new FiltroDTO(filtroService.eliminarFiltro(idFiltro));
      return new Gson()
          .toJson(
              new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(filtroEliminado)));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

  public static Route leerFiltrosUsuario = (req, res) -> {
    res.type("application/json");

    int idUsuario = Integer.parseInt(req.queryParams("id-usuario"));

    try {
      return new Gson()
          .toJson(
              new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(filtroService.listarFiltrosUsuario(idUsuario))));

    } catch (OperationException e) {

      return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));

    }

  };

}


