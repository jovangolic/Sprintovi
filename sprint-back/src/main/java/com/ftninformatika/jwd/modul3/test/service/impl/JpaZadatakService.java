package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.model.Stanje;
import com.ftninformatika.jwd.modul3.test.model.Zadatak;

import com.ftninformatika.jwd.modul3.test.repository.ZadatakRepository;
import com.ftninformatika.jwd.modul3.test.service.SprintService;
import com.ftninformatika.jwd.modul3.test.service.StanjeService;
import com.ftninformatika.jwd.modul3.test.service.ZadatakService;



@Service
public class JpaZadatakService implements ZadatakService {

	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StanjeService stanjeService;
	
	@Override
	public Zadatak findOneById(Long id) {
		// TODO Auto-generated method stub
		return zadatakRepository.findOneById(id);
	}

	@Override
	public Page<Zadatak> findAll(int pageNo) {
		return zadatakRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public List<Zadatak> findAll() {
		return zadatakRepository.findAll();
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		Zadatak saved = zadatakRepository.save(zadatak);
		updateSprintTotalBodovi(saved.getSprint());
		return saved;
	}

	@Override
	public Zadatak update(Zadatak zadatak) {
		Zadatak updated = zadatakRepository.save(zadatak);
		if(updated != null) {
			Sprint sprint = updated.getSprint();
			List<Zadatak> zadaci = sprint.getZadaci();
			//iteracija kroz zadatke kako bi se svaki zadatak azurirao
			for(int i = 0; i < zadaci.size(); i++) {
				if(zadaci.get(i).getId().equals(updated.getId())) {
					zadaci.set(i, updated);
					break;
				}
			}
			sprintService.save(sprint);
			updateSprintTotalBodovi(sprint);
		}
		return updated;
	}

	@Override
	public Zadatak delete(Long id) {
		//pronalazenje zadatka po njegovom id-u
		Zadatak deleted = zadatakRepository.findOneById(id);
		if(deleted != null) {
			Sprint sprint = deleted.getSprint();
			sprint.getZadaci().remove(deleted);
			sprintService.save(sprint);
			zadatakRepository.delete(deleted);
			//azuriranje bodova
			updateSprintTotalBodovi(sprint);
			return deleted;
		}
		return null;
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int pageNo) {
		/*if(!ime.equals("") && sprintId != null) {
			return zadatakRepository.findByImeAndSprintId(ime, sprintId, PageRequest.of(pageNo, 4));
		}
		else if(!ime.equals("")) {
			return zadatakRepository.findByIme(ime, PageRequest.of(pageNo, 4));
		}
		else if(sprintId != null) {
			return zadatakRepository.findBySprintId(sprintId, PageRequest.of(pageNo, 4));
		}
		else {
			return findAll(pageNo);
		}*/
		
		return zadatakRepository.search(ime, sprintId, PageRequest.of(pageNo, 5));
	}

	@Override
	public Zadatak promeniStanje(Long id) {
		Zadatak zadatak = zadatakRepository.findOneById(id);
		Stanje trenutnoStanje = zadatak.getStanje();
		List<Stanje> stanja = stanjeService.findAll();
		int indexTrenutnogStanja = stanja.indexOf(trenutnoStanje);
		if(indexTrenutnogStanja != -1 && indexTrenutnogStanja < stanja.size()-1) {
			Stanje sledeceStanje = stanja.get(indexTrenutnogStanja + 1);
			zadatak.setStanje(sledeceStanje);
			zadatakRepository.save(zadatak);
			return zadatak;
		}
		return null;
	}

	@Override
	public void updateSprintTotalBodovi(Sprint sprint) {
		int ukupnoBodova = 0;
		for(Zadatak z : sprint.getZadaci()) {
			ukupnoBodova += z.getBodovi();
		}
		sprint.setUkupnoBodova(Integer.toString(ukupnoBodova));
		sprintService.save(sprint);
	}

}
