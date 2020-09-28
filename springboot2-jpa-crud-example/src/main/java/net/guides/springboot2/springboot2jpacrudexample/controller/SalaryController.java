package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Salary;
import net.guides.springboot2.springboot2jpacrudexample.model.SalaryDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.SalaryRepository;
import net.guides.springboot2.springboot2jpacrudexample.service.SalaryService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SalaryController {
	@Autowired
	private SalaryRepository  salaryRepository;
	
	@Autowired
	private SalaryService salaryService;

	@GetMapping("/salaries")
	public List<SalaryDTO> getAllSalaries() {
		List<Salary> salarylist;
		List<SalaryDTO> salarydto;
		salarylist=salaryRepository.findAll();
		salarydto=salaryService.salaryListEntity2SalaryListDTO(salarylist);
		
		return salarydto;
	}

	@GetMapping("/salaries/{id}")
	public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable(value = "id") Long salaryId) throws ResourceNotFoundException {
	SalaryDTO salarydto;

		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));
		salarydto=salaryService.salaryEntityToDTO(salary);
			
		return ResponseEntity.ok().body(salarydto);
	}

	@PostMapping("/salaries")
	public SalaryDTO createSalary(@Valid @RequestBody SalaryDTO salarydto) {
		Salary salary=salaryService.salaryDTO2Entity(salarydto);
		Salary salary1=salaryRepository.save(salary);
		SalaryDTO salarydto1=salaryService.salaryEntityToDTO(salary1);		
		return salarydto1;
	}

	@PutMapping("/salaries/{id}")
	public ResponseEntity<SalaryDTO> updateSalary(@PathVariable(value = "id") Long salaryId,
			@Valid @RequestBody SalaryDTO salaryDetails) throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));

		salary.setSalaryYear(salaryDetails.getSalaryYear());
		salary.setSalaryMonth(salaryDetails.getSalaryMonth());
		salary.setSalaryAmount(salaryDetails.getSalaryAmount());
		
		final Salary updatedSalary = salaryRepository.save(salary);
		SalaryDTO updatedSalarydto=salaryService.salaryEntityToDTO(updatedSalary);
		return ResponseEntity.ok(updatedSalarydto);
	}

	@DeleteMapping("/salaries/{id}")
	public Map<String, Boolean> deleteSalary(@PathVariable(value = "id") Long salaryId)
			throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salaries not found for this id :: " + salaryId));

		salaryRepository.delete(salary);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
