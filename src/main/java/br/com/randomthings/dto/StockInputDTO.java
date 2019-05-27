package br.com.randomthings.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.domain.StockInput;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Component
public class StockInputDTO extends AbstractDTO<StockInput> {
	
	private Integer quantity;
	
	private Float value;
	
	private Long providerId;
	
	private ProviderDTO provider;
	
	private Long productId;
	
	private ProductDTO product;
	
	@Override
	public IDTO from(StockInput domain) {
		StockInputDTO stockInputDTO = new StockInputDTO();
		
		this.from(domain, stockInputDTO);
		stockInputDTO.setQuantity(domain.getQuantity());
		stockInputDTO.setValue(domain.getValue());
		stockInputDTO.setProduct((ProductDTO) new ProductDTO().from(domain.getProduct()));
		stockInputDTO.setProvider((ProviderDTO) new ProviderDTO().from(domain.getProvider()));
		
		return stockInputDTO;
	}

	@Override
	public StockInput fill(Long... params) {
		StockInput stockInput = new StockInput();
		Product product = new Product();
		Provider provider = new Provider();
		
		product.setId(productId);
		provider.setId(providerId);
		stockInput.setProduct(product);
		stockInput.setProvider(provider);
		stockInput.setQuantity(quantity);
		stockInput.setValue(value);
		
		return stockInput;
	}
	
}
