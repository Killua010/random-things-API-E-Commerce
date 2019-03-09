package br.com.randomthings.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.randomthings.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {
	
	private StringBuilder Response;
	private List<DomainEntity> resultEntities = new ArrayList<>();
	private Integer httpStatus;
	
}
