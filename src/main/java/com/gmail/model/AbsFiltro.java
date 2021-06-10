package com.gmail.model;

import java.util.Objects;

public abstract class AbsFiltro implements Cloneable {

    int idFiltro;
    int idUsuario;
    String emisor;
    String receptor;
    String asunto;
    String contiene;

    public abstract int getIdFiltro();

    public abstract AbsFiltro setIdFiltro(int idUsuario);

    public abstract int getIdUsuario();

    public abstract AbsFiltro setIdUsuario(int idUsuario);

    public abstract String getEmisor();

    public abstract AbsFiltro setEmisor(String emisor);

    public abstract String getReceptor();

    public abstract AbsFiltro setReceptor(String receptor);

    public abstract String getAsunto();

    public abstract AbsFiltro setAsunto(String asunto);

    public abstract String getContiene();

    public abstract AbsFiltro setContiene(String contiene);

    @Override
    public String toString() {
        return "AbsFiltro{" +
                "idFiltro=" + idFiltro +
                ", emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", asunto='" + asunto + '\'' +
                ", contiene='" + contiene + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbsFiltro)) return false;
        AbsFiltro absFiltro = (AbsFiltro) o;
        return idFiltro == absFiltro.idFiltro &&
                Objects.equals(emisor, absFiltro.emisor) &&
                Objects.equals(receptor, absFiltro.receptor) &&
                Objects.equals(asunto, absFiltro.asunto) &&
                Objects.equals(contiene, absFiltro.contiene);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFiltro, emisor, receptor, asunto, contiene);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
