package br.com.randomthings.dto;

import br.com.randomthings.domain.DomainEntity;

public interface IDTO <Domain extends DomainEntity> {
	public IDTO from(Domain domain);
	public Domain fill(Long... params);
	
}
