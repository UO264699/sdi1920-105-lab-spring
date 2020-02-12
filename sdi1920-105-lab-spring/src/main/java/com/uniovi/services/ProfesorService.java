package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Profesor;

@Service
public class ProfesorService {

	private List<Profesor> profesorsList = new LinkedList<Profesor>()
			;
	 @PostConstruct
	 public void init(){
	 profesorsList.add(new Profesor("12345677S","Andrés","Fernández","..."));
	 profesorsList.add(new Profesor("12645687S","Manuel","Garcia","..."));
	 }
	public List<Profesor> getProfesors(){
	return profesorsList;
	}
	public Profesor getProfesor(String dni){
	return profesorsList.stream()
	.filter(profesor -> profesor.getDni().equals(dni)).findFirst().get();
	}
	public void addProfesor(Profesor profesor){
	
	 profesorsList.add(profesor);
	}
	public void deleteProfesor(String dni){
	profesorsList.removeIf(profesor -> profesor.getDni().equals(dni));
	}
	
	public void edit(String dni,String categoria) {
		
	   getProfesor(dni).setCategoria(categoria);
	}
}
