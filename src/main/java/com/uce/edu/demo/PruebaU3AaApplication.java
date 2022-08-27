package com.uce.edu.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.service.IGestorVentaService;
import com.uce.edu.demo.service.IProductoService;

@SpringBootApplication
public class PruebaU3AaApplication implements CommandLineRunner{
	private static Logger LOG = LogManager.getLogger(PruebaU3AaApplication.class.getName());

	@Autowired
	private IGestorVentaService gestorVenta;
	
	@Autowired
	private IProductoService productoService;
	public static void main(String[] args) {
		SpringApplication.run(PruebaU3AaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Producto p=new Producto();
		p.setCategoria("Alimentos");
		p.setNombre("Galletas Ducales");
		p.setPrecio(new BigDecimal(2.5));
		p.setStock(30);
		p.setCodigoBarras("AGD-10");
		
		this.gestorVenta.ingresarProducto(p);
		this.gestorVenta.ingresarProducto(p);
		
		
		ProductoSencillo ps=new ProductoSencillo();
		ps.setCodigoBarras("AGD-10");
		ps.setCantidad(5);
		
		ProductoSencillo ps2=new ProductoSencillo();
		ps2.setCodigoBarras("AJL-10");
		ps2.setCantidad(5);
		
		List<ProductoSencillo> listaprod = new ArrayList<>();
		listaprod.add(ps2);
		listaprod.add(ps);
		
		this.gestorVenta.realizarVenta(listaprod, "1725776650", "1");
		
		this.productoService.buscarPorCodigo("AGD-10");
		
	}

}
