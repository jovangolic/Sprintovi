package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.web.dto.SprintDTO;

@Component
public class SprintToSprintDto implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint sprint) {
		SprintDTO dto = new SprintDTO();
		dto.setId(sprint.getId());
		dto.setIme(sprint.getIme());
		dto.setUkupnoBodova(sprint.getUkupnoBodova());
		return dto;
	}
	
	public List<SprintDTO> convert(List<Sprint> sprintovi){
		List<SprintDTO> dto = new ArrayList<>();
		for(Sprint s : sprintovi) {
			dto.add(convert(s));
		}
		return dto;
	}

}
