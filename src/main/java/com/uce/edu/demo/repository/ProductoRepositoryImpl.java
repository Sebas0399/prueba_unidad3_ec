package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);
	}

	@Override
	public Producto buscarCodigo(String codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras=:codigo", Producto.class);
		myQuery.setParameter("codigo", codigo);
		return myQuery.getSingleResult();
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		Producto p = this.buscarId(id);
		this.entityManager.remove(p);
	}

	@Override
	public Producto buscarId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Producto.class, id);
	}

	@Override
	public ProductoSencillo buscarCodigoSencillo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
