package com.gmail.excepciones;

public class CorreoExcepcion extends Exception{

    private int error;
    private int id;

    public CorreoExcepcion(int error, int id){
        super();
        this.error=error;
        this.id=id;
    }
    public CorreoExcepcion(int error){
        super();
        this.error=error;
        this.id=id;
    }
    public CorreoExcepcion(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){

        String mensaje;

        switch(error){
            case 1:
                mensaje="Correo_Error 1: No existe correo con id = " + id;
                break;
            case 2:
                mensaje="Correo_Error 2: El correo esta en papelera";
                break;
            case 3:
                mensaje="Correo_Error 3: El correo esta enviado";
                break;
            case 4:
                mensaje="Correo_Error 4: No pudo modificarse";
            default:
                mensaje="Correo_Error 0: No identificado";
                break;
        }

        return mensaje;

    }

}
