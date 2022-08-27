package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IProductoRepository {
	public void insertar(Producto producto);
	public void actualizar(Producto producto);
	public Producto buscarCodigo(String codigo);
	public Producto buscarId(Integer id);
	public void borrar(Integer id);
	public ProductoSencillo buscarCodigoSencillo(String codigo);
	
	
}
