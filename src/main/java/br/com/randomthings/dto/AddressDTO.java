package br.com.randomthings.dto;

import br.com.randomthings.domain.Address;
import lombok.Data;

@Data
public class AddressDTO extends EntityDTO {
	
	private String street;
	
	private Integer number;
	
	private String neighborhood;
	
	private String zipCode;
	
	private String observation;
	
	private Boolean favorite;
	
	private Long cityId;
	
	private Long stateId;

	private Long residenceTypeId;
	
	private CityAddressDTO city;
	
	public static AddressDTO from(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setCreationDate(address.getCreationDate());
		addressDTO.setId(address.getId());
		addressDTO.setLastUpdate(address.getLastUpdate());
		addressDTO.setStatus(address.getStatus());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setNumber(address.getNumber());
		addressDTO.setNeighborhood(address.getNeighborhood());
		addressDTO.setZipCode(address.getZipCode());
		addressDTO.setObservation(address.getObservation());
		addressDTO.setFavorite(address.getFavorite());
		addressDTO.setCityId(address.getCity().getId());
		addressDTO.setStateId(address.getCity().getState().getId());
		addressDTO.setResidenceTypeId(address.getResidenceType().getId());
		addressDTO.setCity(CityAddressDTO.from(address.getCity()));
		
		return addressDTO;
	}
	
}
