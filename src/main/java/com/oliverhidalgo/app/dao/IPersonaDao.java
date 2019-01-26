package com.oliverhidalgo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliverhidalgo.app.model.Persona;

public interface IPersonaDao extends JpaRepository<Persona, Integer>{

}
