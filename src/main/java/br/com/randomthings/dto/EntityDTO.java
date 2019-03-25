package br.com.randomthings.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EntityDTO {
	protected Long id;

	protected Boolean status;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime lastUpdate;
	
}
