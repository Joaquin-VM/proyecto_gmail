package com.gmail;



import com.gmail.dao.UsuarioDAO;
import com.gmail.dto.CorreoDTO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.*;
import com.gmail.service.*;

import java.time.LocalDate;

public class Main {


  public static void main(String[] args) throws CloneNotSupportedException, SQLDBException, CorreoException, ValidationException {

//    UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//    AbsUsuario usuario = UsuarioFactory.buildUsuario();
//
//    usuario.setNombre("Pablito").setApellido("Perez").setCorreo("hola3@gmail.com")
//        .setContrasenia("a").setFechaNacimiento(
//        LocalDate.now()).setSexo("Masculino").setTelefono("54 9 32313213");
//
//    try {
//      usuarioDAO.addUsuario(usuario);
//    } catch (SQLError throwables) {
//      System.out.println("Error al crear el usuario.");
//      ;
//    }

    //FALTA PROBAR QUITARETIQUETA Y CLASFICAR.

    //FILTRO.

//    AbsFiltro filtro = FiltroFactory.buildFiltro();
//    filtro = FiltroDAO.getFiltro(1);
//    filtro.setDestacar(false);
//    FiltroDAO.addFiltro(filtro);

//    filtro = FiltroDAO.getFiltro(1);
//    filtro.setLeido(false).setIdEtiqueta(1);

//    System.out.println(FiltroDAO.getFiltro(1));
//    FiltroDAO.deleteFiltro(1);
//    System.out.println(FiltroDAO.listarFiltrosUsuario(1));

    //FiltroDAO.updateFiltro(filtro);

    //CORREO.

    //AbsCorreo correo = CorreoFactory.buildCorreo();
    //AbsCorreo correo2 = CorreoFactory.buildCorreo();

    //correo.setIdUsuario(1).setBorrado(true).setLeido(false).setImportante(false).setDestacado(false).setConfirmado(true);

    //correo2.setIdCorreo(4).setIdUsuario(1).setBorrado(true).setLeido(true).setImportante(true).setDestacado(true).setConfirmado(true);

    //CorreoDAO.updateCorreo(correo2);

    //System.out.println(CorreoDAO.getCorreo(1));

    //System.out.println(CorreoDAO.getCorreosEnviados(1, CorreoDAO.getCorreo(1).getBorrado()));

    //System.out.println(CorreoDAO.getCorreo(1));

    //CorreoDAO.deleteCorreo(2);

    //CorreoDAO.enviarCorreo(1, 4);

    //CorreoDAO.enviarCorreo(1, new int[] {2,3});

    //ETIQUETA.

//    AbsEtiqueta etiqueta1 = EtiquetaFactory.buildEtiqueta();
//    etiqueta1.setNombreEtiqueta("Personal").setIdUsuario(1);
//
//    AbsEtiqueta etiqueta2 = EtiquetaFactory.buildEtiqueta();
//    etiqueta2.setNombreEtiqueta("Club").setIdUsuario(1);
//
//    AbsEtiqueta etiqueta3 = EtiquetaFactory.buildEtiqueta();
//    etiqueta3.setNombreEtiqueta("Universidad").setIdUsuario(2);
//
//    AbsEtiqueta etiqueta4 = EtiquetaFactory.buildEtiqueta();
//    etiqueta1.setIdEtiqueta(1).setNombreEtiqueta("Hogar").setIdUsuario(1);

//    EtiquetaDAO.addEtiqueta(etiqueta1);
//    EtiquetaDAO.addEtiqueta(etiqueta2);
//    EtiquetaDAO.addEtiqueta(etiqueta3);
//    System.out.println();
//    System.out.println(EtiquetaDAO.listarEtiquetasUsuario(etiqueta1.getIdUsuario()));
//    System.out.println();
//    EtiquetaDAO.updateEtiqueta(etiqueta4);
//    System.out.println(EtiquetaDAO.getEtiqueta(6));
//    System.out.println();
//    EtiquetaDAO.deleteEtiqueta(5);

    //USUARIO YA SE PROBO.

      UsuarioService usuarioService=new UsuarioService();
      CorreoService correoService=new CorreoService();
      EtiquetaService etiquetaService=new EtiquetaService();
      FiltroService filtroService=new FiltroService();
      MostarService mostarService=new MostarService();
      UsuarioDTO usuario = new UsuarioDTO();
      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("CARGAMOS 3 USUARIOS A MODO DE EJEMPLO");
      usuario.setNombre("Gaston");
      usuario.setApellido("Zaragosi");
      usuario.setCorreo("gzaragoi782@iua.edu.ar");
      usuario.setContrasenia("duck");
      usuario.setFechaNacimiento(LocalDate.now());
      usuario.setSexo("Masculino");
      usuario.setTelefono("54 9 32313213");
      usuarioService.crear(usuario);
      ////////////
      usuario.setNombre("Joaquin");
      usuario.setApellido("Vega");
      usuario.setCorreo("jvega420@iua.edu.ar");
      usuario.setContrasenia("horse");
      usuario.setFechaNacimiento(LocalDate.now());
      usuario.setSexo("Masculino");
      usuario.setTelefono("54 9 323786952");
      usuarioService.crear(usuario);
      ////////////
      usuario.setNombre("Marta");
      usuario.setApellido("Gonzales");
      usuario.setCorreo("mgonzales999@iua.edu.ar");
      usuario.setContrasenia("mouse");
      usuario.setFechaNacimiento(LocalDate.now());
      usuario.setSexo("Femenino");
      usuario.setTelefono("54 9 32313213");
      usuarioService.crear(usuario);

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("OBTENEMOS UN USUARIO POR CORREO Y POR ID");
      AbsUsuario absUsuario= UsuarioFactory.buildUsuario();
      absUsuario = usuarioService.obtenerUno("gzaragoi782@iua.edu.ar");
      System.out.println(absUsuario.getIdUsuario());
      System.out.println(usuarioService.obtenerUno(1));

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO gzaragoi782@iua.edu.ar CREA UN CORREO");
      CorreoDTO correo = new CorreoDTO();
      AbsCorreo absCorreo = CorreoFactory.buildCorreo();
      correo.setIdUsuario(absUsuario.getIdUsuario());
      correo.setAsunto("Hola");
      correo.setCuerpo("Te queria contar que hoy fue un buen dia para mi");
      absCorreo=correoService.crear(correo);

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO gzaragoi782@iua.edu.ar ENVIA EL CORREO A jvega420@iua.edu.ar");
      absUsuario = usuarioService.obtenerUno("jvega420@iua.edu.ar");
      correoService.enviar(absCorreo.getIdCorreo(),absUsuario.getIdUsuario());

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO gzaragoi782@iua.edu.ar BORRA EL CORREO QUE ENVIO");
      correoService.eliminarEnviado(absCorreo.getIdCorreo());

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar CREA UN FILTRO PARA DESTACAR AUTOMATICAMENTE LOS CORREOS DE ASUNTO Hola");
      FiltroDTO filtro = new FiltroDTO();
      filtro.setDestacar(true);
      filtro.setAsunto("Hola");

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar REENVIA EL CORREO A mgonzales999@iua.edu.ar");
      AbsUsuario absUsuario2 = usuarioService.obtenerUno("mgonzales999@iua.edu.ar");
      correoService.reeEnviar(absCorreo.getIdCorreo(),absUsuario.getIdUsuario(),absUsuario2.getIdUsuario());
      absCorreo=correoService.crear(correo);


      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar CREEA UNA ETIQUETA LLAMADA PrimeraEtiqueta");
      EtiquetaDTO etiquetaDTO= new EtiquetaDTO();
      AbsEtiqueta absEtiqueta=EtiquetaFactory.buildEtiqueta();
      etiquetaDTO.setIdUsuario(absUsuario.getIdUsuario());
      etiquetaDTO.setNombreEtiqueta("PrimeraEtiqueta");
      absEtiqueta=etiquetaService.crear(etiquetaDTO);

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar ASIGNA LA ETIQUETA PrimeraEtiqueta A EL CORREO QUE REENVIO");
      etiquetaService.agregarEtiquetaACorreo(absEtiqueta.getIdEtiqueta(),absCorreo.getIdCorreo());

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar VE SUS MAIL ENVIADOS");
      mostarService.mostrarCorreos(correoService.obtenerEnviados(absUsuario.getIdUsuario(), false));

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO mgonzales999@iua.edu.ar CREA Y ENVIA UN CORREO A jvega420@iua.edu.ar");
      correo.setIdUsuario(absUsuario2.getIdUsuario());
      correo.setAsunto("Como andas?");
      correo.setCuerpo("Yo bien, al menos");
      absCorreo=correoService.crear(correo);
      correoService.enviar(absCorreo.getIdCorreo(),absUsuario.getIdUsuario());

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar VE SUS MAIL RECIBIDOS");
      mostarService.mostrarCorreos(correoService.obtenerRecibidos(absUsuario.getIdUsuario(),false));

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar LISTA SUS ETIQUETAS");
      for(AbsEtiqueta etiqueta:etiquetaService.listarEtiquetasUsuario(absUsuario.getIdUsuario())){
          System.out.println(etiqueta);
      }

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar LEE EL CORREO QUE LE ENVIO mgonzales999@iua.edu.ar");
      mostarService.abrirCorreo(correoService.leeCorreo(absUsuario.getIdUsuario(), absCorreo.getIdCorreo()));

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar CREA UN CORREO, LO MODIFICA Y LUEGO LO ENVIA A gzaragoi782@iua.edu.ar");
      correo.setIdUsuario(absUsuario.getIdUsuario());
      correo.setAsunto("Todo vien?");
      correo.setCuerpo("Yo bien, al menos");
      absCorreo=correoService.crear(correo);
      correo.setAsunto("Todo bien?");
      absCorreo=correoService.modificar(correo);
      correoService.enviar(absCorreo.getIdCorreo(),usuarioService.obtenerUno("gzaragoi782@iua.edu.ar").getIdUsuario());







  }

}
