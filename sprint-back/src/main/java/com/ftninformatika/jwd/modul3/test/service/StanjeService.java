package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Stanje;

public interface StanjeService {

	
	List<Stanje> findAll();
	Stanje findOneById(Long id);
	
	
}
