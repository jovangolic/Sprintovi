package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Zadatak;
import com.ftninformatika.jwd.modul3.test.web.dto.ZadatakDTO;

@Component
public class ZadatakToZadatakDto implements Converter<Zadatak, ZadatakDTO> {

	@Override
	public ZadatakDTO convert(Zadatak zadatak) {
		ZadatakDTO dto = new ZadatakDTO();
		dto.setId(zadatak.getId());
		dto.setIme(zadatak.getIme());
		dto.setZaduzeni(zadatak.getZaduzeni());
		dto.setBodovi(zadatak.getBodovi());
		dto.setSprintId(zadatak.getSprint().getId());
		dto.setSprintIme(zadatak.getSprint().getIme());
		dto.setStanjeId(zadatak.getStanje().getId());
		dto.setStanjeIme(zadatak.getStanje().getIme());
		return dto;
	}
	
	public List<ZadatakDTO> convert(List<Zadatak> zadaci){
		List<ZadatakDTO> dto = new ArrayList<>();
		for(Zadatak z: zadaci) {
			dto.add(convert(z));
		}
		return dto;
	}
}
