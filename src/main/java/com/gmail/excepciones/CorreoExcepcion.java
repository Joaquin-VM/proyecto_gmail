package com.gmail.excepciones;

public class CorreoExcepcion extends Exception{

    private int error;
    private int id;

    public CorreoExcepcion(int error, int id){
        super();
        this.error=error;
        this.id=id;
    }
    public CorreoExcepcion(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){

        String mensaje="Error 0: No identificado";

        switch(error){
            case 1:
                mensaje="Error 1: No existe correo con id = " + id;
                break;
            case 2:
                mensaje="Error 2: El correo esta en papelera";
                break;
            case 3:
                mensaje="Error 3: El correo esta enviado";
                break;
        }

        return mensaje;

    }

}
