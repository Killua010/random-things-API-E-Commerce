package br.com.randomthings.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.randomthings.domain.DomainEntity;

public interface Idto {
	
	List<EntityDto> getListDto(List<DomainEntity> entities);
	
	@JsonIgnore
	DomainEntity getEntity();
}
