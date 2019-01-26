package com.oliverhidalgo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliverhidalgo.app.model.Producto;

public interface IProductoDao extends JpaRepository<Producto, Integer>{

}
