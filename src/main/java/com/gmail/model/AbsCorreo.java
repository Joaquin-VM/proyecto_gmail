package com.gmail.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbsCorreo implements Cloneable {

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
  public String toString() {
    return "AbsCorreo{" +
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
    if (!(o instanceof AbsCorreo)) {
      return false;
    }
    AbsCorreo absCorreo = (AbsCorreo) o;
    return idCorreo == absCorreo.idCorreo && idUsuario == absCorreo.idUsuario && Objects
        .equals(asunto, absCorreo.asunto) && Objects.equals(cuerpo, absCorreo.cuerpo)
        && Objects.equals(fechaHora, absCorreo.fechaHora) && Objects
        .equals(confirmado, absCorreo.confirmado) && Objects
        .equals(borrado, absCorreo.borrado) && Objects.equals(leido, absCorreo.leido)
        && Objects.equals(destacado, absCorreo.destacado) && Objects
        .equals(importante, absCorreo.importante);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(idCorreo, idUsuario, asunto, cuerpo, fechaHora, confirmado, borrado, leido,
            destacado,
            importante);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
