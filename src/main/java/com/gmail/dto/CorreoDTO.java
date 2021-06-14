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

  public CorreoDTO(AbsCorreo correo){
    this.idCorreo=correo.getIdCorreo();
    this.idUsuario=correo.getIdUsuario();
    this.asunto=correo.getAsunto();
    this.cuerpo=correo.getCuerpo();
    this.fechaHora=correo.getFechaHora();
    this.confirmado= correo.getConfirmado();
    this.borrado=correo.getBorrado();
    this.leido=correo.getLeido();
    this.destacado=correo.getDestacado();
    this.importante=correo.getImportante();
  }

  public CorreoDTO(){ }

  public int getIdCorreo() {
    return idCorreo;
  }

  public void setIdCorreo(int idCorreo) {
    this.idCorreo = idCorreo;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }

  public Boolean getConfirmado() {
    return confirmado;
  }

  public void setConfirmado(Boolean confirmado) {
    this.confirmado = confirmado;
  }

  public Boolean getBorrado() {
    return borrado;
  }

  public void setBorrado(Boolean borrado) {
    this.borrado = borrado;
  }

  public Boolean getLeido() {
    return leido;
  }

  public void setLeido(Boolean leido) {
    this.leido = leido;
  }

  public Boolean getDestacado() {
    return destacado;
  }

  public void setDestacado(Boolean destacado) {
    this.destacado = destacado;
  }

  public Boolean getImportante() {
    return importante;
  }

  public void setImportante(Boolean importante) {
    this.importante = importante;
  }


}
