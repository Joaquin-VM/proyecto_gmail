package com.gmail.model;

class Filtro extends AbsFiltro {

  @Override
  public int getIdFiltro() {
    return this.idFiltro;
  }

  @Override
  public Filtro setIdFiltro(int idFiltro) {
    this.idFiltro = idFiltro;
    return this;
  }

  @Override
  public int getIdUsuario() {
    return this.idUsuario;
  }

  @Override
  public Filtro setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }


  @Override
  public int getIdEmisor() {
    return this.idEmisor;
  }

  @Override
  public Filtro setIdEmisor(int idEmisor) {
    this.idEmisor = idEmisor;
    return this;
  }

  @Override
  public int getIdReceptor() {
    return this.idReceptor;
  }

  @Override
  public Filtro setIdReceptor(int idReceptor) {
    this.idReceptor = idReceptor;
    return this;
  }

  @Override
  public String getAsunto() {
    return this.asunto;
  }

  @Override
  public Filtro setAsunto(String asunto) {
    this.asunto = asunto;
    return this;
  }

  @Override
  public String getContiene() {
    return this.contiene;
  }

  @Override
  public Filtro setContiene(String contiene) {
    this.contiene = contiene;
    return this;
  }

  @Override
  public Boolean getLeido() {
    return this.leido;
  }

  @Override
  public AbsFiltro setLeido(Boolean leido) {
    this.leido = leido;
    return this;
  }

  @Override
  public Boolean getDestacar() {
    return this.destacar;
  }

  @Override
  public AbsFiltro setDestacar(Boolean destacar) {
    this.destacar = destacar;
    return this;
  }

  @Override
  public Boolean getImportante() {
    return this.importante;
  }

  @Override
  public AbsFiltro setImportante(Boolean importante) {
    this.importante = importante;
    return this;
  }

  @Override
  public Boolean getEliminar() {
    return this.eliminar;
  }

  @Override
  public AbsFiltro setEliminar(Boolean eliminar) {
    this.eliminar = eliminar;
    return this;
  }

  @Override
  public Boolean getSpam() {
    return this.spam;
  }

  @Override
  public AbsFiltro setSpam(Boolean spam) {
    this.spam = spam;
    return this;
  }

  @Override
  public int getIdEtiqueta() {
    return this.idEtiqueta;
  }

  @Override
  public AbsFiltro setIdEtiqueta(int idEtiqueta) {
    this.idEtiqueta = idEtiqueta;
    return this;
  }

  @Override
  public int getIdUsuarioReenviar() {
    return this.idUsuarioReenviar;
  }

  @Override
  public AbsFiltro setIdUsuarioReenviar(int idUsuarioReenviar) {
    this.idUsuarioReenviar = idUsuarioReenviar;
    return this;
  }

}
