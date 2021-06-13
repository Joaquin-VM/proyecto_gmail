package com.gmail.service;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.SQLError;
import com.gmail.exception.ValidationError;
import com.gmail.model.AbsEtiqueta;
import java.util.List;

public interface IEtiquetaService {

    AbsEtiqueta crear(EtiquetaDTO dto) throws ValidationError, SQLError;

    AbsEtiqueta obtenerUna(int idEtiqueta) throws SQLError;

    List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
        throws SQLError, ValidationError;

    List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) throws SQLError;

    boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLError;

    boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLError;

    List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo) throws SQLError;

    boolean modificar(int idEitqueta, EtiquetaDTO etiquetaModificada)
        throws ValidationError, SQLError;

    boolean eliminar(int idEtiqueta) throws SQLError, ValidationError;


}
