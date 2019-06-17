package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Product;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.product.StProductRegisterHelp;
import br.com.randomthings.strategy.product.StProductRequiredData;
import br.com.randomthings.strategy.product.StProductValidateExistence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ProductSequence {

	@Autowired
	StProductValidateExistence stProductValidateExistence;
	
	@Autowired
	StProductRegisterHelp stProductRegisterHelp;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StProductRequiredData stProductRequiredData;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;

	@Bean("SAVE_PRODUCT")
	public Sequence<Product> saveProduct() {
		return new Sequence<Product>()
				.add(stProductRegisterHelp)
				.add(stAdminAuthorization)
				.add(stProductRequiredData)
				.add(stProductValidateExistence)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_PRODUCT")
	public Sequence<Product> updateProduct() {
		return new Sequence<Product>()
				.add(stProductRegisterHelp)
				.add(stAdminAuthorization)
				.add(stProductRequiredData)
				.add(stProductValidateExistence)
				.add(stLastUpdate);
	}
	
	@Bean("DELETE_PRODUCT")
	public Sequence<Activation> deleteProduct() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
