package com.gmail.service;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.exception.NotFoundException;
import com.gmail.exception.SQLDBException;
import com.gmail.exception.ValidationException;
import com.gmail.model.AbsEtiqueta;
import java.util.List;

public interface IEtiquetaService {

  AbsEtiqueta crear(EtiquetaDTO dto) throws ValidationException, SQLDBException, NotFoundException;

  AbsEtiqueta obtenerUna(int idEtiqueta) throws SQLDBException, NotFoundException;

  List<AbsEtiqueta> obtenerCoincidentes(String nombreEtiqueta, int idUsuario)
      throws SQLDBException, ValidationException, NotFoundException;

  List<AbsEtiqueta> listarEtiquetasUsuario(int idUsuario) throws SQLDBException, NotFoundException;

  boolean agregarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws SQLDBException, NotFoundException;

  boolean quitarEtiquetaACorreo(int idCorreo, int idEtiqueta)
      throws SQLDBException, NotFoundException;

  List<AbsEtiqueta> listarEtiquetasDeCorreo(int idCorreo) throws SQLDBException, NotFoundException;

  boolean modificar(int idEitqueta, EtiquetaDTO etiquetaModificada)
      throws ValidationException, SQLDBException, NotFoundException;

  boolean eliminar(int idEtiqueta) throws SQLDBException, ValidationException, NotFoundException;


}
