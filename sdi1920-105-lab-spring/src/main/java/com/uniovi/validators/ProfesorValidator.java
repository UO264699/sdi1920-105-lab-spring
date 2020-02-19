package com.uniovi.validators;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.uniovi.entities.Department;
import com.uniovi.entities.Profesor;
import com.uniovi.entities.User;
import com.uniovi.services.DepartmentsService;
import com.uniovi.services.ProfesorService;
@Component
public class ProfesorValidator implements Validator{


	@Autowired
	private ProfesorService profesorsService;
	
	private DepartmentsService departmentService;
	
	@Override
	public boolean supports(Class<?> aClass) {
	return Profesor.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Profesor profesor  = (Profesor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (profesor.getDni().length() < 5 || profesor.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.add.dni.length");
		}
		if (profesorsService.getProfesor(profesor.getDni()) != null) {
			errors.rejectValue("dni", "Error.add.dni.duplicate");
		}
		if (profesor.getNombre().length() < 5 || profesor.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.add.nombre.length");
		}
		if (profesor.getApellido().length() < 5 ||profesor.getApellido().length() > 24) {
			errors.rejectValue("apellido", "Error.add.apellido.length");
		}
		
		List<Department> departments = departmentService.getDepartments();
		
		int cont = 0;
		
		for(Department d:departments) {
			
			if(profesor.getDepartment().getDescription().equals(d.getDescription()))
					cont++;
		}
		
		if(cont<= 0) {
			errors.rejectValue("department", "Error.add.department.length");
			
		}
	}
	
	
	
}
