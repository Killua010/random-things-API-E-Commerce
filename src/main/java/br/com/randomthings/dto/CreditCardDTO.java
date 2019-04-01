package br.com.randomthings.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CreditCardDTO extends EntityDTO {
	
	@NotBlank(message = "O campo numero é obrigatório.")
	@NotNull(message = "O campo numero é obrigatório.")
	@Length(min = 16, max = 16, message = "O campo numero deve ter 16 caracteres")
	private String number;
	
	@NotBlank(message = "O campo nome impresso é obrigatório.")
	@NotNull(message = "O campo nome impresso é obrigatório.")
	@Length(min = 3, max = 100, message = "O campo nome impresso deve ter entre 3 e 100 caracteres")
	private String printedName;
	
	@NotBlank(message = "O campo código de segurança é obrigatório.")
	@NotNull(message = "O campo código de segurança é obrigatório.")
	@Length(min = 3, max = 3, message = "O campo código de segurança deve ter 3 caracteres")
	private String securityCode;
	
	@NotNull(message = "O campo favorito é obrigatório.")
	private Boolean favorite;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "O id da bandeira é obrigatório.")
	@Min(value=1, message="O id da bandeira deve ser maior ou igual a 1")
	private Long creditCardFlagId;
	
	private FlagDTO flag;
		
	public static CreditCardDTO from(CreditCard creditCard) {
		CreditCardDTO creditCardDTO = new CreditCardDTO();
		
		creditCardDTO.setCreationDate(creditCard.getCreationDate());
		creditCardDTO.setId(creditCard.getId());
		creditCardDTO.setLastUpdate(creditCard.getLastUpdate());
		creditCardDTO.setStatus(creditCard.getStatus());
		creditCardDTO.setPrintedName(creditCard.getPrintedName());
		creditCardDTO.setSecurityCode(creditCard.getSecurityCode());
		
		creditCardDTO.setNumber(creditCard.getNumber());
		creditCardDTO.setSecurityCode(creditCard.getSecurityCode());
		creditCardDTO.setFavorite(creditCard.getFavorite());
		creditCardDTO.setFlag(FlagDTO.from(creditCard.getFlag()));
		
		return creditCardDTO;
	}
	
	public void fill(CreditCard card) {
		card.setFavorite(favorite);
		card.getFlag().setId(creditCardFlagId);
		card.setNumber(number);
		card.setPrintedName(printedName);
		card.setSecurityCode(securityCode);
	}
}