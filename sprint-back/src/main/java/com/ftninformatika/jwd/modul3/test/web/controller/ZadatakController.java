package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Zadatak;
import com.ftninformatika.jwd.modul3.test.service.ZadatakService;
import com.ftninformatika.jwd.modul3.test.support.ZadatakDtoToZadatak;
import com.ftninformatika.jwd.modul3.test.support.ZadatakToZadatakDto;
import com.ftninformatika.jwd.modul3.test.web.dto.ZadatakDTO;

@RestController
@RequestMapping(value="/api/zadaci" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ZadatakController {
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private ZadatakDtoToZadatak toZadatak;

	@Autowired
	private ZadatakToZadatakDto toZadatakDto;
	
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> create (@Validated @RequestBody ZadatakDTO dto){
		
		Zadatak zadatak = toZadatak.convert(dto);
		if(zadatak.getSprint() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(zadatak.getStanje() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zadatak saved = zadatakService.save(zadatak);
		zadatakService.updateSprintTotalBodovi(saved.getSprint());
		return new ResponseEntity<>(toZadatakDto.convert(saved) ,HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasRole('KORISNIK')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> update(@PathVariable Long id, @Validated @RequestBody ZadatakDTO dto){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zadatak zadatak = toZadatak.convert(dto);
		if(zadatak.getSprint() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(zadatak.getStanje() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zadatak updated = zadatakService.update(zadatak);
		zadatakService.updateSprintTotalBodovi(updated.getSprint());
		return new ResponseEntity<>(toZadatakDto.convert(updated) ,HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Zadatak obrisan = zadatakService.delete(id);
		if(obrisan != null) {
			zadatakService.updateSprintTotalBodovi(obrisan.getSprint());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("HasAnyRole('ADMIN, KORISNIK')")
	@GetMapping("/{id}")
	public ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id){
		Zadatak zadatak = zadatakService.findOneById(id);
		if(zadatak != null) {
			return new ResponseEntity<>(toZadatakDto.convert(zadatak) ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*//@PreAuthorize("HasAnyRole('*')")
	@GetMapping
	public ResponseEntity<List<ZadatakDTO>> findAll(){
		List<Zadatak> zadaci = zadatakService.findAll();
		return new ResponseEntity<>(toZadatakDto.convert(zadaci) ,HttpStatus.OK);
	}*/
	
	
	//pretraga po odredjenim parametrima
	//@PreAuthorize("hasAnyRole('*')")
	@GetMapping
	public ResponseEntity<List<ZadatakDTO>> getAll(@RequestParam(required = false) String ime,@RequestParam(required = false) Long sprintId,
				@RequestParam(value="pageNo", defaultValue = "0") int pageNo){
		Page<Zadatak> page = zadatakService.search(ime, sprintId, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toZadatakDto.convert(page.getContent()) ,headers ,HttpStatus.OK);
	}
	
	//metoda za promenu stanja zadatka. U ResponseEntity se prosledjuje Zadatak a ne dto objekat
	//@PreAuthorize("hasAnyRole('KORISNIK')")
	@PutMapping("/{id}/promeni-stanje")
	public ResponseEntity<Zadatak> promeniStanje(@PathVariable Long id){
		
		Zadatak zadatak = zadatakService.promeniStanje(id);
		if(zadatak == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(zadatak);
		}
	}
}

