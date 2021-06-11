package com.gmail.model;

import com.gmail.dto.EtiquetaDTO;

public class EtiquetaFactory {

  public static AbsEtiqueta buildEtiqueta() {
    return new Etiqueta();
  }

  public static AbsEtiqueta buildEtiqueta(EtiquetaDTO dto) {
    return new Etiqueta(dto);
  }

}
