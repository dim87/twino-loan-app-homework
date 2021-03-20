package com.example.twinoloanapphomework.api.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MappingService<ENTITY, DTO> {

	@Autowired
	private ModelMapper modelMapper;

	public ENTITY convertDto(final DTO item) {
		return modelMapper.map(item, getEntityClass());
	}

	public List<ENTITY> convertDto(final List<DTO> list) {
		return list.stream().map(item -> modelMapper.map(item, getEntityClass())).collect(Collectors.toList());
	}

	public List<DTO> convertEntity(final List<ENTITY> list) {
		return list.stream().map(item -> modelMapper.map(item, getDtoClass())).collect(Collectors.toList());
	}

	public DTO convertEntity(final ENTITY item) {
		return modelMapper.map(item, getDtoClass());
	}

	protected abstract Class<ENTITY> getEntityClass();

	protected abstract Class<DTO> getDtoClass();
}
