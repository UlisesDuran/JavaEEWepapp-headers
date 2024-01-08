package com.uduran.apiserverlet.webapp.headers.services;

import com.uduran.apiserverlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 150),
                new Producto(2L, "mesa ordenador", "mueble", 200),
                new Producto(3L, "Teclado mecanico", "computacion", 100));
    }
}
