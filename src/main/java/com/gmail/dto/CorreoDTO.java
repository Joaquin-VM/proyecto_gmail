package com.gmail.dto;

import com.gmail.model.AbsCorreo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorreoDTO {

  private int idCorreo;
  private int idUsuario;
  private String asunto;
  private String cuerpo;
  private LocalDateTime fechaHora;
  private boolean confirmado;
  private boolean borrado;
  private boolean leido;
  private boolean destacado;
  private boolean importante;
  private boolean spam;
  private List<String> dirCorreosReceptores;

  public CorreoDTO() {
    this.dirCorreosReceptores = new ArrayList<>();
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
    this.spam = correo.getSpam();
    this.dirCorreosReceptores = correo.getDirCorreosReceptores();
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

  public boolean getConfirmado() {
    return confirmado;
  }

  public CorreoDTO setConfirmado(boolean confirmado) {
    this.confirmado = confirmado;
    return this;
  }

  public boolean getBorrado() {
    return borrado;
  }

  public CorreoDTO setBorrado(boolean borrado) {
    this.borrado = borrado;
    return this;
  }

  public boolean getLeido() {
    return leido;
  }

  public CorreoDTO setLeido(boolean leido) {
    this.leido = leido;
    return this;
  }

  public boolean getDestacado() {
    return destacado;
  }

  public CorreoDTO setDestacado(boolean destacado) {
    this.destacado = destacado;
    return this;
  }

  public boolean getImportante() {
    return importante;
  }

  public CorreoDTO setImportante(boolean importante) {
    this.importante = importante;
    return this;
  }

  public boolean getSpam() {
    return spam;
  }

  public CorreoDTO setSpam(boolean spam) {
    this.spam = spam;
    return this;
  }

  public List<String> getDirCorreosReceptores() {
    return dirCorreosReceptores;
  }

  public CorreoDTO setDirCorreosReceptores(List<String> dirCorreosReceptores) {
    this.dirCorreosReceptores = dirCorreosReceptores;
    return this;
  }

}
