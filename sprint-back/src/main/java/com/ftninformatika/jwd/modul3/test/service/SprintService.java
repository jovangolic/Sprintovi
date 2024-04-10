package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Sprint;

public interface SprintService {

	
	Sprint findOneById(Long id);
	List<Sprint> findAll();
	Sprint save(Sprint sprint);
	
}
