package com.gmail.excepciones;

public class EtiquetaExcepcion extends Exception{

    private int error;
    private int id;

    public EtiquetaExcepcion(int error, int id){
        super();
        this.error=error;
        this.id=id;
    }
    public EtiquetaExcepcion(int error){
        super();
        this.error=error;
        this.id=id;
    }
    public EtiquetaExcepcion(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){

        String mensaje;

        switch(error){
            case 1:
                mensaje="Etiqueta_Error 1: No existe correo con id = " + id;
                break;
            default:
                mensaje="Etiqueta_Error 0: No identificado";
                break;
        }

        return mensaje;

    }

}
