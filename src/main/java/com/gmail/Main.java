package com.gmail;

import com.gmail.dto.CorreoDTO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.FiltroException;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.AbsUsuario;
import com.gmail.model.impl.CorreoFactory;
import com.gmail.model.impl.EtiquetaFactory;
import com.gmail.model.impl.UsuarioFactory;
import com.gmail.service.MostrarService;
import com.gmail.service.impl.CorreoService;
import com.gmail.service.impl.EtiquetaService;
import com.gmail.service.impl.FiltroService;
import com.gmail.service.impl.UsuarioService;
import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {

    //NO USAMOS NUNCA LAS FACTORIES.
    UsuarioService usuarioService = new UsuarioService();
    CorreoService correoService = new CorreoService();
    EtiquetaService etiquetaService = new EtiquetaService();
    FiltroService filtroService = new FiltroService();
    MostrarService mostrarService = new MostrarService();
    UsuarioDTO usuario = new UsuarioDTO();

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("CARGAMOS 3 USUARIOS A MODO DE EJEMPLO");
    usuario.setNombre("Gaston");
    usuario.setApellido("Zaragosi");
    usuario.setCorreo("gzaragoi782@iua.edu.ar");
    usuario.setContrasenia("duck");
    usuario.setFechaNacimiento(LocalDate.now());
    usuario.setSexo("Masculino");
    usuario.setTelefono("54 9 32313213");

    try {
      usuarioService.crear(usuario);
    } catch (ValidationException | NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    ////////////
    usuario.setNombre("Joaquin");
    usuario.setApellido("Vega");
    usuario.setCorreo("jvega420@iua.edu.ar");
    usuario.setContrasenia("horse");
    usuario.setFechaNacimiento(LocalDate.now());
    usuario.setSexo("Masculino");
    usuario.setTelefono("54 9 323786952");

    try {
      usuarioService.crear(usuario);
    } catch (ValidationException | NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    ////////////
    usuario.setNombre("Marta");
    usuario.setApellido("Gonzales");
    usuario.setCorreo("mgonzales999@iua.edu.ar");
    usuario.setContrasenia("mouse");
    usuario.setFechaNacimiento(LocalDate.now());
    usuario.setSexo("Femenino");
    usuario.setTelefono("54 9 32313213");

    try {
      usuarioService.crear(usuario);
    } catch (ValidationException | NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("OBTENEMOS UN USUARIO POR CORREO Y POR ID");
    AbsUsuario absUsuario = UsuarioFactory.buildUsuario();

    try {
      absUsuario = usuarioService.obtenerUno("gzaragoi782@iua.edu.ar");
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(absUsuario.getIdUsuario());

    try {
      System.out.println(usuarioService.obtenerUno(1));
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO gzaragoi782@iua.edu.ar CREA UN CORREO");
    CorreoDTO correo = new CorreoDTO();
    AbsCorreo absCorreo = CorreoFactory.buildCorreo();
    correo.setIdUsuario(absUsuario.getIdUsuario());
    correo.setAsunto("Hola");
    correo.setCuerpo("Te queria contar que hoy fue un buen dia para mi");

    try {
      absCorreo = correoService.crear(correo);
    } catch (CorreoException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO gzaragoi782@iua.edu.ar ENVIA EL CORREO A jvega420@iua.edu.ar");

    try {
      absUsuario = usuarioService.obtenerUno("jvega420@iua.edu.ar");
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    try {
      correoService.enviar(absCorreo.getIdCorreo(), absUsuario.getIdUsuario());
    } catch (CloneNotSupportedException | CorreoException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO gzaragoi782@iua.edu.ar BORRA EL CORREO QUE ENVIO");

    try {
      correoService.eliminarEnviado(absCorreo.getIdCorreo());
    } catch (CorreoException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println(
        "EL USUARIO jvega420@iua.edu.ar CREA UN FILTRO PARA DESTACAR AUTOMATICAMENTE LOS CORREOS DE ASUNTO Hola");
    FiltroDTO filtro = new FiltroDTO();
    filtro.setDestacar(true);
    filtro.setAsunto("Hola");
    filtro.setIdUsuario(absUsuario.getIdUsuario());

    try {
      filtroService.crear(filtro);
    } catch (FiltroException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out
        .println("EL USUARIO jvega420@iua.edu.ar REENVIA EL CORREO A mgonzales999@iua.edu.ar");
    AbsUsuario absUsuario2 = null;

    try {
      absUsuario2 = usuarioService.obtenerUno("mgonzales999@iua.edu.ar");
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    try {
      correoService.reeEnviar(absCorreo.getIdCorreo(), absUsuario.getIdUsuario(),
          absUsuario2.getIdUsuario());
    } catch (CloneNotSupportedException | CorreoException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO jvega420@iua.edu.ar CREA UNA ETIQUETA LLAMADA PrimeraEtiqueta");
    EtiquetaDTO etiquetaDTO = new EtiquetaDTO();
    AbsEtiqueta absEtiqueta = EtiquetaFactory.buildEtiqueta();
    etiquetaDTO.setIdUsuario(absUsuario.getIdUsuario());
    etiquetaDTO.setNombreEtiqueta("PrimeraEtiqueta");

    try {
      absEtiqueta = etiquetaService.crear(etiquetaDTO);
    } catch (ValidationException | NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println(
        "EL USUARIO jvega420@iua.edu.ar ASIGNA LA ETIQUETA PrimeraEtiqueta A EL CORREO QUE REENVIO");

    try {
      etiquetaService.agregarEtiquetaACorreo(absEtiqueta.getIdEtiqueta(), absCorreo.getIdCorreo());
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO jvega420@iua.edu.ar VE SUS MAIL ENVIADOS");

    try {
      mostrarService
          .mostrarCorreos(correoService.obtenerEnviados(absUsuario.getIdUsuario(), false));
    } catch (CorreoException | SQLDBException | NotFoundException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out
        .println("EL USUARIO mgonzales999@iua.edu.ar CREA Y ENVIA UN CORREO A jvega420@iua.edu.ar");
    correo.setIdUsuario(absUsuario2.getIdUsuario());
    correo.setAsunto("Como andas?");
    correo.setCuerpo("Yo bien, al menos");

    try {
      absCorreo = correoService.crear(correo);
    } catch (CorreoException | SQLDBException e) {
      e.getMessage();
    }

    try {
      correoService.enviar(absCorreo.getIdCorreo(), absUsuario.getIdUsuario());
    } catch (CloneNotSupportedException | CorreoException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO jvega420@iua.edu.ar VE SUS MAIL RECIBIDOS");

    try {
      mostrarService
          .mostrarCorreos(correoService.obtenerRecibidos(absUsuario.getIdUsuario(), false));
    } catch (CorreoException | SQLDBException | NotFoundException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("EL USUARIO jvega420@iua.edu.ar LISTA SUS ETIQUETAS");

    try {
      for (AbsEtiqueta etiqueta : etiquetaService
          .listarEtiquetasUsuario(absUsuario.getIdUsuario())) {
        System.out.println(etiqueta);
      }
    } catch (NotFoundException | SQLDBException e) {
      e.getMessage();
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println(
        "EL USUARIO jvega420@iua.edu.ar LEE EL CORREO QUE LE ENVIO mgonzales999@iua.edu.ar");

    try {
      mostrarService.abrirCorreo(correoService.leeCorreo(absCorreo.getIdCorreo(), absUsuario.getIdUsuario()));
      System.out.println("hola");
    } catch (CorreoException | SQLDBException | NotFoundException e) {
      System.out.println(e.getMessage());
    }

    System.out.println(
        "////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println(
        "EL USUARIO jvega420@iua.edu.ar CREA UN CORREO, LO MODIFICA Y LUEGO LO ENVIA A gzaragoi782@iua.edu.ar");
    correo.setIdUsuario(absUsuario.getIdUsuario());
    correo.setAsunto("Todo vien?");
    correo.setCuerpo("Yo bien, al menos");

    try {
      absCorreo = correoService.crear(correo);
    } catch (CorreoException | SQLDBException e) {
      e.getMessage();
    }

    correo.setAsunto("Todo bien?");
    correo.setIdCorreo(absCorreo.getIdCorreo());

    try {
      absCorreo = correoService.modificar(correo);
    } catch (CorreoException | SQLDBException e) {
      e.getMessage();
    }

    try {
      correoService.enviar(absCorreo.getIdCorreo(),
          usuarioService.obtenerUno("gzaragoi782@iua.edu.ar").getIdUsuario());
    } catch (CloneNotSupportedException | CorreoException | NotFoundException | SQLDBException e) {
      e.getMessage();
    }

  }

}
