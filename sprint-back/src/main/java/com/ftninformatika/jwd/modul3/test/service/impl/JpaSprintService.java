package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.repository.SprintRepository;
import com.ftninformatika.jwd.modul3.test.service.SprintService;


@Service
public class JpaSprintService implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	
	@Override
	public Sprint findOneById(Long id) {
		return sprintRepository.getOne(id);
	}

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

}
