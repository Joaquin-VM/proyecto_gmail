package com.gmail;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import com.gmail.conf.Web;
import com.gmail.controller.CorreoController;
import com.gmail.controller.EtiquetaController;
import com.gmail.controller.FIltroController;
import com.gmail.controller.InicioController;
import com.gmail.controller.UsuarioController;

public class App {

  public static void main(String[] args) {

    Web.arrancarServidor();

    path("/api", () -> {

      //INICIO.
      //http://localhost:6584/api/inicio
      get("/inicio", InicioController.inicio);

      //USUARIO.
      //http://localhost:6584/api/usuario
      path("/usuario", () -> {

        //CREAR UN USUARIO.
        //http://localhost:6584/api/usuario/crear -> Con el JSON del cuerpo.
        post("/crear", UsuarioController.crearUsuario);

        //GET DE USUARIO.
        //http://localhost:6584/api/usuario/leer
        path("/leer", () -> {

          //LEER UN USUARIO POR ID.
          //http://localhost:6584/api/usuario/leer/id -> 1 parametro: id-usuario.
          get("/id", UsuarioController.leerUsuarioPorId);

          //LEER UN USUARIO POR DIRECCION DE CORREO.
          //http://localhost:6584/api/usuario/leer/correo -> 1 parametro: dir-correo.
          get("/correo", UsuarioController.leerUsuarioPorCorreo);

        });

        //MODIFICAR UN USUARIO.
        //http://localhost:6584/api/usuario/modificar -> Con el JSON del cuerpo.
        put("/modificar", UsuarioController.modificarUsuario);

        //ELIMINAR UN USUARIO.
        path("/eliminar", () -> {

          //ELIMINAR UN USUARIO POR ID.
          //http://localhost:6584/api/usuario/eliminar/id -> 1 parametro: id-usuario.
          delete("/id", UsuarioController.eliminarUsuarioPorId);

          //ELIMINAR UN USUARIO POR DIRECCION DE CORREO.
          //http://localhost:6584/api/usuario/eliminar/correo -> 1 parametro: correo-usuario.
          delete("/correo", UsuarioController.eliminarUsuarioPorCorreo);

        });

      });

      //CORREO.
      //http://localhost:6584/api/correo
      path("/correo", () -> {

        //CREAR UN CORREO.
        //http://localhost:6584/api/correo/crear -> Con el JSON del cuerpo.
        post("/crear", CorreoController.crearCorreo);

        //TODOS LOS GET DE CORREO.
        path("/leer", () -> {

          //LEER UN CORREO RECIBIDO POR UN USUARIO.
          //http://localhost:6584/api/correo/leer/recibido -> 2 parametros: id-correo e id-usuario.
          get("/recibido", CorreoController.leerCorreoRecibido);

          //LEER LOS CORREOS RECIBIDOS POR UN USUARIO.
          //http://localhost:6584/api/correo/leer/recibidos -> 1 parametro: id-usuario.
          get("/recibidos", CorreoController.leerCorreosRecibidos);

          //LEER UN CORREO ENVIADO POR UN USUARIO.
          //http://localhost:6584/api/correo/leer/enviado -> 1 parametro: id-correo.
          get("/enviado", CorreoController.leerCorreoEnviado);

          //LEER LOS CORREOS ENVIADOS POR UN USUARIO.
          //http://localhost:6584/api/correo/leer/enviados -> 2 parametros: id-usuario y borrado.
          get("/enviados", CorreoController.leerCorreosEnviados);

          //LEER LOS CORREOS IMPORTANTES DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/importantes -> 1 parametro: id-usuario.
          get("/importantes", CorreoController.leerCorreosImportantes);

          //LEER LOS CORREOS DESTACADOS DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/destacados -> 1 parametro: id-usuario.
          get("/destacados", CorreoController.leerCorreosDestacados);

          //LEER LOS CORREOS BORRADOS DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/borrados -> 1 parametro: id-usuario.
          get("/borrados", CorreoController.leerCorreosBorrados);

          //LEER LOS CORREOS LEIDOS DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/leidos -> 1 parametro: id-usuario.
          get("/leidos", CorreoController.leerCorreosLeidos);

          //LEER LOS CORREOS NO LEIDOS DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/no-leidos -> 1 parametro: id-usuario.
          get("/no-leidos", CorreoController.leerCorreosNoLeidos);

          //LEER LOS CORREOS SPAM DE UN USUARIO.
          //http://localhost:6584/api/correo/leer/spam -> 1 parametro: id-usuario.
          get("/spam", CorreoController.leerCorreosSpam);

        });

        //ENVIO DE CORREO A DESTINATARIOS.
        path("/enviar", () -> {

          //ENVIAR A UN USUARIO.
          //http://localhost:6584/api/correo/enviar/uno -> 2 parametros: id-correo e id-usuario-receptor.
          post("/uno", CorreoController.enviarCorreoAUno);

          //ENVIAR A UNO O MAS USUARIOS.
          //http://localhost:6584/api/correo/enviar/muchos -> 2 parametros: id-correo e id-usuario-receptor (N veces).
          post("/muchos", CorreoController.enviarCorreoAMuchos);

        });

        //REENVIAR UN CORREO.
        //http://localhost:6584/api/correo/reenviar -> 3 parametros: id-correo, id-usuario-emisor e id-usuario-receptor.
        post("/reenviar", CorreoController.reenviarCorreo);

        //MODIFICAR UN CORREO.
        //http://localhost:6584/api/correo/modificar -> Con el JSON del cuerpo.
        put("/modificar", CorreoController.modificarCorreo);

        //ELIMINAR UN CORREO.
        //http://localhost:6584/api/correo/eliminar
        path("/eliminar", () -> {

          //ELIMINAR UN CORREO ENVIADO.
          //http://localhost:6584/api/correo/eliminar/enviado -> 1 parametro: id-correo.
          delete("/enviado", CorreoController.eliminarCorreoEnviado);

          //ELIMINAR UN CORREO RECIBIDO.
          //http://localhost:6584/api/correo/eliminar/recibido -> 2 parametros: id-correo e id-usuario-receptor.
          delete("/recibido", CorreoController.eliminarCorreoRecibido);

        });

      });

      //ETIQUETA.
      //http://localhost:6584/api/etiqueta
      path("/etiqueta", () -> {

        //CREAR UNA ETIQUETA.
        //http://localhost:6584/api/etiqueta/crear -> Con el JSON del cuerpo.
        post("/crear", EtiquetaController.crearEtiqueta);

        //GET DE ETIQUETA.
        //http://localhost:6584/api/etiqueta/leer
        path("/leer", () -> {

          //LEER UNA ETIQUETA POR ID.
          //http://localhost:6584/api/etiqueta/una -> 1 parametro: id-etiqueta.
          get("/uno", EtiquetaController.leerEtiquetaPorId);

          //LEER ETIQUETAS POR LISTADO.
          //http://localhost:6584/api/etiqueta/leer/listado
          path("/listado", () -> {

            //LEER TODAS LAS ETIQUETAS DE UN USUARIO.
            //http://localhost:6584/api/etiqueta/leer/listado/usuario -> 1 parametro: id-usuario.
            get("/usuario", EtiquetaController.leerEtiquetasUsuario);

            //LEER TODAS LAS ETIQUETAS QUE UN USUARIO HA ASIGNADO A UN CORREO.
            //http://localhost:6584/api/etiqueta/leer/listado/correo -> 2 parametros: id-correo e id-usuario.
            get("/correo", EtiquetaController.leerEtiquetasCorreo);

            //LEER TODAS LAS ETIQUETAS QUE CONTENGAN UN TEXTO ESPECIFICO.
            //http://localhost:6584/api/etiqueta/leer/listado/coincidentes -> 2 parametros: texto e id-usuario.
            get("/coincidentes", EtiquetaController.leerEtiquetasCoincidentes);

          });

        });

        //MODIFICAR UNA ETIQUETA.
        //http://localhost:6584/api/etiqueta/modificar -> Con el JSON del cuerpo.
        put("/modificar", EtiquetaController.modificarEtiqueta);

        //ELIMINAR UNA ETIQUETA.
        //http://localhost:6584/api/etiqueta/eliminar -> 1 parametro: id-etiqueta.
        delete("/eliminar", EtiquetaController.eliminarEtiqueta);

        //AGREGAR UNA ETIQUETA A UN CORREO.
        //http://localhost:6584/api/etiqueta/agregar -> 2 parametros: id-etiqueta e id-correo.
        post("/agregar", EtiquetaController.agregarEtiquetaACorreo);

        //QUITAR UNA ETIQUETA A UN CORREO.
        //http://localhost:6584/api/etiqueta/quitar -> 3 parametros: id-etiqueta, id-correo e id-usuario.
        delete("/quitar", EtiquetaController.quitarEtiquetaACorreo);

      });

      //FILTRO.
      //http://localhost:6584/api/filtro
      path("/filtro", () -> {

        //CREAR UN FILTRO.
        //http://localhost:6584/api/filtro/crear -> Con el JSON del cuerpo.
        post("/crear", FIltroController.crearFiltro);

        //GET DE FILTRO.
        //http:localhost:6584/api/filtro/leer
        path("/leer", () -> {

          //LEER UN FILTRO POR ID.
          //http://localhost:6584/api/filtro/leer/uno -> 1 parametro: id-filtro.
          get("/uno", FIltroController.leerFiltroPorId);

          //LEER TODOS LOS FILTROS DE UN USUARIO.
          //http://localhost:6584/api/filtro/leer/listado -> 1 parametro: id-usuario.
          get("/listado", FIltroController.leerFiltrosUsuario);

        });

        //MODIFICAR UN FILTRO.
        //http://localhost:6584/api/filtro/modificar -> Con el JSON del cuerpo.
        put("/modificar", FIltroController.modificarFiltro);

        //ELIMINAR UN FILTRO.
        //http://localhost:6584/api/filtro/eliminar -> 1 parametro: id-filtro.
        delete("/eliminar", FIltroController.eliminarFiltro);

      });

    });

  }

}

