package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_credit_card")
public class CreditCard extends DomainEntity {
	private String number;
	private String printedName;
	private String securityCode;
	private Boolean favorite;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crediti_card_flag_id")
	private CreditCardFlag flag;
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
}
