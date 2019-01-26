package com.oliverhidalgo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import com.oliverhidalgo.app.exception.ModeloNotFoundException;
import com.oliverhidalgo.app.model.Persona;
import com.oliverhidalgo.app.model.Producto;
import com.oliverhidalgo.app.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService service;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Producto>> listar(){
		return new ResponseEntity<List<Producto>>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Producto> listarPorId(@PathVariable("id") Integer id) {
		Producto pro = service.listarId(id);
		if(pro == null) {
			throw new ModeloNotFoundException("Id no existe: " + id);
		}
		
		Resource<Producto> resource = new Resource<Producto>(pro);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("producto-resource"));
		
		return resource;
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Object> registrar(@RequestBody Producto pro){
		Producto producto = new Producto();
		producto = service.registrar(pro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producto.getIdProducto()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Object> modificar(@RequestBody Producto pro){
		service.modificar(pro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Producto pro = service.listarId(id);
		if(pro == null) {
			throw new ModeloNotFoundException("Id no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
	
}
