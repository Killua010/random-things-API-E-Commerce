package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.Address;
import br.com.randomthings.dto.specific.CityAddressDTO;
import lombok.Data;

@Data
@Component
public class AddressDTO extends AbstractDTO<Address> {
	
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

	@Override
	public IDTO from(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		this.from(address, addressDTO);
		
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

	@Override
	public Address fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
