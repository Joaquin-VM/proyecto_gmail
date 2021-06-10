package com.gmail.model;

public class Filtro extends AbsFiltro{

    @Override
    public int getIdFiltro() {
        return this.idFiltro;
    }

    @Override
    public Filtro setIdFiltro(int idFiltro) {
        this.idFiltro = idFiltro;
        return this;
    }

    @Override
    public int getIdUsuario() {
        return this.idUsuario;
    }

    @Override
    public Filtro setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }


    @Override
    public String getEmisor() {
        return this.emisor;
    }

    @Override
    public Filtro setEmisor(String emisor) {
        this.emisor = emisor;
        return this;
    }

    @Override
    public String getReceptor() {
        return this.receptor;
    }

    @Override
    public Filtro setReceptor(String receptor) {
        this.receptor = receptor;
        return this;
    }

    @Override
    public String getAsunto() {
        return this.asunto;
    }

    @Override
    public Filtro setAsunto(String asunto) {
        this.asunto = asunto;
        return this;
    }

    @Override
    public String getContiene() {
        return this.contiene;
    }

    @Override
    public Filtro setContiene(String contiene) {
        this.contiene = contiene;
        return this;
    }

}
