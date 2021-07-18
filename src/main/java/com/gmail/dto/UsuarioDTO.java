package com.gmail.dto;

import com.gmail.model.AbsUsuario;
import java.time.LocalDate;

public class UsuarioDTO {

  private int idUsuario;
  private String nombre;
  private String apellido;
  private String correo;
  private String contrasenia;
  private String telefono;
  private String sexo;
  private LocalDate fechaNacimiento;

  public UsuarioDTO(){

  }

  public UsuarioDTO(AbsUsuario usuario) {
    this.idUsuario = usuario.getIdUsuario();
    this.nombre = usuario.getNombre();
    this.apellido = usuario.getApellido();
    this.correo = usuario.getCorreo();
    this.contrasenia = usuario.getContrasenia();
    this.telefono = usuario.getTelefono();
    this.sexo = usuario.getSexo();
    this.fechaNacimiento = usuario.getFechaNacimiento();
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public UsuarioDTO setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public UsuarioDTO setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getApellido() {
    return apellido;
  }

  public UsuarioDTO setApellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  public String getCorreo() {
    return correo;
  }

  public UsuarioDTO setCorreo(String correo) {
    this.correo = correo;
    return this;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public UsuarioDTO setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
    return this;
  }

  public String getTelefono() {
    return telefono;
  }

  public UsuarioDTO setTelefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  public String getSexo() {
    return sexo;
  }

  public UsuarioDTO setSexo(String sexo) {
    this.sexo = sexo;
    return this;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public UsuarioDTO setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
    return this;
  }

}
