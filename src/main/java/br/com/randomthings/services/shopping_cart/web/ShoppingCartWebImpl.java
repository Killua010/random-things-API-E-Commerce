package br.com.randomthings.services.shopping_cart.web;

import java.util.Date;

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

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.domain.Stock;
import br.com.randomthings.dto.CartItemDTO;
import br.com.randomthings.dto.ShoppingCartDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.job.ShoppingCartJob;
import br.com.randomthings.services.cart_item.CartItemService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.shopping_cart.ShoppingCartService;
import br.com.randomthings.services.stock.StockService;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;
import br.com.randomthings.viewhelper.ProductViewHelper;

@Service
public class ShoppingCartWebImpl implements ShoppingCartWebService {
	
	private Scheduler scheduler;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StRegistration stRegistration;
	
	@Autowired
	private StLastUpdate stLastUpdate;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@PostConstruct
	public void init() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
	}

	@Override
	public ShoppingCart insertProduct(ProductViewHelper helper, Long clientId) {
		Client client = clientService.findById(clientId);
		ShoppingCart cart;
		if(client.getShoppingCart() == null) {
			cart = new ShoppingCart();
			cart.setSubTotal((float) 0.0);
			stRegistration.execute(cart);
		} else {
			cart = client.getShoppingCart();
		}
		
		Product product = productService.findById(helper.getId());
		
		if(product.getStock().getTotalQuantity() == 0) {
			throw new StrategyValidation(new StringBuilder("Produto com id " + product.getId() + " sem estoque"));
		}
		
		for(ShoppingCartItem item : cart.getCartItems()) {
			if(item.getProduct().equals(product)) {
				return cart;
			}
		}
		
		Integer totalStock = product.getStock().getTotalQuantity() - 1;
		product.getStock().setTotalQuantity(totalStock);
		
		ShoppingCartItem cartItem = new ShoppingCartItem();
		cartItem.setCart(client.getShoppingCart());
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setCart(cart);
		cart.setSubTotal((float) cart.getSubTotal() + product.getPrice());
		cart.getCartItems().add(cartItem);
		
		stRegistration.execute(cartItem);
		stLastUpdate.execute(cart);
		
		client.setShoppingCart(cart);
		cart.setClient(client);
		
		cart = shoppingCartService.save(cart);
		startJob(client.getId());
		return cart;
	}

	@Override
	public void cleanShoppingCart(Long clientId) {
		Client client = clientService.findById(clientId);
		for(ShoppingCartItem cartItem: client.getShoppingCart().getCartItems()) {
			if(cartItem.getStatus() == false) {
				continue;
			}				
			cartItem.setStatus(false);
			Integer newStock = cartItem.getProduct().getStock().getTotalQuantity() + cartItem.getQuantity();
			cartItem.getProduct().getStock().setTotalQuantity(newStock);
			stockService.save(cartItem.getProduct().getStock());
		}
		shoppingCartService.save(client.getShoppingCart());
	}
	
	private void startJob(Long id) {
		JobDetail detail = JobBuilder.newJob(ShoppingCartJob.class).withIdentity(id.toString()).build();
		detail.getJobDataMap().put("ID", id);
		detail.getJobDataMap().put("SERVICE", this);
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setName(id.toString());
		Date now = new Date();
		now.setTime(now.getTime() + (1000 * 60 * 15));
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

	@Override
	public ShoppingCart getShoppingCartByClientId(Long clientId) {
		ShoppingCart cart = clientService.findById(clientId).getShoppingCart();
		if(cart == null) {
			cart = new ShoppingCart();
		}
		return cart;
	}

	@Override
	public ShoppingCart removeProduct(ProductViewHelper helper, Long clientId) {
		Client client = clientService.findById(clientId);		
		Product product = productService.findById(helper.getId());
		for(ShoppingCartItem cartItem : client.getShoppingCart().getCartItems()) {
			if(cartItem.getProduct().equals(product)) {
				if(cartItem.getStatus() == true) {
					Integer newStock = cartItem.getProduct().getStock().getTotalQuantity() + cartItem.getQuantity();
					cartItem.getProduct().getStock().setTotalQuantity(newStock);
					stockService.save(cartItem.getProduct().getStock());
				}
				client.getShoppingCart().getCartItems().remove(cartItem);
				cartItemService.delete(cartItem.getId());
				break;
			}
		}
		ShoppingCart cart = shoppingCartService.save(client.getShoppingCart());

		return cart;
	}

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCartDTO cartDTO) {
		ShoppingCart shoppingCart = shoppingCartService.findById(cartDTO.getId());
		Float subTotal = (float) 0.0;
		for(ShoppingCartItem cartItem: shoppingCart.getCartItems()) {
			for(int i = 0; i < cartDTO.getIdItem().length; i++) {
				if(cartItem.getId().equals(cartDTO.getIdItem()[i])) {
					cartItem.setQuantity(cartDTO.getQuantityItem()[i]);
					subTotal += (cartItem.getQuantity() * cartItem.getProduct().getPrice());
					Integer total = cartItem.getProduct().getStock().getTotalQuantity() + 1;
					total -= cartDTO.getQuantityItem()[i];
					cartItem.getProduct().getStock().setTotalQuantity(total);
					stockService.save(cartItem.getProduct().getStock());
				}
			}
		}
		shoppingCart.setSubTotal(subTotal);
		startJob(shoppingCart.getClient().getId());
		return shoppingCartService.save(shoppingCart);
	}

}
