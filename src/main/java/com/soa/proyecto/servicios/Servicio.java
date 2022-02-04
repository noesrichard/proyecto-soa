package com.soa.proyecto.servicios;

import java.util.List;

public interface Servicio<T>{

    List<T> get();

    T get(T entidad);

    T crear(T entidad);

    T edit(T entidad);

    T eliminar(T entidad);

}
