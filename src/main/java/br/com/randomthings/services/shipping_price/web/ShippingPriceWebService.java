package br.com.randomthings.services.shipping_price.web;

public interface ShippingPriceWebService {
	public Float calculatePrice(Long clientId, Long addressId);
}
