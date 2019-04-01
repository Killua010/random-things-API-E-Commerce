package br.com.randomthings.dto;

import br.com.randomthings.domain.ResidenceType;
import lombok.Data;

@Data
public class ResidenceTypeDTO extends EntityDTO  {
	
	private String name;
	
	public static ResidenceTypeDTO from(ResidenceType residenceType) {
		ResidenceTypeDTO residenceTypeDTO = new ResidenceTypeDTO();
		residenceTypeDTO.setName(residenceType.getName());
		residenceTypeDTO.setCreationDate(residenceType.getCreationDate());
		residenceTypeDTO.setId(residenceType.getId());
		residenceTypeDTO.setLastUpdate(residenceType.getLastUpdate());
		residenceTypeDTO.setStatus(residenceType.getStatus());
		return residenceTypeDTO;
	}
}
