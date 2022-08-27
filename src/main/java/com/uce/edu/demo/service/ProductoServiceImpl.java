package com.uce.edu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	@Autowired
	private IProductoRepository productoRepo;

	@Override
	public Producto buscarPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return this.productoRepo.buscarCodigo(codigo);
	}
	
}
