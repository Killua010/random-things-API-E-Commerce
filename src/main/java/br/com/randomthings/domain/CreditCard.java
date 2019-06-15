package br.com.randomthings.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_credit_card")
public class CreditCard extends DomainEntity {
	private String number;
	private String printedName;
	private String securityCode;
	private Boolean favorite;
	
    @JoinColumn(name = "credit_card_flag_id")
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private CreditCardFlag flag;
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
}
