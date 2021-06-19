package com.gmail;

import com.gmail.dto.CorreoDTO;
import com.gmail.dto.EtiquetaDTO;
import com.gmail.dto.FiltroDTO;
import com.gmail.dto.UsuarioDTO;
import com.gmail.exception.CorreoException;
import com.gmail.exception.FiltroException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.*;
import com.gmail.model.impl.CorreoFactory;
import com.gmail.model.impl.EtiquetaFactory;
import com.gmail.model.impl.UsuarioFactory;
import com.gmail.service.*;

import java.time.LocalDate;

public class Main {


  public static void main(String[] args)
      throws CloneNotSupportedException, SQLDBException, CorreoException, ValidationException, FiltroException {

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
      filtro.setIdUsuario(absUsuario.getIdUsuario());
      filtroService.crear(filtro);
      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar REENVIA EL CORREO A mgonzales999@iua.edu.ar");
      AbsUsuario absUsuario2 = usuarioService.obtenerUno("mgonzales999@iua.edu.ar");
      correoService.reeEnviar(absCorreo.getIdCorreo(),absUsuario.getIdUsuario(),absUsuario2.getIdUsuario());


      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar CREA UNA ETIQUETA LLAMADA PrimeraEtiqueta");
      EtiquetaDTO etiquetaDTO= new EtiquetaDTO();
      AbsEtiqueta absEtiqueta= EtiquetaFactory.buildEtiqueta();
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
      mostarService.abrirCorreo(correoService.leeCorreo(absCorreo.getIdCorreo(), absUsuario.getIdUsuario()));

      System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
      System.out.println("EL USUARIO jvega420@iua.edu.ar CREA UN CORREO, LO MODIFICA Y LUEGO LO ENVIA A gzaragoi782@iua.edu.ar");
      correo.setIdUsuario(absUsuario.getIdUsuario());
      correo.setAsunto("Todo vien?");
      correo.setCuerpo("Yo bien, al menos");
      absCorreo=correoService.crear(correo);
      correo.setAsunto("Todo bien?");
      correo.setIdCorreo(absCorreo.getIdCorreo());
      absCorreo=correoService.modificar(correo);
      correoService.enviar(absCorreo.getIdCorreo(),usuarioService.obtenerUno("gzaragoi782@iua.edu.ar").getIdUsuario());

  }

}
