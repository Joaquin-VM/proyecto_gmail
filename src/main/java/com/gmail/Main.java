package com.gmail;

import com.gmail.dao.CorreoDAO;
import com.gmail.dao.EtiquetaDAO;
import com.gmail.dao.FiltroDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.model.AbsCorreo;
import com.gmail.model.AbsEtiqueta;
import com.gmail.model.AbsFiltro;
import com.gmail.model.AbsUsuario;
import com.gmail.model.CorreoFactory;
import com.gmail.model.EtiquetaFactory;
import com.gmail.model.FiltroFactory;
import com.gmail.model.UsuarioFactory;
import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {

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

    //USUARIO YA SE PROBO.AAAAA

    //MODIFICACION YO.Hola

  }
}
