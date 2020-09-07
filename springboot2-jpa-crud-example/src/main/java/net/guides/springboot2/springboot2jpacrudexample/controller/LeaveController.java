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
import net.guides.springboot2.springboot2jpacrudexample.model.Leave;
import net.guides.springboot2.springboot2jpacrudexample.model.LeaveDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.LeaveRepository;
import net.guides.springboot2.springboot2jpacrudexample.service.LeaveService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class LeaveController {
	@Autowired
	private LeaveRepository  leaveRepository;
	
	@Autowired
	private LeaveService leaveService;

	@GetMapping("/leaves")
	public List<LeaveDTO> getAllSalaries() {
		List<Leave> leavelist;
		List<LeaveDTO> leavedto;
		leavelist=leaveRepository.findAll();
		leavedto=leaveService.leaveListEntity2LeaveListDTO(leavelist);
		
		return leavedto;
	}

	@GetMapping("/leaves/{id}")
	public ResponseEntity<LeaveDTO> getLeaveById(@PathVariable(value = "id") Long leaveId) throws ResourceNotFoundException {
	LeaveDTO leavedto;

		Leave leave = leaveRepository.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		leavedto=leaveService.leaveEntityToDTO(leave);
			
		return ResponseEntity.ok().body(leavedto);
	}

	@PostMapping("/leaves")
	public LeaveDTO createLeave(@Valid @RequestBody LeaveDTO leavedto) {
		Leave leave=leaveService.leaveDTO2Entity(leavedto);
		Leave leave1=leaveRepository.save(leave);
		LeaveDTO leavedto1=leaveService.leaveEntityToDTO(leave1);		
		return leavedto1;
	}

	@PutMapping("/leaves/{id}")
	public ResponseEntity<LeaveDTO> updateLeave(@PathVariable(value = "id") Long leaveId,
			@Valid @RequestBody LeaveDTO leavdto) throws ResourceNotFoundException {
		Leave leave = leaveRepository.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));

		leave.setFromDate(leavdto.getFromDate());
		leave.setToDate(leavdto.getToDate());
		leave.setReason(leavdto.getReason());
		
		final Leave updatedLeave = leaveRepository.save(leave);
		LeaveDTO updatedLeavedto=leaveService.leaveEntityToDTO(updatedLeave);
		return ResponseEntity.ok(updatedLeavedto);
	}

	@DeleteMapping("/leaves/{id}")
	public Map<String, Boolean> deleteLeave(@PathVariable(value = "id") Long leaveId)
			throws ResourceNotFoundException {
		Leave leave = leaveRepository.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Salaries not found for this id :: " + leaveId));

		leaveRepository.delete(leave);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
