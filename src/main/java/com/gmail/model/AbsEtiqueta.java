package com.gmail.model;

import java.util.Objects;

public abstract class AbsEtiqueta implements Cloneable {

    int idEtiqueta;
    String nombreEtiqueta;
    int idUsuario;

    public abstract int getIdEtiqueta();

    public abstract AbsEtiqueta setIdEtiqueta(int idEtiqueta);

    public abstract String getNombreEtiqueta();

    public abstract AbsEtiqueta setNombreEtiqueta(String nombreEtiqueta);

    public abstract int getIdUsuario();

    public abstract Etiqueta setIdUsuario(int idUsuario);

    @Override
    public String toString() {
        return "AbsEtiqueta{" +
                "idEtiqueta=" + idEtiqueta +
                ", nombreEtiqueta='" + nombreEtiqueta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbsEtiqueta)) return false;
        AbsEtiqueta that = (AbsEtiqueta) o;
        return idEtiqueta == that.idEtiqueta &&
                Objects.equals(nombreEtiqueta, that.nombreEtiqueta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtiqueta, nombreEtiqueta);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}