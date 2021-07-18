package com.gmail.dto;

import com.gmail.model.AbsFiltro;

public class FiltroDTO {

  private int idFiltro;
  private int idUsuario;
  private int idEmisor;
  private int idReceptor;
  private String asunto;
  private String contiene;
  private Boolean leido;
  private Boolean destacar;
  private Boolean importante;
  private Boolean eliminar;
  private Boolean spam;
  private int idEtiqueta;
  private int idUsuarioReenviar;

  public FiltroDTO() {

  }

  public FiltroDTO(AbsFiltro filtro) {
    this.idFiltro = filtro.getIdFiltro();
    this.idUsuario = filtro.getIdUsuario();
    this.idEmisor = filtro.getIdEmisor();
    this.idReceptor = filtro.getIdReceptor();
    this.asunto = filtro.getAsunto();
    this.contiene = filtro.getContiene();
    this.leido = filtro.getLeido();
    this.destacar = filtro.getDestacar();
    this.importante = filtro.getImportante();
    this.eliminar = filtro.getEliminar();
    this.spam = filtro.getSpam();
    this.idEtiqueta = filtro.getIdEtiqueta();
    this.idUsuarioReenviar = filtro.getIdUsuarioReenviar();
  }

  public int getIdFiltro() {
    return idFiltro;
  }

  public FiltroDTO setIdFiltro(int idFiltro) {
    this.idFiltro = idFiltro;
    return this;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public FiltroDTO setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  public int getIdEmisor() {
    return idEmisor;
  }

  public FiltroDTO setIdEmisor(int idEmisor) {
    this.idEmisor = idEmisor;
    return this;
  }

  public int getIdReceptor() {
    return idReceptor;
  }

  public FiltroDTO setIdReceptor(int idReceptor) {
    this.idReceptor = idReceptor;
    return this;
  }

  public String getAsunto() {
    return asunto;
  }

  public FiltroDTO setAsunto(String asunto) {
    this.asunto = asunto;
    return this;
  }

  public String getContiene() {
    return contiene;
  }

  public FiltroDTO setContiene(String contiene) {
    this.contiene = contiene;
    return this;
  }

  public Boolean getLeido() {
    return leido;
  }

  public FiltroDTO setLeido(Boolean leido) {
    this.leido = leido;
    return this;
  }

  public Boolean getDestacar() {
    return destacar;
  }

  public FiltroDTO setDestacar(Boolean destacar) {
    this.destacar = destacar;
    return this;
  }

  public Boolean getImportante() {
    return importante;
  }

  public FiltroDTO setImportante(Boolean importante) {
    this.importante = importante;
    return this;
  }

  public Boolean getEliminar() {
    return eliminar;
  }

  public FiltroDTO setEliminar(Boolean eliminar) {
    this.eliminar = eliminar;
    return this;
  }

  public Boolean getSpam() {
    return spam;
  }

  public FiltroDTO setSpam(Boolean spam) {
    this.spam = spam;
    return this;
  }

  public int getIdEtiqueta() {
    return idEtiqueta;
  }

  public FiltroDTO setIdEtiqueta(int idEtiqueta) {
    this.idEtiqueta = idEtiqueta;
    return this;
  }

  public int getIdUsuarioReenviar() {
    return idUsuarioReenviar;
  }

  public FiltroDTO setIdUsuarioReenviar(int idUsuarioReenviar) {
    this.idUsuarioReenviar = idUsuarioReenviar;
    return this;
  }

}
