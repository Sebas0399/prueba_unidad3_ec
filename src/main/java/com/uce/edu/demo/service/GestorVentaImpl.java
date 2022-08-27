package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Reporte;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class GestorVentaImpl implements IGestorVentaService {
	@Autowired
	private IProductoRepository productoRepo;
	@Autowired
	private IVentaRepository ventaRepo;
	
	@Autowired
	private IDetalleVentaRepository detalleVentaRepo;
	@Override
	public void ingresarProducto(Producto p) {
		// TODO Auto-generated method stub
		try {
			Producto producto = this.productoRepo.buscarCodigo(p.getCodigoBarras());
			producto.setStock(producto.getStock() + p.getStock());
			this.productoRepo.actualizar(producto);
		}

		catch (EmptyResultDataAccessException e) {
			System.out.println("No existe");
			this.productoRepo.insertar(p);
		}
	}

	@Override
	public void realizarVenta(List<ProductoSencillo> productosLista, String cedula, String nroVenta) {
		// TODO Auto-generated method stub
		Venta v = new Venta();
		v.setCedulaCliente(cedula);
		v.setFecha(LocalDateTime.now());
		v.setNumero(nroVenta);
		for (ProductoSencillo pl : productosLista) {
			
			if (pl.getCantidad() > this.productoRepo.buscarCodigo(pl.getCodigoBarras()).getStock()) {
				throw new RuntimeException();
			} else {
				DetalleVenta dv = new DetalleVenta();
				dv.setProducto(this.productoRepo.buscarCodigo(pl.getCodigoBarras()));
				dv.setCantidad(pl.getCantidad());
				dv.setSubtotal(this.productoRepo.buscarCodigo(pl.getCodigoBarras()).getPrecio()
						.multiply(new BigDecimal(pl.getCantidad())));
				;
				this.detalleVentaRepo.insertar(dv);
				Producto p= this.productoRepo.buscarCodigo(pl.getCodigoBarras());
				p.setStock(p.getStock()-pl.getCantidad());
				this.productoRepo.actualizar(p);
				//this.productoRepo.actualizar(this.productoRepo.buscarCodigo(pl.getCodigoBarras()).setStock(dv.getProducto().getStock()-pl.getCantidad()));
				
				//dv.getProducto().setStock(dv.getProducto().getStock()-pl.getCantidad());
				if(v.getTotalVenta()==null) {
					v.setTotalVenta(new BigDecimal(0));
				}
				v.setTotalVenta(v.getTotalVenta().add(dv.getSubtotal()));
			}
			
		}
		this.ventaRepo.insertar(v);
	}

	@Override
	public List<Reporte> reporteVentas(LocalDateTime fechaVenta, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		List<Reporte>listaReporte=new ArrayList<>();
		List<Venta>ventas=this.ventaRepo.listarVentas();
		
		
		for(Venta v:ventas) {
			Reporte r=new Reporte();
			r.setCantidad(cantidad);
			
		
		}
		return listaReporte;
	}

	@Override
	public void reporteStock(String codigoBarras) {
		// TODO Auto-generated method stub
		
	}
	

}
