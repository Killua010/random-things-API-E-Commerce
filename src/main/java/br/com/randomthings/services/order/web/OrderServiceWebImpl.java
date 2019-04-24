package br.com.randomthings.services.order.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import br.com.randomthings.dto.GetOrderDTO;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.card.CreditCardService;
import br.com.randomthings.services.cart_item.CartItemService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.services.shipping_price.web.ShippingPriceWebService;
import br.com.randomthings.services.shopping_cart.ShoppingCartService;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;
import br.com.randomthings.strategy.standard.StRegistration;

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
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private StRegistration stRegistration;
	
	@Override
	public Order save(GetOrderDTO orderDTO) {
		Order order = new Order();
		Client client = clientService.findById(orderDTO.getClientId());
		ShippingPrice shippingPrice = new ShippingPrice();
		DeliveryAddress address = addressService.findById(orderDTO.getAddressId());
		PayamentType payamentType = new PayamentType();
		CardPayment cardPayment = new CardPayment();
		CreditCard card = cardService.findById(orderDTO.getCardId());
		
		cardPayment.setTotalValue(orderDTO.getOrderValue());
		cardPayment.setCard(card);
		stRegistration.execute(cardPayment);
		payamentType.setCardPayment(cardPayment);
		stRegistration.execute(payamentType);
		shippingPrice.setAddress(address);
		shippingPrice.setBusinessDays(10);
		shippingPrice.setValue(priceWebService.calculatePrice(client.getId(), orderDTO.getAddressId()));
		stRegistration.execute(shippingPrice);
		ShoppingCart cart = cartService.findById(orderDTO.getCart().getId());
		
		for(ShoppingCartItem cartItem: cart.getCartItems()) {
			OrderItem item = new OrderItem();
			stRegistration.execute(item);
			item.setOrder(order);
			item.setProduct(cartItem.getProduct());
			item.setQuantity(cartItem.getQuantity());
			order.getItems().add(item);
		}
		
		cartWebService.cleanShoppingCart(client.getId());
		
		StringBuilder errors = runStrategys(order, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		order.setClient(client);
		order.setShippingPrice(shippingPrice);
		order.setPayamentType(payamentType);
		order.setStatusOrder(StatusOrder.APROVADO);
		order.setOrderValue(cart.getSubTotal());
		
		
		client.getShoppingCart().setSubTotal((float) 0.0);
		client.getShoppingCart().setLastUpdate(LocalDateTime.now());
		
		for(ShoppingCartItem cartItem: client.getShoppingCart().getCartItems()) {
			cartItem.setCart(null);
			cartItemService.delete(cartItem);
		}
		
		cartService.save(client.getShoppingCart());
		
		return orderService.save(order);
	}

	@Override
	public List<OrderDTO> getByStatus(String status) {
		StatusOrder order = StatusOrder.valueOf(status);
		
		List<OrderDTO> orders = new ArrayList<>(); 
		for(Order od: orderService.getByStatus(order)) {
			orders.add(OrderDTO.from(od));
		}
		
		return orders;
	}

	@Override
	public OrderDTO nextStep(Long id) {
		Order order = orderService.findById(id);
		Integer sequence = order.getStatusOrder().getOrder();
		Arrays.asList(StatusOrder.values()).
        	forEach(st -> {
        		if(st.getOrder().equals(sequence+1)) {
        			order.setStatusOrder(st);
        		}
        	});
		
		return OrderDTO.from(orderService.save(order));
	}

}
