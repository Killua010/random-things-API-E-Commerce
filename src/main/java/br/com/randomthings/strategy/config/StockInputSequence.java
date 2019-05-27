package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.domain.StockInput;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.provider.StProviderRegisterHelp;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;
import br.com.randomthings.strategy.stock_input.StStockInpputRegisterHelp;

@Configuration
public class StockInputSequence {

	@Autowired
	StStockInpputRegisterHelp stStockInpputRegisterHelp;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Bean("SAVE_STOCKINPUT")
	public Sequence<StockInput> saveCategory() {
		return new Sequence<StockInput>()
				.add(stStockInpputRegisterHelp)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_STOCKINPUT")
	public Sequence<StockInput> updateCategory() {
		return new Sequence<StockInput>()
				.add(stStockInpputRegisterHelp)
				.add(stLastUpdate);
	}

}
