package com.gmail.model;

import com.gmail.dto.UsuarioDTO;
import java.time.LocalDate;

public abstract class AbsUsuario implements Cloneable {

  int idUsuario;
  String nombre;
  String apellido;
  String correo;
  String contrasenia;
  String telefono;
  String sexo;
  LocalDate fechaNacimiento;

  public AbsUsuario() {
  }

  public AbsUsuario(UsuarioDTO dto) {
    this.idUsuario = dto.getIdUsuario();
    this.nombre = dto.getNombre();
    this.apellido = dto.getApellido();
    this.correo = dto.getCorreo();
    this.contrasenia = dto.getContrasenia();
    this.telefono = dto.getTelefono();
    this.sexo = dto.getSexo();
    this.fechaNacimiento = dto.getFechaNacimiento();
  }

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
  public abstract String toString();

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  @Override
  public abstract Object clone() throws CloneNotSupportedException;

}
