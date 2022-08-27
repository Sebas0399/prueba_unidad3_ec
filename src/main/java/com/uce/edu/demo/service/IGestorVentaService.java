package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Reporte;

public interface IGestorVentaService {
public void ingresarProducto(Producto p);
public void realizarVenta(List<ProductoSencillo> productosLista,String cedula,String nroVenta);
public List<Reporte> reporteVentas(LocalDateTime fechaVenta,String categoria,Integer cantidad);
public void reporteStock(String codigoBarras);
}
