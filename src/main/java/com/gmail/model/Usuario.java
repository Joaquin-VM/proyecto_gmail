package com.gmail.model;

import com.gmail.dto.UsuarioDTO;
import java.time.LocalDate;

class Usuario extends AbsUsuario {

  public Usuario() {
    super();
  }

  public Usuario(UsuarioDTO dto) {
    super(dto);
  }

  @Override
  public int getIdUsuario() {
    return this.idUsuario;
  }

  @Override
  public Usuario setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  @Override
  public String getNombre() {
    return this.nombre;
  }

  @Override
  public Usuario setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  @Override
  public String getApellido() {
    return this.apellido;
  }

  @Override
  public Usuario setApellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  @Override
  public String getCorreo() {
    return this.correo;
  }

  @Override
  public Usuario setCorreo(String correo) {
    this.correo = correo;
    return this;
  }

  @Override
  public String getContrasenia() {
    return this.contrasenia;
  }

  @Override
  public Usuario setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
    return this;
  }

  @Override
  public String getTelefono() {
    return this.telefono;
  }

  @Override
  public Usuario setTelefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  @Override
  public String getSexo() {
    return sexo;
  }

  @Override
  public Usuario setSexo(String sexo) {
    this.sexo = sexo;
    return this;
  }

  @Override
  public LocalDate getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  @Override
  public Usuario setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
    return this;
  }

  @Override
  public String toString() {
    return "Usuario{" +
        "idUsuario=" + this.idUsuario +
        ", nombre='" + this.nombre + '\'' +
        ", apellido='" + this.apellido + '\'' +
        ", correo='" + this.correo + '\'' +
        ", contrasenia='" + this.contrasenia + '\'' +
        ", telefono='" + this.telefono + '\'' +
        ", sexo='" + this.sexo + '\'' +
        ", fechaNacimiento=" + this.fechaNacimiento +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Usuario)) {
      return false;
    }

    AbsUsuario usuario = (AbsUsuario) o;

    return this.idUsuario == usuario.getIdUsuario() &&
        this.nombre.equals(usuario.getNombre()) &&
        this.apellido.equals(usuario.getApellido()) &&
        this.correo.equals(usuario.getCorreo()) &&
        this.contrasenia.equals(usuario.getContrasenia()) &&
        this.telefono.equals(usuario.getTelefono()) &&
        this.sexo == usuario.getSexo() &&
        this.fechaNacimiento.compareTo(usuario.getFechaNacimiento()) == 0;
  }

  @Override
  public int hashCode() {
    return this.idUsuario +
        this.nombre.length() + this.apellido.length()
        + this.correo.length() + this.contrasenia.length() +
        this.telefono.length() / 1000000 + this.sexo.length()
        + this.fechaNacimiento.getYear() / 1000
        + this.fechaNacimiento.getMonthValue() + this.fechaNacimiento.getDayOfMonth();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {

    AbsUsuario usuario = new Usuario();
    usuario.setIdUsuario(this.idUsuario);
    usuario.setNombre(this.nombre);
    usuario.setApellido(this.apellido);
    usuario.setCorreo(this.correo);
    usuario.setContrasenia(this.contrasenia);
    usuario.setTelefono(this.telefono);
    usuario.setSexo(this.sexo);
    usuario.setFechaNacimiento(LocalDate.from(this.fechaNacimiento));

    return usuario;

  }

}
