package com.oliverhidalgo.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliverhidalgo.app.controller.ProductoController;
import com.oliverhidalgo.app.dao.IProductoDao;
import com.oliverhidalgo.app.model.Producto;
import com.oliverhidalgo.app.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoDao dao;


	@Override
	public Producto registrar(Producto t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Producto listarId(int id) {
		// TODO Auto-generated method stub
		//return dao.findOne(id);
		return null;
	}

	@Override
	public void eliminar(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Producto> listar() {
		return dao.findAll();
	}
	
	
}
