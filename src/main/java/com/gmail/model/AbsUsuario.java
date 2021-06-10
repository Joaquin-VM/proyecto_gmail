package com.gmail.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbsUsuario implements Cloneable {

  int idUsuario;
  String nombre;
  String apellido;
  String correo;
  String contrasenia;
  String telefono;
  String sexo;
  LocalDate fechaNacimiento;

  public abstract int getIdUsuario();

  public abstract AbsUsuario setIdUsuario(int idUsuario);

  public abstract String getNombre();

  public abstract AbsUsuario setNombre(String nombre);

  public abstract String getApellido();

  public abstract AbsUsuario setApellido(String apellido);

  public abstract String getCorreo();

  public abstract AbsUsuario setCorreo(String correo);

  public abstract String getContrasenia();

  public abstract AbsUsuario setContrasenia(String contrasenia);

  public abstract String getTelefono();

  public abstract AbsUsuario setTelefono(String telefono);

  public abstract String getSexo();

  public abstract AbsUsuario setSexo(String sexo);

  public abstract LocalDate getFechaNacimiento();

  public abstract AbsUsuario setFechaNacimiento(LocalDate fechaNacimiento);

  @Override
  public String toString() {
    return "AbsUsuario{" +
        "idUsuario=" + idUsuario +
        ", nombre='" + nombre + '\'' +
        ", apellido='" + apellido + '\'' +
        ", correo='" + correo + '\'' +
        ", contrasenia='" + contrasenia + '\'' +
        ", telefono='" + telefono + '\'' +
        ", sexo='" + sexo + '\'' +
        ", fechaNacimiento=" + fechaNacimiento +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbsUsuario)) {
      return false;
    }
    AbsUsuario that = (AbsUsuario) o;
    return idUsuario == that.idUsuario && Objects.equals(nombre, that.nombre)
        && Objects.equals(apellido, that.apellido) && Objects
        .equals(correo, that.correo) && Objects.equals(contrasenia, that.contrasenia)
        && Objects.equals(telefono, that.telefono) && Objects
        .equals(sexo, that.sexo) && Objects.equals(fechaNacimiento, that.fechaNacimiento);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(idUsuario, nombre, apellido, correo, contrasenia, telefono, sexo,
            fechaNacimiento);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
