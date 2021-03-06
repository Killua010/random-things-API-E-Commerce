package br.com.randomthings.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.dto.specific.CityAddressDTO;
import lombok.Data;

@Data
@Component
public class DeliveryAddressDTO extends AbstractDTO<DeliveryAddress> {
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@NotNull(message = "O campo nome é obrigatório.")
	@Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
	private String fullName;
	
	@NotBlank(message = "O campo rua é obrigatório.")
	@NotNull(message = "O campo rua é obrigatório.")
	@Length(min = 3, max = 100, message = "O campo rua deve ter entre 3 e 100 caracteres")
	private String street;
	
	@NotNull(message = "O campo numero é obrigatório.")
	@Min(value=1, message="O campo numero deve ser maior ou igual a 1")
	private Integer number;
	
	@NotBlank(message = "O campo bairro é obrigatório.")
	@NotNull(message = "O campo bairro é obrigatório.")
	@Length(min = 3, max = 100, message = "O campo bairro deve ter entre 3 e 100 caracteres")
	private String neighborhood;
	
	@NotBlank(message = "O campo CEP é obrigatório")
	@Length(min = 8, max = 9, message = "O CEP deve conter 8 caracteres")
	private String zipCode;
	
	private String observation;
	
	@NotNull(message = "O campo favorito é obrigatório.")
	private Boolean favorite;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "O id da cidade é obrigatório.")
	@Min(value=1, message="O id da cidade deve ser maior ou igual a 1")
	private Long cityId;
	
	@NotNull(message = "O id do estado é obrigatório.")
	@Min(value=1, message="O id do estado deve ser maior ou igual a 1")
	private Long stateId;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "O id do tipo de residencia é obrigatório.")
	@Min(value=1, message="O id do tipo de residencia deve ser maior ou igual a 1")
	private Long residenceTypeId;
	
	private CityAddressDTO city;
	
	@Override
	public IDTO from(DeliveryAddress address) {
		DeliveryAddressDTO addressDTO = new DeliveryAddressDTO();
		this.from(address, addressDTO);

		addressDTO.setFullName(address.getFullName());
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
	public DeliveryAddress fill(Long... params) {
		DeliveryAddress address = new DeliveryAddress();
		City city = new City();
		ResidenceType residenceType = new ResidenceType();
		address.setResidenceType(residenceType);
		address.setCity(city);
		
		address.getCity().setId(cityId);
		address.setFullName(fullName);
		address.setNeighborhood(neighborhood);
		address.setNumber(number);
		address.setObservation(observation);
		address.getResidenceType().setId(residenceTypeId);
		address.setStreet(street);
		address.setZipCode(zipCode);
		address.setFavorite(favorite);
		
		address.setId((params.length == 0) ? null : params[0]);
		address.setStatus((null == this.status) ? null : this.status);
		
		return address;
		
	}
}
