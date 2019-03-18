package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.randomthings.domain.StatusActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusActivationDTO {
	private String name;
	private String description;
	
	public static List<StatusActivationDTO> getAll(){
		List<StatusActivationDTO> dtos = new ArrayList<StatusActivationDTO>();
		for(StatusActivation activation: StatusActivation.values()) {
			StatusActivationDTO activationDTO = new StatusActivationDTO();
			activationDTO.setDescription(activation.getDescription());
			activationDTO.setName(activation.toString());
			dtos.add(activationDTO);
		}
		return dtos;
	}

	
}
