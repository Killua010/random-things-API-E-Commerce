package br.com.randomthings.dto;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.DeliveryAddress;
import lombok.Data;

@Data
public class CityDTO {
	
	private Long cityId;
	private String cityName;
	private Long stateId;
	private String stateName;
	private Long countryId;
	private String countryName;
	
	public static CityDTO from(City city) {
		CityDTO cityDTO = new CityDTO();
		
		cityDTO.setCityId(city.getId());
		cityDTO.setCityName(city.getName());
		cityDTO.setStateId(city.getState().getId());
		cityDTO.setStateName(city.getState().getName());
		cityDTO.setCountryId(city.getState().getCountry().getId());
		cityDTO.setCountryName(city.getState().getCountry().getName());
		
		return cityDTO;
	}
}
