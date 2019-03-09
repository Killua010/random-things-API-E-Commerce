package br.com.randomthings.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import br.com.randomthings.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class EntityDto implements Idto{
	protected Long id;

	protected Boolean status;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime lastUpdate;
	
	public abstract DomainEntity getEntity(Long id);
	
	public abstract EntityDto getDTO(DomainEntity entity);
}
