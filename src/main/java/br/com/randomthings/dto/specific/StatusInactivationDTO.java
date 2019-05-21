package br.com.randomthings.dto.specific;

import java.util.ArrayList;
import java.util.List;

import br.com.randomthings.domain.StatusInactivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusInactivationDTO {
	private String name;
	private String description;
	
	public static List<StatusInactivationDTO> getAll(){
		List<StatusInactivationDTO> dtos = new ArrayList<StatusInactivationDTO>();
		for(StatusInactivation inactivation: StatusInactivation.values()) {
			StatusInactivationDTO inactivationDTO = new StatusInactivationDTO();
			inactivationDTO.setDescription(inactivation.getDescription());
			inactivationDTO.setName(inactivation.toString());
			dtos.add(inactivationDTO);
		}
		return dtos;
	}

	
}
