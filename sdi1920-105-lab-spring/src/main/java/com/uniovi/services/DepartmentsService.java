package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Department;
import com.uniovi.entities.Profesor;
import com.uniovi.repositories.DepartmentsRepository;

@Service
public class DepartmentsService {
	
	@Autowired 
	private DepartmentsRepository departmentsRepository;
	
	 
		public List<Department> getDepartments(){
			
			List<Department> departments = new ArrayList<Department>();
			departmentsRepository.findAll().forEach(departments::add);
			return departments;

		
		}
		public Department getDepartment(Long id){
			
			return departmentsRepository.findById(id).get();
		}
		public void addDepartment(Department department){
		
			departmentsRepository.save(department);
		}
		public void deleteDepartment(Long id){
			
			departmentsRepository.deleteById(id);
			
		}
		
		public void edit(Long id,String descripcion) {
			
			departmentsRepository.findById(id).get().setDescription(descripcion);
		}

}
