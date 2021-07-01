package com.gmail.rest.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

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
      get("/inicio", (req, res) -> new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS, "¡Bienvenido al clon de gmail!")));

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

        get("/leer", (req, res) -> {
          res.type("application/json");

          int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(usuarioService.obtenerUno(idUsuario))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        delete("/eliminar", (req, res) -> {
          res.type("application/json");

          int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));

          try {
            usuarioService.eliminar(idUsuario);
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                        "Usuario id " + idUsuario + " eliminado."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/usuario/modificar
//        put("/modificar", (req, res) -> {
//          res.type("application/json");
//
//          UsuarioDTO aModificar = new Gson().fromJson(req.body(), UsuarioDTO.class);
//          UsuarioDTO usuarioModificado = new UsuarioDTO();
//
//          String mensajeExcepcion = new String();
//
//          try {
//            usuarioModificado = new UsuarioDTO(usuarioService.modificar(usuarioModificado));
//            return new Gson().toJson(
//                new StandardResponse(StatusResponse.SUCCESS,
//                    new Gson().toJsonTree(usuarioModificado)));
//          } catch (Exception e) {
//            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
//                new Gson().toJson(e.getMessage())));
//          }
//
//        });

      });

      //CORREO.
      path("/correo", () -> {

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

        //TODOS LOS GET DE CORREO.
        path("/leer", () -> {

          //OBTENIDO.
          //http://localhost:6584/api/correo/leer/recibido y 2 parametros idCorreo e idUsuario.

          get("/recibido", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));
            int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));

            try {
              return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                  new Gson()
                      .toJsonTree(
                          correoService.leeCorreo(idCorreo, idUsuario))));
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

          });

          //ENVIADO.

          //http://localhost:6584/api/correo/leer/enviado y un parametro de id de correo.
          get("/enviado", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));

            try {
              return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                  new Gson()
                      .toJsonTree(
                          correoService.obtenerEnviado(idCorreo))));
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

          });

          //ENVIADOS.
          //http://localhost:6584/api/correo/leer/enviados y recibe dos parametros idUsuario y borrado.
          get("/enviados", (req, res) -> {
            res.type("application/json");

            int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));
            boolean estaBorrado = Boolean.valueOf(req.queryParams("borrado"));

            try {
              return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                  new Gson()
                      .toJsonTree(
                          correoService.obtenerEnviados(idUsuario, estaBorrado))));
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

          });

          //RECIBIDOS.
          http://localhost:6584/api/correo/leer/recibidos y recibe dos parametros idUsuario y borrado.

          get("/recibidos", (req, res) -> {
            res.type("application/json");

            int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));
            boolean estaBorrado = Boolean.valueOf(req.queryParams("borrado"));

            try {
              return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                  new Gson()
                      .toJsonTree(
                          correoService
                              .obtenerRecibidos(idUsuario, estaBorrado))));
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

          });

        });

        path("/enviar", () -> {

          //ENVIAR A 1.

          //http://localhost:6584/api/correo/enviar/uno y 2 parametros idCorreo e idUsuario.
          post("/uno", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));
            int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));

            try {
              correoService.enviar(idCorreo, idUsuario);
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
          });

          //http://localhost:6584/api/correo/enviar/muchos y 2 parametros idCorreo e N valores de id de usuario.
          //ENVIAR A 1 O MAS USUARIOS.
          post("/muchos", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));

            System.out.println(req.queryParams());

            String[] parametros = req.queryParamsValues("idUsuario").clone();

            int[] arregloIdUsuarios = new int[parametros.length];

            for (int i = 0; i < arregloIdUsuarios.length; ++i) {
              arregloIdUsuarios[i] = Integer.parseInt(parametros[i]);
            }

            try {
              correoService.enviar(idCorreo, arregloIdUsuarios);
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
          });

        });

        //REENVIAR.
        //http://localhost:6584/api/correo/reenviar y 3 parametros idCorreo, idUsuarioEmisor e idUsuarioReceptor.
        post("/reenviar", (req, res) -> {
          res.type("application/json");

          int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));
          int idUsuarioEmisor = Integer.parseInt(req.queryParams("idUsuarioEmisor"));
          int idUsuarioReceptor = Integer.parseInt(req.queryParams("idUsuarioReceptor"));

          try {
            correoService.reeEnviar(idCorreo, idUsuarioEmisor, idUsuarioReceptor);
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //FALTA GET, ELIMINAR Y MODIFICAR DE CORREO.

      });

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

        //http://localhost:6584/api/etiqueta/leer y un parametro id de etiqueta.
        get("/leer", (req, res) -> {
          res.type("application/json");

          int idEtiqueta = Integer.parseInt(req.queryParams("idEtiqueta"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(etiquetaService.obtenerUna(idEtiqueta))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/etiqueta/eliminar y un parametro id de etiqueta.
        delete("/eliminar", (req, res) -> {
          res.type("application/json");

          int idEtiqueta = Integer.parseInt(req.queryParams("idEtiqueta"));

          try {
            etiquetaService.eliminar(idEtiqueta);
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                        "Etiqueta id " + idEtiqueta + " eliminada."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          //FALTA MODIFICAR ETIQUETA.

        });

      });

      //FILTRO. //Tiene que englobar.

      path("/filtro", () -> {

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

        //http://localhost:6584/api/filtro/leer y un parametro id de filtro.
        get("/leer", (req, res) -> {
          res.type("application/json");

          int idFiltro = Integer.parseInt(req.queryParams("idFiltro"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(filtroService.obtenerUno(idFiltro))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //http://localhost:6584/api/filtro/eliminar con un parametro id de filtro.
        delete("/eliminar", (req, res) -> {
          res.type("application/json");

          int idFiltro = Integer.parseInt(req.queryParams("idFiltro"));

          try {
            filtroService.eliminarFiltro(idFiltro);
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                        "Filtro id " + idFiltro + " eliminado."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //FALTA MODIFICAR FILTRO.

      });

    });

  }

}

