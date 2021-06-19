package com.gmail.model;

import com.gmail.dto.CorreoDTO;
import java.time.LocalDateTime;

public abstract class AbsCorreo implements Cloneable {

  protected int idCorreo;
  protected int idUsuario;
  protected String asunto;
  protected String cuerpo;
  protected LocalDateTime fechaHora;
  protected Boolean confirmado;
  protected Boolean borrado;
  protected Boolean leido;
  protected Boolean destacado;
  protected Boolean importante;

  public AbsCorreo() {
  }

  public AbsCorreo(CorreoDTO dto) {
    this.idCorreo = dto.getIdCorreo();
    this.idUsuario = dto.getIdUsuario();
    this.asunto = dto.getAsunto();
    this.cuerpo = dto.getCuerpo();
    this.fechaHora = dto.getFechaHora();
    this.confirmado = dto.getConfirmado();
    this.borrado = dto.getBorrado();
    this.leido = dto.getLeido();
    this.destacado = dto.getDestacado();
    this.importante = dto.getImportante();
  }

  public abstract int getIdCorreo();

  public abstract AbsCorreo setIdCorreo(int idCorreo);

  public abstract int getIdUsuario();

  public abstract AbsCorreo setIdUsuario(int idUsuario);

  public abstract String getAsunto();

  public abstract AbsCorreo setAsunto(String asunto);

  public abstract String getCuerpo();

  public abstract AbsCorreo setCuerpo(String cuerpo);

  public abstract LocalDateTime getFechaHora();

  public abstract AbsCorreo setFechaHora(LocalDateTime fecha);

  public abstract Boolean getConfirmado();

  public abstract AbsCorreo setConfirmado(Boolean confirmado);

  public abstract Boolean getBorrado();

  public abstract AbsCorreo setBorrado(Boolean borrado);

  public abstract Boolean getLeido();

  public abstract AbsCorreo setLeido(Boolean leido);

  public abstract Boolean getDestacado();

  public abstract AbsCorreo setDestacado(Boolean destacado);

  public abstract Boolean getImportante();

  public abstract AbsCorreo setImportante(Boolean importante);

  @Override
  public abstract String toString();

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
