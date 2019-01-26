package com.oliverhidalgo.app.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.oliverhidalgo.app.service.IPersonaService;
import com.oliverhidalgo.app.exception.ModeloNotFoundException;
import com.oliverhidalgo.app.model.Persona;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Persona>> listar(){
		return new ResponseEntity<List<Persona>>(service.listar(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Persona> listarPorId(@PathVariable("id") Integer id) {
		Persona per = service.listarId(id);
		if(per == null) {
			throw new ModeloNotFoundException("Id no existe: " + id);
		}
		
		Resource<Persona> resource = new Resource<Persona>(per);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("persona-resource"));
		
		return resource;
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> registrar(@RequestBody Persona per) {
		Persona persona = new Persona();
		persona = service.registrar(per);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(persona.getIdPersona()).toUri();
			
		return ResponseEntity.created(location).build();	
	}
	
	@PutMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modificar(@RequestBody Persona per){
		service.modificar(per);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Persona per = service.listarId(id);
		if(per==null) {
			throw new ModeloNotFoundException("Id no existe" + id);
		}else {
			service.eliminar(id);
		}
	}
	
}
