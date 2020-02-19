package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Department;
import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;
import com.uniovi.validators.ProfesorValidator;

import javassist.expr.NewArray;

@Controller
public class ProfesorController {

	@Autowired 
	private ProfesorService profesorsService;
	
	@Autowired
	private ProfesorValidator profesorValidator;
	
	
	@RequestMapping("/profesor/list")
	public String getList(Model model) {
		
		model.addAttribute("profesorList", profesorsService.getProfesors() );
		return "profesor/list";

		
	}
	
	@RequestMapping(value = "/profesor/add", method=RequestMethod.POST)
	public String setProfesor(@Validated Profesor profesor,BindingResult result) {
		
		profesorValidator.validate(profesor, result);
		if(result.hasErrors())
			return "/profesor/add";
		
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
	public String getProfesor(Model model){
		model.addAttribute("profesor", new Profesor());
	return "profesor/add";
	}
}
