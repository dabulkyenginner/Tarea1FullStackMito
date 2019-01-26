package com.oliverhidalgo.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliverhidalgo.app.dao.IPersonaDao;
import com.oliverhidalgo.app.model.Persona;
import com.oliverhidalgo.app.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private IPersonaDao dao;

	@Override
	public Persona registrar(Persona t) {
		return dao.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.deleteById(id);
	}

	@Override
	public Persona listarId(int id) {
		//return dao.findOne(id);
		return null;
	}

	@Override
	public List<Persona> listar() {
		return dao.findAll();
	}
	
}
