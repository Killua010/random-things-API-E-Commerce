package br.com.randomthings.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.randomthings.dto.ClientDTO;

public class ClientPasswordEqualsValidator implements ConstraintValidator<ClientPasswordEquals, ClientDTO> {


	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		if(objDto.getPassword() != null && objDto.getConfirmPassword() != null &&
				!objDto.getPassword().equals(objDto.getConfirmPassword())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Senhas diferentes").addPropertyNode("password")
					.addConstraintViolation();
			return false;
		}
		
		return true;
	}
}