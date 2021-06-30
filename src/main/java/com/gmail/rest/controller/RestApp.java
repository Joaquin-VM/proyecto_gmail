package com.gmail.rest.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import com.gmail.dto.CorreoDTO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.rest.controller.json.response.StandardResponse;
import com.gmail.rest.controller.json.response.StatusResponse;
import com.gmail.service.MostrarService;
import com.gmail.service.impl.CorreoService;
import com.gmail.service.impl.EtiquetaService;
import com.gmail.service.impl.FiltroService;
import com.gmail.service.impl.UsuarioService;
import com.google.gson.Gson;

public class RestApp {

  public static void main(String[] args) {

    port(6584);

    final UsuarioService usuarioService = new UsuarioService();
    final CorreoService correoService = new CorreoService();
    final EtiquetaService etiquetaService = new EtiquetaService();
    final FiltroService filtroService = new FiltroService();
    final MostrarService mostrarService = new MostrarService();

    path("/api", () -> {

      //http://localhost:6584/api/inicio
      get("/inicio", (req, res) -> {
        return new Gson()
            .toJson(new StandardResponse(StatusResponse.SUCCESS, "¡Bienvenido al clon de gmail!"));
      });

      //USUARIO.
      ////http://localhost:6584/api/usuario
      path("/usuario", () -> {

        //http://localhost:6584/api/usuario/crear
        post("/crear", (req, res) -> {
          res.type("application/json");

          UsuarioDTO usuarioDTO = new Gson().fromJson(req.body(), UsuarioDTO.class);

          try {
            usuarioService.crear(usuarioDTO);
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //http://localhost:6584/api/usuario/leer/id
        get("/leer/:id", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(usuarioService.obtenerUno(Integer.parseInt(req.params(":id"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/usuario/eliminar/id
        delete("/eliminar/:id", (req, res) -> {
          res.type("application/json");

          String mensajeExcepcion;

          try {
            usuarioService.eliminar(Integer.parseInt(req.params(":id")));
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "Usuario id " + Integer.parseInt(
                        req.params(":id")) + " eliminado."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/usuario/modificar/id
        put("/modificar/:id", (req, res) -> {
          res.type("application/json");

          UsuarioDTO aModificar = new Gson().fromJson(req.body(), UsuarioDTO.class);
          UsuarioDTO usuarioModificado = new UsuarioDTO();

          String mensajeExcepcion = new String();

          try {
            usuarioModificado = new UsuarioDTO(usuarioService.modificar(usuarioModificado));
            return new Gson().toJson(
                new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(usuarioModificado)));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                new Gson().toJson(e.getMessage())));
          }

        });


      });

      //CORREO.
      path("/correo", () -> {

        //http://localhost:6584/api/correo/crear
        post("/crear", (req, res) -> {
          res.type("application/json");

          CorreoDTO correoDTO = new Gson().fromJson(req.body(), CorreoDTO.class);

          try {
            correoService.crear(correoDTO);
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

      });

      //TODOS LOS GET DE CORREO.
      path("/leer", () -> {

        //ENVIADO.
        get("/enviado/:id", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(
                        correoService.obtenerEnviado(Integer.parseInt(req.params(":id"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //RECIBIDO.
        get("/recibido/:id_correo/:id_usuario", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(
                        correoService.obtenerRecibido(Integer.parseInt(req.params(":id_correo")),
                            Integer.parseInt(req.params(":id_usuario"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //ENVIADOS.
        get("/enviados/:id_usuario/:borrado", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(
                        correoService.obtenerEnviados(Integer.parseInt(req.params(":id_usuario")),
                            Boolean.valueOf(req.params(":borrado"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //RECIBIDOS.
        get("/recibidos/:id_usuario/:borrado", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(
                        correoService.obtenerRecibidos(Integer.parseInt(req.params(":id_usuario")),
                            Boolean.valueOf(":borrado")))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //ENVIAR.
        post("/crear/:id_correo/:id_usuario", (req, res) -> {
          res.type("application/json");

          CorreoDTO correoDTO = new Gson().fromJson(req.body(), CorreoDTO.class);

          try {
            correoService.enviar(Integer.parseInt(req.queryParams(":id_correo")),
                Integer.parseInt(req.queryParams(":id_usuario")));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

      });

      //ENVIAR A MAS DE 1.

      //REENVIAR.
      post("/crear/:id_correo/:id_usuario_emisor/:id_usuario_receptor", (req, res) -> {
        res.type("application/json");

        CorreoDTO correoDTO = new Gson().fromJson(req.body(), CorreoDTO.class);

        try {
          correoService.reeEnviar(Integer.parseInt(req.queryParams(":id_correo")),
              Integer.parseInt(req.queryParams(":id_usuario_emisor")),
              Integer.parseInt(req.queryParams(":id_usuario_receptor")));
        } catch (Exception e) {
          return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }

        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
      });

      //FALTA GET, ELIMINAR Y MODIFICAR DE CORREO.

      //ETIQUETA.
      path("/etiqueta", () -> {

        //http://localhost:6584/api/etiqueta/crear
        post("/crear", (req, res) -> {
          res.type("application/json");

          EtiquetaDTO etiquetaDTO = new Gson().fromJson(req.body(), EtiquetaDTO.class);

          try {
            etiquetaService.crear(etiquetaDTO);
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //http://localhost:6584/api/etiqueta/leer/id
        get("/leer/:id", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(etiquetaService.obtenerUna(Integer.parseInt(req.params(":id"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/etiqueta/eliminar/id
        delete("/eliminar/:id", (req, res) -> {
          res.type("application/json");

          String mensajeExcepcion;

          try {
            etiquetaService.eliminar(Integer.parseInt(req.params(":id")));
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "Etiqueta id " + Integer.parseInt(
                        req.params(":id")) + " eliminado."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          //FALTA MODIFICAR ETIQUETA.

        });

        //FILTRO. //Tiene que englobar.

        //http://localhost:6584/api/filtro/crear
        post("/crear", (req, res) -> {
          res.type("application/json");

          FiltroDTO filtroDTO = new Gson().fromJson(req.body(), FiltroDTO.class);

          try {
            filtroService.crear(filtroDTO);
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //http://localhost:6584/api/filtro/leer/id
        get("/leer/:id", (req, res) -> {
          res.type("application/json");

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(filtroService.obtenerUno(Integer.parseInt(req.params(":id"))))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //FALTA MODIFICAR FILTRO. FILTRO NO LLEVA ELIMINAR.

      });


    });

  }

}
