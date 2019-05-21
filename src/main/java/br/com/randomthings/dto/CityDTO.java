package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.City;
import lombok.Data;

@Data
@Component
public class CityDTO extends AbstractDTO<City>{
	
	private String name;

	@Override
	public IDTO from(City city) {
		CityDTO cityDTO = new CityDTO();
		this.from(city, cityDTO);
		
		cityDTO.setName(city.getName());
		
		return cityDTO;
	}

	@Override
	public City fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
}
