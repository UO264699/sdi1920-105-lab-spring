package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;

@Controller
public class ProfesorController {

	@Autowired 
	private ProfesorService profesorsService;
	
	
	@RequestMapping("/profesor/list")
	public String getList(Model model) {
		
		model.addAttribute("profesorList", profesorsService.getProfesors() );
		return "profesor/list";

		
	}
	
	@RequestMapping(value = "/profesor/add", method=RequestMethod.POST)
	public String setProfesor(@ModelAttribute Profesor profesor) {
		
	   profesorsService.addProfesor(profesor.getDni(),profesor);
	   return "redirect:/profesor/list";
	}
	
	@RequestMapping("/profesor/details/{dni}")
	public String getDetail(@PathVariable String dni) {
		
		return profesorsService.getProfesor(dni).toString();

	}
	
	@RequestMapping("/profesor/delete/{dni}")
	public String deleteProfesor(@PathVariable String dni) {
		
		profesorsService.deleteProfesor(dni);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping("/profesor/edit/{dni}/{categoria}")
	public String getEdit(@PathVariable String dni,@PathVariable String categoria) {
		
		profesorsService.edit(dni, categoria);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping(value="/profesor/add")
	public String getProfesor(){
	return "profesor/add";
	}
}
