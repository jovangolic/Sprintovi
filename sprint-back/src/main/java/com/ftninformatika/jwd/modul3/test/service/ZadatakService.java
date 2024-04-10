package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.model.Zadatak;

public interface ZadatakService {

	
	Zadatak findOneById(Long id);
	Page<Zadatak> findAll(int pageNo);
	List<Zadatak> findAll();
	Zadatak save(Zadatak zadatak);
	Zadatak update(Zadatak zadatak);
	Zadatak delete(Long id);
	Page<Zadatak> search(String ime, Long sprintId, int pageNo);
	Zadatak promeniStanje(Long id);
	public void updateSprintTotalBodovi(Sprint sprint);
}
