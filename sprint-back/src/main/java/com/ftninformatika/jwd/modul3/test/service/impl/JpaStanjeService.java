package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Stanje;
import com.ftninformatika.jwd.modul3.test.repository.StanjeRepository;
import com.ftninformatika.jwd.modul3.test.service.StanjeService;


@Service
public class JpaStanjeService implements StanjeService {

	@Autowired
	private StanjeRepository stanjeRepository;
	
	@Override
	public List<Stanje> findAll() {
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje findOneById(Long id) {
		return stanjeRepository.getOne(id);
	}

}
