package com.gmail.model.impl;

import com.gmail.dto.CorreoDTO;
import com.gmail.model.AbsCorreo;
import java.time.LocalDateTime;
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
  public List<String> getUsuariosQueRecibieron() {
    return usuariosQueRecibieron;
  }

  @Override
  public AbsCorreo setUsuariosQueRecibieron(List<String> usuariosQueRecibieron) {
    this.usuariosQueRecibieron = usuariosQueRecibieron;
    return this;
  }

  @Override
  public String toString() {
    return "Correo{" +
        "idCorreo=" + idCorreo +
        ", idUsuario=" + idUsuario +
        ", asunto='" + asunto + '\'' +
        ", cuerpo='" + cuerpo + '\'' +
        ", fechaHora=" + fechaHora +
        ", confirmado=" + confirmado +
        ", borrado=" + borrado +
        ", leido=" + leido +
        ", destacado=" + destacado +
        ", importante=" + importante +
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

    return this.idCorreo == correo.getIdCorreo();
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
        (this.importante ? 1 : 0);
  }

}
