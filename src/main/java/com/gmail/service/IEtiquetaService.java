package com.gmail.service;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.model.AbsEtiqueta;

public interface IEtiquetaService {

    AbsEtiqueta crear(EtiquetaDTO dto);

    AbsEtiqueta modificar(int idEitqueta, EtiquetaDTO etiquetaModificada);

    AbsEtiqueta eliminar(int idEtiqueta);

    AbsEtiqueta obtenerUna(int idEtiqueta);

}
