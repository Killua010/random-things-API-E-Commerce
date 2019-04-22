package br.com.randomthings.services.order.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CardPayment;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.PayamentType;
import br.com.randomthings.domain.ShippingPrice;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.domain.StatusOrder;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.card.CreditCardService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.services.shipping_price.web.ShippingPriceWebService;
import br.com.randomthings.services.shopping_cart.ShoppingCartService;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;

@Service
public class OrderServiceWebImpl extends ExecuteStrategys<Order> implements OrderServiceWeb {
	
	@Autowired
	private ShoppingCartWebService cartWebService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private CreditCardService cardService;

	@Autowired
	private DeliveryAddressService addressService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ShippingPriceWebService priceWebService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public Order save(OrderDTO orderDTO) {
		Order order = new Order();
		Client client = clientService.findById(orderDTO.getClientId());
		ShippingPrice shippingPrice = new ShippingPrice();
		DeliveryAddress address = addressService.findById(orderDTO.getAddressId());
		PayamentType payamentType = new PayamentType();
		CardPayment cardPayment = new CardPayment();
		CreditCard card = cardService.findById(orderDTO.getCardId());
		
		cardPayment.setTotalValue(orderDTO.getOrderValue());
		cardPayment.setCard(card);
		
		payamentType.setCardPayment(cardPayment);
		
		shippingPrice.setAddress(address);
		shippingPrice.setBusinessDays(10);
		shippingPrice.setValue(priceWebService.calculatePrice(client.getId(), orderDTO.getAddressId()));
		
		ShoppingCart cart = cartService.findById(orderDTO.getCart().getId());
		
		for(ShoppingCartItem cartItem: cart.getCartItems()) {
			OrderItem item = new OrderItem();
			item.setProduct(cartItem.getProduct());
			item.setQuantity(cartItem.getQuantity());
			order.getItems().add(item);
		}
		
		order.setClient(client);
		order.setShippingPrice(shippingPrice);
		order.setPayamentType(payamentType);
		order.setStatusOrder(StatusOrder.EMPROCESSAMENTO);
		order.setOrderValue(cart.getSubTotal());
		
		cartWebService.cleanShoppingCart(client.getId());
		
		StringBuilder errors = runStrategys(order, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return orderService.save(order);
	}

}
