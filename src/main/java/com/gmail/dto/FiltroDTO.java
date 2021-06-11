package com.gmail.dto;

public class FiltroDTO {

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

  public int getIdFiltro() {
    return idFiltro;
  }

  public void setIdFiltro(int idFiltro) {
    this.idFiltro = idFiltro;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public int getIdEmisor() {
    return idEmisor;
  }

  public void setIdEmisor(int idEmisor) {
    this.idEmisor = idEmisor;
  }

  public int getIdReceptor() {
    return idReceptor;
  }

  public void setIdReceptor(int idReceptor) {
    this.idReceptor = idReceptor;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getContiene() {
    return contiene;
  }

  public void setContiene(String contiene) {
    this.contiene = contiene;
  }

  public Boolean getLeido() {
    return leido;
  }

  public void setLeido(Boolean leido) {
    this.leido = leido;
  }

  public Boolean getDestacar() {
    return destacar;
  }

  public void setDestacar(Boolean destacar) {
    this.destacar = destacar;
  }

  public Boolean getImportante() {
    return importante;
  }

  public void setImportante(Boolean importante) {
    this.importante = importante;
  }

  public Boolean getEliminar() {
    return eliminar;
  }

  public void setEliminar(Boolean eliminar) {
    this.eliminar = eliminar;
  }

  public Boolean getSpam() {
    return spam;
  }

  public void setSpam(Boolean spam) {
    this.spam = spam;
  }

  public int getIdEtiqueta() {
    return idEtiqueta;
  }

  public void setIdEtiqueta(int idEtiqueta) {
    this.idEtiqueta = idEtiqueta;
  }

  public int getIdUsuarioReenviar() {
    return idUsuarioReenviar;
  }

  public void setIdUsuarioReenviar(int idUsuarioReenviar) {
    this.idUsuarioReenviar = idUsuarioReenviar;
  }

}
