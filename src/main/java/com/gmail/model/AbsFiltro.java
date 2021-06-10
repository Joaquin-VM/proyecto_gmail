package com.gmail.model;

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
  int idEtiqueta;
  int idUsuarioReenviar;

  public abstract int getIdFiltro();

  public abstract AbsFiltro setIdFiltro(int idUsuario);

  public abstract int getIdUsuario();

  public abstract AbsFiltro setIdUsuario(int idUsuario);

  public abstract String getEmisor();

  public abstract AbsFiltro setEmisor(String emisor);

  public abstract String getReceptor();

  public abstract AbsFiltro setReceptor(String receptor);

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
        ", emisor='" + idEmisor + '\'' +
        ", receptor='" + idReceptor + '\'' +
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
    return idFiltro == absFiltro.idFiltro &&
        Objects.equals(idEmisor, absFiltro.idEmisor) &&
        Objects.equals(idReceptor, absFiltro.idReceptor) &&
        Objects.equals(asunto, absFiltro.asunto) &&
        Objects.equals(contiene, absFiltro.contiene);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idFiltro, idEmisor, idReceptor, asunto, contiene);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
