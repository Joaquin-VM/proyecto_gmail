package com.gmail.model;

import java.time.LocalDateTime;

public class Correo extends AbsCorreo {

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


}
