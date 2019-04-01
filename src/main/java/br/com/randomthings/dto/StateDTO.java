package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.State;
import lombok.Data;

@Data
public class StateDTO {
	
	private Long id;
	private String name;
	private String code;
	private List<CityDTO> cities;
	
	private void setCities(Set<City> set) {
		cities = new ArrayList<CityDTO>();
		for(City city: set) {
			cities.add(CityDTO.from(city));
		}
	}
	
	public static StateDTO from(State state) {
		StateDTO stateDTO = new StateDTO();
		
		stateDTO.setId(state.getId());
		stateDTO.setName(state.getName());
		stateDTO.setCode(state.getStateCode());
		stateDTO.setCities(state.getCities());
		
		return stateDTO;
	}
}
