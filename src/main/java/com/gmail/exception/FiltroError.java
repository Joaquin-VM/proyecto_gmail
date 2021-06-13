package com.gmail.exception;

public class FiltroError extends Exception{

    private int error;
    private int id;
    public FiltroError(String msg) {
        super(msg);
    }

    public FiltroError(int error, int id) {
        super();
        this.error = error;
        this.id = id;
    }

    public FiltroError(int error) {
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
            case 2:
                mensaje = "Filtro_Error 02: El Filtro esta en papelera";
                break;
            case 3:
                mensaje = "Filtro_Error 03: El Filtro esta enviado";
                break;
            case 4:
                mensaje = "Filtro_Error 04: No pudo modificarse";
            case 5:
                mensaje = "Filtro_Error 05: No pudo eliminarse";
            case 6:
                mensaje = "Filtro_Error 06: No pudo enviar el correo al usuario id = " + id;
            case 7:
                mensaje = "Filtro_Error 07: No pudo enviar el correo a " + id + " usuarios";
            default:
                mensaje = "Filtro_Error 00: No identificado";
                break;
        }

        return mensaje;

    }

}
