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
import com.uniovi.entities.Mark;
import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;
import com.uniovi.validators.ProfesorValidator;

import javassist.expr.NewArray;

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
	public String setProfesor(Profesor profesor) {
		
		
	   profesorsService.addProfesor(profesor.getDni(),profesor);
	   return "redirect:/profesor/list";
	}
	
	@RequestMapping("/profesor/details/{dni}")
	public String getDetail(Model model, @PathVariable String dni){
	model.addAttribute("profesor", profesorsService.getProfesor(dni));
	return "profesor/details";
	}
	
	@RequestMapping("/profesor/delete/{dni}")
	public String deleteProfesor(@PathVariable String dni) {
		
		profesorsService.deleteProfesor(dni);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping(value="/profesor/edit/{dni}")
	public String getEdit(Model model, @PathVariable String dni){
		model.addAttribute("profesor", profesorsService.getProfesor(dni));
		
		return "profesor/edit";
	}
	
	@RequestMapping(value="/profesor/add")
	public String getProfesor(Model model){
		model.addAttribute("profesor", new Profesor());
	return "profesor/add";
	}
	
	@RequestMapping(value="/profesor/edit/{dni}", method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable String dni, @ModelAttribute Profesor profesor){
		Profesor original = profesorsService.getProfesor(dni);
		// modificar solo score y description
		original.setNombre(profesor.getNombre());
		original.setApellido(profesor.getApellido());
		profesorsService.addProfesor(dni,original);
		return "redirect:/profesor/details/"+dni;
	}
}
