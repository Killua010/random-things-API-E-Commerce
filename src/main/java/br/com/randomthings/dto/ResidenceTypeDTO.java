package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.ResidenceType;
import lombok.Data;

@Data
@Component
public class ResidenceTypeDTO extends AbstractDTO<ResidenceType>  {
	
	private String name;

	@Override
	public IDTO from(ResidenceType residenceType) {
		ResidenceTypeDTO residenceTypeDTO = new ResidenceTypeDTO();
		this.from(residenceType, residenceTypeDTO);
		residenceTypeDTO.setName(residenceType.getName());
		return residenceTypeDTO;
	}

	@Override
	public ResidenceType fill(Long... params) {
		ResidenceType residenceType = new ResidenceType();
		residenceType.setName(name);
		residenceType.setId((null == params[0]) ? null : params[0]);
		residenceType.setStatus((null == this.status) ? null : this.status);
		return residenceType;
	}
}
