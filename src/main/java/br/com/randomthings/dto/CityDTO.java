package br.com.randomthings.dto;

import br.com.randomthings.domain.City;
import lombok.Data;

@Data
public class CityDTO {
	
	private Long id;
	private String name;
	
	public static CityDTO from(City city) {
		CityDTO cityDTO = new CityDTO();
		
		cityDTO.setId(city.getId());
		cityDTO.setName(city.getName());
		
		return cityDTO;
	}
}
