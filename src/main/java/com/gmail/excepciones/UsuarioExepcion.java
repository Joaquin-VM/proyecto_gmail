package com.gmail.excepciones;

public class UsuarioExepcion extends Exception{

    private int error;
    private int id;

    public UsuarioExepcion(int error, int id){
        super();
        this.error=error;
        this.id=id;
    }
    public UsuarioExepcion(int error){
        super();
        this.error=error;
        this.id=id;
    }
    public UsuarioExepcion(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){

        String mensaje;

        switch(error){
            case 1:
                mensaje="Usuario_Error 1: No existe correo con id = " + id;
                break;
            default:
                mensaje="Usuario_Error 0: No identificado";
                break;
        }

        return mensaje;

    }

}
