package com.gmail.model;

import com.gmail.dto.FiltroDTO;
import java.util.Objects;

public abstract class AbsFiltro implements Cloneable {

  int idFiltro;
  int idUsuario;
  int idEmisor;
  int idReceptor;
  String asunto;
  String contiene;
  Boolean leido;
  Boolean destacar;
  Boolean importante;
  Boolean eliminar;
  Boolean spam;
  int idEtiqueta=0;
  int idUsuarioReenviar=0;

  public AbsFiltro(){}

  public AbsFiltro(FiltroDTO dto){
    this.idFiltro = dto.getIdFiltro();
    this.idUsuario = dto.getIdUsuario();
    this.idEmisor = dto.getIdEmisor();
    this.idReceptor = dto.getIdReceptor();
    this.asunto = dto.getAsunto();
    this.contiene = dto.getContiene();
    this.leido = dto.getLeido();
    this.destacar = dto.getDestacar();
    this.importante = dto.getImportante();
    this.eliminar = dto.getEliminar();
    this.spam = dto.getSpam();
    this.idEtiqueta = dto.getIdEtiqueta();
    this.idUsuarioReenviar = dto.getIdUsuarioReenviar();
  }

  public abstract int getIdFiltro();

  public abstract AbsFiltro setIdFiltro(int idUsuario);

  public abstract int getIdUsuario();

  public abstract AbsFiltro setIdUsuario(int idUsuario);

  public abstract int getIdEmisor();

  public abstract AbsFiltro setIdEmisor(int idEmisor);

  public abstract int getIdReceptor();

  public abstract AbsFiltro setIdReceptor(int idReceptor);

  public abstract String getAsunto();

  public abstract AbsFiltro setAsunto(String asunto);

  public abstract String getContiene();

  public abstract AbsFiltro setContiene(String contiene);

  public abstract Boolean getLeido();

  public abstract AbsFiltro setLeido(Boolean leido);

  public abstract Boolean getDestacar();

  public abstract AbsFiltro setDestacar(Boolean destacar);

  public abstract Boolean getImportante();

  public abstract AbsFiltro setImportante(Boolean importante);

  public abstract Boolean getEliminar();

  public abstract AbsFiltro setEliminar(Boolean eliminar);

  public abstract Boolean getSpam();

  public abstract AbsFiltro setSpam(Boolean spam);

  public abstract int getIdEtiqueta();

  public abstract AbsFiltro setIdEtiqueta(int idEtiqueta);

  public abstract int getIdUsuarioReenviar();

  public abstract AbsFiltro setIdUsuarioReenviar(int idUsuarioReenviar);

  @Override
  public String toString() {
    return "AbsFiltro{" +
        "idFiltro=" + idFiltro +
        ", idUsuario=" + idUsuario +
        ", idEmisor=" + idEmisor +
        ", idReceptor=" + idReceptor +
        ", asunto='" + asunto + '\'' +
        ", contiene='" + contiene + '\'' +
        ", leido=" + leido +
        ", destacar=" + destacar +
        ", importante=" + importante +
        ", eliminar=" + eliminar +
        ", spam=" + spam +
        ", idEtiqueta=" + idEtiqueta +
        ", idUsuarioReenviar=" + idUsuarioReenviar +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbsFiltro)) {
      return false;
    }
    AbsFiltro absFiltro = (AbsFiltro) o;
    return idFiltro == absFiltro.idFiltro && idUsuario == absFiltro.idUsuario
        && idEmisor == absFiltro.idEmisor && idReceptor == absFiltro.idReceptor
        && idEtiqueta == absFiltro.idEtiqueta && idUsuarioReenviar == absFiltro.idUsuarioReenviar
        && Objects.equals(asunto, absFiltro.asunto) && Objects
        .equals(contiene, absFiltro.contiene) && Objects.equals(leido, absFiltro.leido)
        && Objects.equals(destacar, absFiltro.destacar) && Objects
        .equals(importante, absFiltro.importante) && Objects
        .equals(eliminar, absFiltro.eliminar) && Objects.equals(spam, absFiltro.spam);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(idFiltro, idUsuario, idEmisor, idReceptor, asunto, contiene, leido, destacar,
            importante, eliminar, spam, idEtiqueta, idUsuarioReenviar);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
