package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.CreditCardFlag;
import lombok.Data;

@Data
@Component
public class FlagDTO extends AbstractDTO<CreditCardFlag>  {
	
	private String name;

	@Override
	public IDTO from(CreditCardFlag flag) {
		FlagDTO flagDTO = new FlagDTO();
		this.from(flag, flagDTO);
		
		flagDTO.setName(flag.getName());
		
		return flagDTO;
	}

	@Override
	public CreditCardFlag fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
}
