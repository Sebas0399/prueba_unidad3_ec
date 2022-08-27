package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoService {

	Producto buscarPorCodigo(String codigo);
}
