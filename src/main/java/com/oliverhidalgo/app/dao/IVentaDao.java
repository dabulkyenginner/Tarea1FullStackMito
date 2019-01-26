package com.oliverhidalgo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliverhidalgo.app.model.Venta;

public interface IVentaDao extends JpaRepository<Venta,Integer> {

}
