package com.gmail.model.impl;

import com.gmail.dto.CorreoDTO;
import com.gmail.model.AbsCorreo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Correo extends AbsCorreo {

  public Correo() {
    super();
  }

  public Correo(CorreoDTO dto) {
    super(dto);
  }

  @Override
  public int getIdCorreo() {
    return this.idCorreo;
  }

  @Override
  public Correo setIdCorreo(int idCorreo) {
    this.idCorreo = idCorreo;
    return this;
  }

  @Override
  public int getIdUsuario() {
    return this.idUsuario;
  }

  @Override
  public Correo setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  @Override
  public String getAsunto() {
    return this.asunto;
  }

  @Override
  public Correo setAsunto(String asunto) {
    this.asunto = asunto;
    return this;
  }

  @Override
  public String getCuerpo() {
    return cuerpo;
  }

  @Override
  public Correo setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
    return this;
  }

  @Override
  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  @Override
  public Correo setFechaHora(LocalDateTime fecha) {
    this.fechaHora = fecha;
    return this;
  }

  @Override
  public Boolean getConfirmado() {
    return this.confirmado;
  }

  @Override
  public Correo setConfirmado(Boolean confirmado) {
    this.confirmado = confirmado;
    return this;
  }

  @Override
  public Boolean getBorrado() {
    return this.borrado;
  }

  @Override
  public AbsCorreo setBorrado(Boolean borrado) {
    this.borrado = borrado;
    return this;
  }

  @Override
  public Boolean getLeido() {
    return this.leido;
  }

  @Override
  public AbsCorreo setLeido(Boolean leido) {
    this.leido = leido;
    return this;
  }

  @Override
  public Boolean getDestacado() {
    return this.destacado;
  }

  @Override
  public AbsCorreo setDestacado(Boolean destacado) {
    this.destacado = destacado;
    return this;
  }

  @Override
  public Boolean getImportante() {
    return this.importante;
  }

  @Override
  public AbsCorreo setImportante(Boolean importante) {
    this.importante = importante;
    return this;
  }

  @Override
  public Boolean getSpam() {
    return this.spam;
  }

  @Override
  public AbsCorreo setSpam(Boolean spam) {
    this.spam = spam;
    return this;
  }

  @Override
  public List<String> getDirCorreosReceptores() {
    return dirCorreosReceptores;
  }

  @Override
  public AbsCorreo setDirCorreosReceptores(List<String> dirCorreosReceptores) {
    this.dirCorreosReceptores = dirCorreosReceptores;
    return this;
  }

  @Override
  public String toString() {
    return "AbsCorreo{" +
        "idCorreo=" + this.idCorreo +
        ", idUsuario=" + this.idUsuario +
        ", asunto='" + this.asunto + '\'' +
        ", cuerpo='" + this.cuerpo + '\'' +
        ", fechaHora=" + this.fechaHora +
        ", confirmado=" + this.confirmado +
        ", borrado=" + this.borrado +
        ", leido=" + this.leido +
        ", destacado=" + this.destacado +
        ", importante=" + this.importante +
        ", spam=" + this.spam +
        ", dirCorreosReceptores=" + this.dirCorreosReceptores +
        '}';
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Correo)) {
      return false;
    }

    AbsCorreo correo = (AbsCorreo) o;

    return this.idCorreo == correo.getIdCorreo() && this.dirCorreosReceptores
        .equals(correo.getDirCorreosReceptores());
  }

  @Override
  public int hashCode() {
    return this.idCorreo + this.idUsuario +
        this.asunto.length() + this.cuerpo.length() / 10 +
        this.fechaHora.getYear() + this.fechaHora.getMonthValue() +
        this.fechaHora.getDayOfYear() + this.fechaHora.getHour() +
        this.fechaHora.getMinute() + this.fechaHora.getSecond() +
        (this.confirmado ? 1 : 0) + (this.borrado ? 1 : 0) +
        (this.leido ? 1 : 0) + (this.destacado ? 1 : 0) +
        (this.importante ? 1 : 0) + (this.spam ? 1 : 0) +
        this.dirCorreosReceptores.size();
  }

  @Override
  public Object clone() {

    List<String> dirCorreosCopia = new ArrayList<>();

    for(int i = 0; i < this.dirCorreosReceptores.size(); ++i){
      dirCorreosCopia.add(this.dirCorreosReceptores.get(i));
    }

    return new Correo().setIdCorreo(this.idCorreo).setIdUsuario(this.idUsuario)
        .setAsunto(this.asunto).setCuerpo(this.cuerpo).setFechaHora(this.fechaHora)
        .setConfirmado(this.confirmado).setBorrado(this.borrado).setLeido(this.leido)
        .setDestacado(this.destacado).setImportante(this.importante).setSpam(this.spam)
        .setDirCorreosReceptores(dirCorreosCopia);

  }

}
