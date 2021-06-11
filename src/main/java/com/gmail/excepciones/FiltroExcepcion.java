package com.gmail.excepciones;

public class FiltroExcepcion extends Exception{

    private int error;
    private int id;

    public FiltroExcepcion(int error, int id){
        super();
        this.error=error;
        this.id=id;
    }
    public FiltroExcepcion(int error){
        super();
        this.error=error;
        this.id=id;
    }
    public FiltroExcepcion(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){

        String mensaje;

        switch(error){
            case 1:
                mensaje="Filtro_Error 1: No existe correo con id = " + id;
                break;
            default:
                mensaje="Filtro_Error 0: No identificado";
                break;
        }

        return mensaje;

    }

}
