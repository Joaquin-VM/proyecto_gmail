package com.gmail.model.impl;

import com.gmail.model.AbsFiltro;
import com.gmail.model.impl.Filtro;
import com.gmail.dto.FiltroDTO;

public class FiltroFactory {

  public static AbsFiltro buildFiltro() {
    return new Filtro();
  }

  public static AbsFiltro buildFiltro(FiltroDTO dto) {
    return new Filtro(dto);
  }

}
