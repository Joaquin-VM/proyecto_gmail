package com.gmail.service;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsEtiqueta;
import java.util.List;

public interface IEtiquetaService {

  AbsEtiqueta crear(EtiquetaDTO dto) throws ValidationException, SQLDBException;

  AbsEtiqueta obtenerUna(int idEtiqueta) throws SQLDBException;

  List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLDBException, ValidationException;

  List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) throws SQLDBException;

  boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLDBException;

  boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta) throws SQLDBException;

  List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo) throws SQLDBException;

  boolean modificar(int idEitqueta, EtiquetaDTO etiquetaModificada)
      throws ValidationException, SQLDBException;

  boolean eliminar(int idEtiqueta) throws SQLDBException, ValidationException;


}
