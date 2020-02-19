package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private Set<Profesor> teachers;
	
	public Department(String description) {

		this.description = description;
	}

	public Department() {

	
	
}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
