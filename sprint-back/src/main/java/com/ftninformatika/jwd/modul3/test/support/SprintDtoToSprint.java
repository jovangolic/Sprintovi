package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Sprint;
import com.ftninformatika.jwd.modul3.test.service.SprintService;
import com.ftninformatika.jwd.modul3.test.web.dto.SprintDTO;

@Component
public class SprintDtoToSprint implements Converter<SprintDTO, Sprint> {
	
	@Autowired
	private SprintService sprintService;

	@Override
	public Sprint convert(SprintDTO dto) {
		Sprint sprint = null;
		if(dto.getId() == null) {
			sprint = new Sprint();
		}
		else {
			sprint = sprintService.findOneById(dto.getId());
		}
		if(sprint != null) {
			sprint.setId(dto.getId());
			sprint.setIme(dto.getIme());
			sprint.setUkupnoBodova(dto.getUkupnoBodova());
		}
		return sprint;
	}

}
