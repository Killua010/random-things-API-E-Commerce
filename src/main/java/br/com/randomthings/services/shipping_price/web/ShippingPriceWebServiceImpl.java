package br.com.randomthings.services.shipping_price.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.dto.DeliveryAddressDTO;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.client.ClientService;

@Service
public class ShippingPriceWebServiceImpl implements ShippingPriceWebService {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DeliveryAddressService addressService;
	
	@Override
	public Float calculatePrice(Long clientId, Long addressId) {
		Client client = clientService.findById(clientId);
		DeliveryAddress address = addressService.findById(addressId);
		
		
		Float weight = (float) 0.0;
		
		for(ShoppingCartItem item: client.getShoppingCart().getCartItems()) {
			for(TechnicalRow row: item.getProduct().getTechnicalRows()) {
				if(row.getField().getName().equals("Peso")) {
					String[] wg = row.getDescription().split(" Kg");
					weight += (Float.parseFloat(wg[0]) * item.getQuantity());
					continue;
				}
			}
		}
		
		Float price = (float) (15 + (weight * 1.5));
		
		return price;
	}

}
