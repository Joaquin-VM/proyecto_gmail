package com.gmail.controller;

import com.gmail.utils.json.response.StandardResponse;
import com.gmail.utils.json.response.StatusResponse;
import com.google.gson.Gson;
import spark.Route;

public class InicioController {

  public static Route inicio = (req, res) -> new Gson()
      .toJson(new StandardResponse(StatusResponse.SUCCESS,
          new Gson()
              .toJsonTree("¡Bienvenido al clon de Gmail!")));

}
