package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="_change")
public class Change extends DomainEntity {
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
	private Order order;
	
	@OneToMany(mappedBy="change", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<ChangeItem> items = new HashSet<>();
	
	@Column(name = "status_change", nullable = true)
	@Enumerated(EnumType.STRING)
	@Basic(fetch=FetchType.EAGER)
	private StatusChange statusChange;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "client_id")
	private Client client;

}
