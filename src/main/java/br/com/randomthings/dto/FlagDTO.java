package br.com.randomthings.dto;

import br.com.randomthings.domain.CreditCardFlag;
import lombok.Data;

@Data
public class FlagDTO extends EntityDTO  {
	
	private String name;
	
	public static FlagDTO from(CreditCardFlag flag) {
		FlagDTO flagDTO = new FlagDTO();
		flagDTO.setName(flag.getName());
		flagDTO.setCreationDate(flag.getCreationDate());
		flagDTO.setId(flag.getId());
		flagDTO.setLastUpdate(flag.getLastUpdate());
		flagDTO.setStatus(flag.getStatus());
		return flagDTO;
	}
}
