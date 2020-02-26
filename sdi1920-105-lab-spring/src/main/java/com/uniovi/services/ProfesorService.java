package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Department;
import com.uniovi.entities.Profesor;
import com.uniovi.repositories.DepartmentsRepository;
import com.uniovi.repositories.ProfesorsRepository;

@Service
public class ProfesorService {

	@Autowired
	private ProfesorsRepository profesorsRepository; 
	
	
			
	 
	public List<Profesor> getProfesors(){
		
		List<Profesor> profesors = new ArrayList<Profesor>();
		profesorsRepository.findAll().forEach(profesors::add);
		return profesors;

	
	}
	public Profesor getProfesor(String dni){
		
		return profesorsRepository.findById(dni).get();
	}
	public void addProfesor(String dni,Profesor profesor){
	
		profesor.setDni(dni);
	
		profesorsRepository.save(profesor);
	}
	public void deleteProfesor(String dni){
		
		profesorsRepository.deleteById(dni);
		
	}
	
	public void edit(String dni) {
		
		profesorsRepository.findById(dni).get();
	}
	
	
}
