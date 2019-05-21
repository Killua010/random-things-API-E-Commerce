package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.State;
import lombok.Data;

@Data
@Component
public class StateDTO extends AbstractDTO<State> {
	
	private String name;
	private String code;
	private List<CityDTO> cities;
	
	@Override
	public IDTO from(State state) {
		StateDTO stateDTO = new StateDTO();
		this.from(state, stateDTO);
		
		stateDTO.setName(state.getName());
		stateDTO.setCode(state.getStateCode());
		stateDTO.setCities(state.getCities());
		
		return stateDTO;
	}

	@Override
	public State fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
	private void setCities(Set<City> set) {
		cities = new ArrayList<CityDTO>();
		for(City city: set) {
			cities.add((CityDTO) new CityDTO().from(city));
		}
	}
}
