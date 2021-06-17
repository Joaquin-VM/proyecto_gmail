package com.gmail.exception;

public class FiltroException extends Exception{

    private int error;
    private int id;
    public FiltroException(String msg) {
        super(msg);
    }

    public FiltroException(int error, int id) {
        super();
        this.error = error;
        this.id = id;
    }

    public FiltroException(int error) {
        super();
        this.error = error;
    }

    @Override
    public String getMessage() {

        String mensaje;

        switch (error) {
            case 1:
                mensaje = "Filtro_Error 01: No existe Filtro con id = " + id;
                break;
            case 4:
                mensaje = "Filtro_Error 04: No pudo modificarse";
            case 5:
                mensaje = "Filtro_Error 05: No pudo eliminarse";
            default:
                mensaje = "Filtro_Error 00: No identificado";
                break;
        }

        return mensaje;

    }

}
