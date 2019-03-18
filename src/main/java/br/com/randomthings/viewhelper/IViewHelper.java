package br.com.randomthings.viewhelper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.randomthings.domain.DomainEntity;

public interface IViewHelper {
	
	List<EntityViewHelper> getListViewHelper(List<DomainEntity> entities);
	
	@JsonIgnore
	DomainEntity getEntity();
}
