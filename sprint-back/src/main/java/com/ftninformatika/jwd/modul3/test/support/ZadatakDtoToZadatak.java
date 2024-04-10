package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Zadatak;
import com.ftninformatika.jwd.modul3.test.service.SprintService;
import com.ftninformatika.jwd.modul3.test.service.StanjeService;
import com.ftninformatika.jwd.modul3.test.service.ZadatakService;
import com.ftninformatika.jwd.modul3.test.web.dto.ZadatakDTO;

@Component
public class ZadatakDtoToZadatak implements Converter<ZadatakDTO, Zadatak> {
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private SprintService sprintService;

	@Autowired
	private StanjeService stanjeService;
	
	@Override
	public Zadatak convert(ZadatakDTO dto) {
		Zadatak zadatak = null;
		if(dto.getId() == null) {
			zadatak = new Zadatak();
		}
		else {
			zadatak = zadatakService.findOneById(dto.getId());
		}
		if(zadatak != null) {
			zadatak.setId(dto.getId());
			zadatak.setIme(dto.getIme());
			zadatak.setZaduzeni(dto.getZaduzeni());
			zadatak.setBodovi(dto.getBodovi());
			zadatak.setSprint(sprintService.findOneById(dto.getSprintId()));
			zadatak.setStanje(stanjeService.findOneById(dto.getStanjeId()));
		}
		return zadatak;
	}

}
