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

          int id = Integer.parseInt(req.queryParams("id"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(usuarioService.obtenerUno(id))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        delete("/eliminar", (req, res) -> {
          res.type("application/json");

          int idUsuario = Integer.parseInt(req.params("idUsuario"));

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

          get("", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.params("idCorreo"));
            int idUsuario = Integer.parseInt(req.params("idUsuario"));

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

          get("/enviado", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.params("idCorreo"));

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
          get("/enviados", (req, res) -> {
            res.type("application/json");

            int idUsuario = Integer.parseInt(req.params("idUsuario"));
            boolean estaBorrado = Boolean.valueOf(req.params("borrado"));

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
          get("/recibidos", (req, res) -> {
            res.type("application/json");

            int idUsuario = Integer.parseInt(req.params("idUsuario"));
            boolean estaBorrado = Boolean.valueOf(req.params("borrado"));

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

          //ENVIAR A 1 O MAS USUARIOS.
          post("/muchos", (req, res) -> {
            res.type("application/json");

            int idCorreo = Integer.parseInt(req.queryParams("idCorreo"));
            String[] idUsuariosString = (String[]) req.params().values().toArray();
            int[] idUsuariosInt = new int[idUsuariosString.length];

            for (int i = 0; i < idUsuariosInt.length; ++i) {
              idUsuariosInt[i] = Integer.parseInt(idUsuariosString[i]);
            }

            try {
              correoService.enviar(idCorreo, idUsuariosInt);
            } catch (Exception e) {
              return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
          });

        });

        //REENVIAR.
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

        get("/leer", (req, res) -> {
          res.type("application/json");

          int idEtiqueta = Integer.parseInt(req.params("idEtiqueta"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(etiquetaService.obtenerUna(idEtiqueta))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        delete("/eliminar", (req, res) -> {
          res.type("application/json");

          int idEtiqueta = Integer.parseInt(req.params("idEtiqueta"));

          try {
            etiquetaService.eliminar(idEtiqueta);
            return new Gson()
                .toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                        "Etiqueta id " + idEtiqueta + " eliminado."));

          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

          //FALTA MODIFICAR ETIQUETA.

        });

      });

      //FILTRO. //Tiene que englobar.

      path("/filtro", () -> {

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

        get("/leer", (req, res) -> {
          res.type("application/json");

          int idFiltro = Integer.parseInt(req.params("idFiltro"));

          try {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                new Gson()
                    .toJsonTree(filtroService.obtenerUno(idFiltro))));
          } catch (Exception e) {
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
          }

        });

        //FALTA MODIFICAR FILTRO. FILTRO NO LLEVA ELIMINAR.

      });

    });

  }

}

