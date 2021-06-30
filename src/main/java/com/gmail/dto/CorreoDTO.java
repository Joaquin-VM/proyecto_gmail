package com.gmail.dto;

import com.gmail.model.AbsCorreo;
import java.time.LocalDateTime;

public class CorreoDTO {

  int idCorreo;
  int idUsuario;
  String asunto;
  String cuerpo;
  LocalDateTime fechaHora;
  Boolean confirmado;
  Boolean borrado;
  Boolean leido;
  Boolean destacado;
  Boolean importante;

  public CorreoDTO() {

  }

  public CorreoDTO(AbsCorreo correo) {
    this.idCorreo = correo.getIdCorreo();
    this.idUsuario = correo.getIdUsuario();
    this.asunto = correo.getAsunto();
    this.cuerpo = correo.getCuerpo();
    this.fechaHora = correo.getFechaHora();
    this.confirmado = correo.getConfirmado();
    this.borrado = correo.getBorrado();
    this.leido = correo.getLeido();
    this.destacado = correo.getDestacado();
    this.importante = correo.getImportante();
  }

  public int getIdCorreo() {
    return idCorreo;
  }

  public CorreoDTO setIdCorreo(int idCorreo) {
    this.idCorreo = idCorreo;
    return this;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public CorreoDTO setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  public String getAsunto() {
    return asunto;
  }

  public CorreoDTO setAsunto(String asunto) {
    this.asunto = asunto;
    return this;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public CorreoDTO setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
    return this;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public CorreoDTO setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
    return this;
  }

  public Boolean getConfirmado() {
    return confirmado;
  }

  public CorreoDTO setConfirmado(Boolean confirmado) {
    this.confirmado = confirmado;
    return this;
  }

  public Boolean getBorrado() {
    return borrado;
  }

  public CorreoDTO setBorrado(Boolean borrado) {
    this.borrado = borrado;
    return this;
  }

  public Boolean getLeido() {
    return leido;
  }

  public CorreoDTO setLeido(Boolean leido) {
    this.leido = leido;
    return this;
  }

  public Boolean getDestacado() {
    return destacado;
  }

  public CorreoDTO setDestacado(Boolean destacado) {
    this.destacado = destacado;
    return this;
  }

  public Boolean getImportante() {
    return importante;
  }

  public CorreoDTO setImportante(Boolean importante) {
    this.importante = importante;
    return this;
  }

}
