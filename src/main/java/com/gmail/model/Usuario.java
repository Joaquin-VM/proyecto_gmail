package com.gmail.model;

import java.time.LocalDate;

public class Usuario extends AbsUsuario {

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

}
