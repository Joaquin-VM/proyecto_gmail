package com.gmail.model;

import com.gmail.dto.CorreoDTO;
import java.time.LocalDateTime;

class Correo extends AbsCorreo {

    public Correo(){
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
}
