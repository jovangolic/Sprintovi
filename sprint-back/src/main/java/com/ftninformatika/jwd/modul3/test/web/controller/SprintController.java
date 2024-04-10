package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.service.SprintService;
import com.ftninformatika.jwd.modul3.test.support.SprintToSprintDto;
import com.ftninformatika.jwd.modul3.test.web.dto.SprintDTO;

@RestController
@RequestMapping(value="/api/sprintovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class SprintController {

	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private SprintToSprintDto toDto;
	
	@GetMapping
	public ResponseEntity<List<SprintDTO>> getAll(){
		
		List<Sprint> sprintovi = sprintService.findAll();
		return new ResponseEntity<>(toDto.convert(sprintovi) ,HttpStatus.OK);
	}
}
