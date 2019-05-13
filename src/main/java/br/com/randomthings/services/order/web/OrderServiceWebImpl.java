package br.com.randomthings.services.order.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CardPayment;
import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CouponPayment;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.PayamentType;
import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.domain.ShippingPrice;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.domain.StatusChange;
import br.com.randomthings.domain.StatusOrder;
import br.com.randomthings.dto.GetOrderDTO;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.job.OrderPaymantJob;
import br.com.randomthings.job.ShoppingCartJob;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.card.CreditCardService;
import br.com.randomthings.services.cart_item.CartItemService;
import br.com.randomthings.services.change_coupon.ChangeCouponService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.coupons.PromotionalCouponService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.services.shipping_price.web.ShippingPriceWebService;
import br.com.randomthings.services.shopping_cart.ShoppingCartService;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;
import br.com.randomthings.services.stock.StockService;
import br.com.randomthings.strategy.standard.StRegistration;

@Service
public class OrderServiceWebImpl extends ExecuteStrategys<Order> implements OrderServiceWeb {
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private ChangeCouponService changeCouponService;
	
	@Autowired
	private PromotionalCouponService promotionalCouponService;
	
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
	
	private Scheduler scheduler;
	
	@PostConstruct
	public void init() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
	}
	
	@Override
	public Order save(GetOrderDTO orderDTO) {

		// create and get objects of database
		Order order = new Order();
		Client client = clientService.findById(orderDTO.getClientId());
		ShippingPrice shippingPrice = new ShippingPrice();
		DeliveryAddress address = addressService.findById(orderDTO.getAddressId());
		PayamentType payamentType = new PayamentType();
		CardPayment cardPayment = new CardPayment();
		CreditCard card = cardService.findById(orderDTO.getCardId());
		
		Float value = (float) 0.0;
		
		ShoppingCart cart = cartService.findById(orderDTO.getCart().getId());
		
		
		for(ShoppingCartItem cartItem: cart.getCartItems()) {
			value += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
			OrderItem item = new OrderItem();
			stRegistration.execute(item);
			item.setOrder(order);
			item.setProduct(cartItem.getProduct());
			item.setQuantity(cartItem.getQuantity());
			order.getItems().add(item);
		}
		
		// verify if has coupons(change and promotional)
		value = addCoupons(orderDTO, client, value, payamentType);
		
		cardPayment.setTotalValue(value);
		cardPayment.setCard(card);
		stRegistration.execute(cardPayment);
		payamentType.setCardPayment(cardPayment);
		stRegistration.execute(payamentType);
		shippingPrice.setAddress(address);
		shippingPrice.setBusinessDays(10);
		shippingPrice.setValue(priceWebService.calculatePrice(client.getId(), orderDTO.getAddressId()));
		stRegistration.execute(shippingPrice);
		
		
		cartWebService.cleanShoppingCart(client.getId());
		
		StringBuilder errors = runStrategys(order, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		order.setClient(client);
		order.setShippingPrice(shippingPrice);
		order.setPayamentType(payamentType);
		order.setStatusOrder(StatusOrder.EMPROCESSAMENTO);
		order.setOrderValue(value);
		
		client.getShoppingCart().setSubTotal((float) 0.0);
		client.getShoppingCart().setLastUpdate(LocalDateTime.now());
		
		for(ShoppingCartItem cartItem: client.getShoppingCart().getCartItems()) {
			cartItem.setCart(null);
			cartItemService.delete(cartItem);
		}
		
		cartService.save(client.getShoppingCart());
		
		order = orderService.save(order);
		
		startJob(order.getId());
		
		return order;
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

	@Override
	public List<OrderDTO> getByIdClient(Long id) {
		Client client = clientService.findById(id);
		List<OrderDTO> orders = new ArrayList<>(); 
		Order aux;
		Boolean flag = false;
		List<Order> list = orderService.getByClient(client);
		while(!flag) {
			flag = true;
			for(int i = 0; i < list.size() -1; i++) {
				if(list.get(i).getCreationDate().isBefore(list.get(i+1).getCreationDate())) {
					aux = list.get(i);
					list.set(i, list.get(i+1));
					list.set(i+1, aux);
					flag = false;
				}
			}
		}
		for(Order od: list) {
			orders.add(OrderDTO.from(od));
		}
		
		return orders;
	}
	
	@Override
	public void reprovedOrder(Long id) {
		Order order = orderService.findById(id);
		order.setStatusOrder(StatusOrder.REPROVADO);
		orderService.save(order);
		
		for(OrderItem item: order.getItems()) {
			Integer newStock = item.getProduct().getStock().getTotalQuantity() + item.getQuantity();
			item.getProduct().getStock().setTotalQuantity(newStock);
			stockService.save(item.getProduct().getStock());
		}
		
		if(order.getPayamentType().getCouponPayment() != null && order.getPayamentType().getCouponPayment().getChangeCoupon() != null) {
			ChangeCoupon newCoupon = new ChangeCoupon();
			newCoupon.setClient(order.getClient());
			newCoupon.setValue(order.getPayamentType().getCouponPayment().getChangeCouponValue());
			stRegistration.execute(newCoupon);
			changeCouponService.save(newCoupon);
		}
		
		
	}
	
	private void startJob(Long id) {
		JobDetail detail = JobBuilder.newJob(OrderPaymantJob.class).withIdentity(id.toString()).build();
		detail.getJobDataMap().put("ID", id);
		detail.getJobDataMap().put("SERVICE", this);
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setName(id.toString());
		Date now = new Date();
		now.setTime(now.getTime() + (1000 * 3));
		trigger.setStartTime(now);
		trigger.setRepeatCount(0);
		trigger.afterPropertiesSet();
		
		try {
			JobKey jobKey = new JobKey(id.toString());
			if(scheduler.checkExists(jobKey)) {
				scheduler.deleteJob(jobKey);
			}
			
			scheduler.scheduleJob(detail, trigger.getObject());
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	private Float addCoupons(GetOrderDTO orderDTO, Client client, Float value, PayamentType payamentType) {
		
		if(!orderDTO.getChangeCoupon().trim().isEmpty() || !orderDTO.getPromotionalCoupon().trim().isEmpty()) {
			
			CouponPayment couponPayment = new CouponPayment();
			
			
			if(!orderDTO.getPromotionalCoupon().trim().isEmpty()) {
				PromotionalCoupon coupon = promotionalCouponService.findByName(orderDTO.getPromotionalCoupon());
				if(!coupon.getShelfLife().isBefore(LocalDate.now())) {
					if(coupon.getValue() > value) {
						couponPayment.setPromotionalCoupon(coupon);
						couponPayment.setPromotionalCouponValue(value);
						value = (float) 0.0;
					} else {
						couponPayment.setPromotionalCoupon(coupon);
						couponPayment.setPromotionalCouponValue(coupon.getValue());
						value = value - coupon.getValue();
					}
				} else {
					throw new StrategyValidation(new StringBuilder("Cupom vencido no dia " + coupon.getShelfLife().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
				}
			}
			
			
			if(!orderDTO.getChangeCoupon().trim().isEmpty()) {
				ChangeCoupon coupon = changeCouponService.findByNameAndClient(orderDTO.getChangeCoupon(), client);
				if(coupon.getStatus() == true && coupon.getValue() > value) {
					ChangeCoupon newCoupon = new ChangeCoupon();
					newCoupon.setClient(client);
					Float newValue = coupon.getValue() - value;
					newCoupon.setValue(newValue);
					couponPayment.setChangeCouponValue(value);
					couponPayment.setChangeCoupon(coupon);
					coupon.setStatus(false);
					value = (float) 0.0;
					stRegistration.execute(newCoupon);
					changeCouponService.save(newCoupon);
				} else if(coupon.getStatus() == true && coupon.getValue() < value) {
					coupon.setStatus(false);
					value = value - coupon.getValue();
					couponPayment.setChangeCouponValue(coupon.getValue());
					couponPayment.setChangeCoupon(coupon);
				} else {
					throw new StrategyValidation(new StringBuilder("Cupom " + coupon.getName() +" já utilizado!!"));
				}
				
				changeCouponService.save(coupon);
			}
			
			payamentType.setCouponPayment(couponPayment);
		}
		return value;
	}
	

}
