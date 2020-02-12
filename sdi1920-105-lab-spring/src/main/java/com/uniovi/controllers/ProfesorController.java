package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;

@RestController
public class ProfesorController {

	@Autowired 
	private ProfesorService profesorsService;
	
	
	@RequestMapping("/profesor/list")
	public String getList() {
		return profesorsService.getProfesors().toString();
		
	}
	
	@RequestMapping("/profesor/add")
	public String setProfesor(@ModelAttribute Profesor profesor) {
		
	   profesorsService.addProfesor(profesor);
	   return "OK";
	}
	
	@RequestMapping("/profesor/details/{dni}")
	public String getDetail(@PathVariable String dni) {
		
		return profesorsService.getProfesor(dni).toString();

	}
	
	@RequestMapping("/profesor/delete/{dni}")
	public String deleteProfesor(@PathVariable String dni) {
		
		profesorsService.deleteProfesor(dni);
		return "OK";
	}
	
	@RequestMapping("/profesor/edit/{dni}/{categoria}")
	public String getEdit(@PathVariable String dni,@PathVariable String categoria) {
		
		profesorsService.edit(dni, categoria);
		return "OK";
	}
	
	
}
