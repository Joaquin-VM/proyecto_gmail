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

    @Override
    public String toString() {
        return "AbsCorreo{" +
                "idCorreo=" + idCorreo +
                ", asunto='" + asunto + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", fecha=" + fechaHora +
                ", confirmado=" + confirmado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbsCorreo)) return false;
        AbsCorreo absCorreo = (AbsCorreo) o;
        return idCorreo == absCorreo.idCorreo &&
                Objects.equals(asunto, absCorreo.asunto) &&
                Objects.equals(cuerpo, absCorreo.cuerpo) &&
                Objects.equals(fechaHora, absCorreo.fechaHora) &&
                Objects.equals(confirmado, absCorreo.confirmado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCorreo, asunto, cuerpo, fechaHora, confirmado);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
