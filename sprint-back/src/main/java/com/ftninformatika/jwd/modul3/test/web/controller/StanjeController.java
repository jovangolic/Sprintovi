package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Stanje;
import com.ftninformatika.jwd.modul3.test.service.StanjeService;
import com.ftninformatika.jwd.modul3.test.support.StanjeToStanjeDto;
import com.ftninformatika.jwd.modul3.test.web.dto.StanjeDTO;

@RestController
@RequestMapping(value="/api/stanja" , produces = MediaType.APPLICATION_JSON_VALUE)
public class StanjeController {
	
	@Autowired
	private StanjeService stanjeService;
	
	@Autowired
	private StanjeToStanjeDto toDto;
	
	@GetMapping
	public ResponseEntity<List<StanjeDTO>> getAll(){
		
		List<Stanje> stanja = stanjeService.findAll();
		return new ResponseEntity<>(toDto.convert(stanja) ,HttpStatus.OK);
	}

}
