package com.oliverhidalgo.app.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliverhidalgo.app.dao.IVentaDao;
import com.oliverhidalgo.app.model.Venta;
import com.oliverhidalgo.app.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	private IVentaDao dao;
	
	@Override
	public Venta registrar(Venta t) {
		t.getDetalleVenta().forEach(det -> det.setVenta(t));
		return dao.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.deleteById(id);
	}

	@Override
	public Venta listarId(int id) {
		//return dao.findOne(id);
		return null;
	}

	@Override
	public List<Venta> listar() {
		return dao.findAll();
	}

}
