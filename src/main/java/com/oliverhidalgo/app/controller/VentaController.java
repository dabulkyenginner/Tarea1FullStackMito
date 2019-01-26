package com.oliverhidalgo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import com.oliverhidalgo.app.model.Venta;
import com.oliverhidalgo.app.service.IVentaService;

@RestController
@RequestMapping(value="/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService service;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Venta>> listar(){
		return new ResponseEntity<List<Venta>>(service.listar(), HttpStatus.OK);
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	ResponseEntity<Object> registrar(@RequestBody Venta ven){
		Venta venta = new Venta();
		venta = service.registrar(ven);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venta.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
}
