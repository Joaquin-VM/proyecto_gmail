package com.gmail.model;

public class FiltroFactory {

  public static AbsFiltro buildFiltro(){
    return new Filtro();
  }

}
