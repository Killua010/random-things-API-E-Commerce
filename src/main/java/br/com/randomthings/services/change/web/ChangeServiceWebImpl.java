package br.com.randomthings.services.change.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.ChangeItem;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.StatusChange;
import br.com.randomthings.dto.ChangeDTO;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.change.ChangeService;
import br.com.randomthings.services.change_coupon.ChangeCouponService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.order.OrderItemService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.strategy.standard.StRegistration;

@Service
public class ChangeServiceWebImpl extends ExecuteStrategys<Change> implements ChangeServiceWeb{

	@Autowired
	private ChangeCouponService changeCouponService;
	
	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private StRegistration stRegistration;
	
	@Override
	public Change save(ChangeDTO changeDTO) {
		Change change = new Change();
		Client client = clientService.findById(changeDTO.getClientId());
		Order order = orderService.findById(changeDTO.getOrderId());
		change.setClient(client);
		change.setOrder(order);
		Order newOrder = change.getOrder();
		
		for(int i = 0; i < changeDTO.getIdItem().length; i++) {
			OrderItem orderItem = orderItemService.findById(changeDTO.getIdItem()[i]);
			Integer quantity = orderItem.getQuantity();
			for(Change changes: changeService.getByOrder(newOrder)) {
				for(ChangeItem changeItem: changes.getItems()) {
					if(changeItem.getProduct().equals(orderItem.getProduct())) {
						quantity -= changeItem.getQuantity();
						break;
					}
				}
			}
			
			if(quantity - changeDTO.getQuantityItem()[i] < 0) {
				throw new StrategyValidation(new StringBuilder("Produto com id: " + orderItem.getProduct().getId() + " não pode ser trocado"));
			}
			
			ChangeItem changeItem = new ChangeItem();
			changeItem.setProduct(orderItem.getProduct());
			changeItem.setQuantity(changeDTO.getQuantityItem()[i]);
			changeItem.setChange(change);
			stRegistration.execute(changeItem);
			change.getItems().add(changeItem);
		}
		
		StringBuilder errors = runStrategys(change, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		change.setStatusChange(StatusChange.EMTROCA);
		return changeService.save(change);
	}

	@Override
	public List<ChangeDTO> getByStatus(String status) {
		StatusChange statu = StatusChange.valueOf(status);
		
		List<ChangeDTO> changes = new ArrayList<>(); 
		for(Change change: changeService.getByStatus(statu)) {
			changes.add((ChangeDTO) new ChangeDTO().from(change));
		}
		
		return changes;
	}

	@Override
	public Change aprovedChange(Long id) {
		Change change = changeService.findById(id);
		Float total = (float) 0.0;
		for(ChangeItem item: change.getItems()) {
			total += (item.getProduct().getPrice() * item.getQuantity()); 
		}
		ChangeCoupon coupon = new ChangeCoupon();
		coupon.setClient(change.getClient());
		coupon.setValue(total);
		stRegistration.execute(coupon);
		change.setStatusChange(StatusChange.TROCAAUTORIZADA);
		changeCouponService.save(coupon);
		return changeService.save(change);
	}

	@Override
	public Change reprovedChange(Long id) {
		Change change = changeService.findById(id);
		change.setStatusChange(StatusChange.TROCAREPROVADA);
		return changeService.save(change);
	}

	@Override
	public List<ChangeDTO> getByIdClient(Long id) {
		Client client = clientService.findById(id);
		List<ChangeDTO> changes = new ArrayList<>(); 
		for(Change change: changeService.getByClient(client)) {
			changes.add((ChangeDTO) new ChangeDTO().from(change));
		}
		
		return changes;
	}

}
