package br.com.randomthings.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EntityDTO {
	protected Long id;

	protected Boolean status;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime creationDate;
	
	private LocalDateTime lastUpdate;
	
}
