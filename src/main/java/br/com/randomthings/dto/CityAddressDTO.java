package br.com.randomthings.dto;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.DeliveryAddress;
import lombok.Data;

@Data
public class CityAddressDTO {
	
	private Long cityId;
	private String cityName;
	private Long stateId;
	private String stateName;
	private String stateCode;
	private Long countryId;
	private String countryName;
	
	public static CityAddressDTO from(City city) {
		CityAddressDTO cityDTO = new CityAddressDTO();
		
		cityDTO.setCityId(city.getId());
		cityDTO.setCityName(city.getName());
		cityDTO.setStateId(city.getState().getId());
		cityDTO.setStateName(city.getState().getName());
		cityDTO.setStateCode(city.getState().getStateCode());
		cityDTO.setCountryId(city.getState().getCountry().getId());
		cityDTO.setCountryName(city.getState().getCountry().getName());
		
		return cityDTO;
	}
}
