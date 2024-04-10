package com.ftninformatika.jwd.modul3.test.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Stanje;
import com.ftninformatika.jwd.modul3.test.web.dto.StanjeDTO;

@Component
public class StanjeToStanjeDto implements Converter<Stanje, StanjeDTO> {
	
	@Override
	public StanjeDTO convert(Stanje stanje) {
		StanjeDTO dto = new StanjeDTO();
		dto.setId(stanje.getId());
		dto.setIme(stanje.getIme());
		return dto;
	}
	
	
	public List<StanjeDTO> convert(List<Stanje> stanja){
		List<StanjeDTO> dto = new ArrayList<>();
		for(Stanje s : stanja) {
			dto.add(convert(s));
		}
		return dto;
	}

}
