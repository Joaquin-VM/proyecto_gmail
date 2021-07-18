package com.gmail.service;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.OperationException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsEtiqueta;
import java.util.List;

public interface IEtiquetaService {

  AbsEtiqueta crear(EtiquetaDTO dto)
      throws ValidationException, SQLDBException, NotFoundException, OperationException;

  AbsEtiqueta obtenerUna(int idEtiqueta)
      throws SQLDBException, NotFoundException, OperationException;

  List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLDBException, ValidationException, NotFoundException, OperationException;

  List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario)
      throws SQLDBException, NotFoundException, OperationException;

  AbsEtiqueta modificar(EtiquetaDTO etiquetaModificada)
      throws ValidationException, SQLDBException, NotFoundException, OperationException;

  AbsEtiqueta eliminar(int idEtiqueta)
      throws SQLDBException, ValidationException, NotFoundException, OperationException;

  AbsEtiqueta agregarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws SQLDBException, NotFoundException, OperationException;

  AbsEtiqueta quitarEtiquetaACorreo(int idCorreo, int idEtiqueta, int idUsuario)
      throws NotFoundException, OperationException;

  List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo, int idUsuario)
      throws NotFoundException, OperationException;

}
