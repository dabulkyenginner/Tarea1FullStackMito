package com.oliverhidalgo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//Codigos de respuesta en el API Rest
public class ModeloNotFoundException extends RuntimeException{
	
	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}

}
