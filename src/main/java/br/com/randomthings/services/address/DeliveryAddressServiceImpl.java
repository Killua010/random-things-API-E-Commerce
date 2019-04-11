package br.com.randomthings.services.address;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.DeliveryAddressRepository;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService{

	@Autowired
	DeliveryAddressRepository deliveryAddressRepostory;
	
	@Override
	public DeliveryAddress findById(Long id) {
		return deliveryAddressRepostory.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + DeliveryAddress.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public DeliveryAddress save(DeliveryAddress domain) {
		domain = deliveryAddressRepostory.save(domain);
		return domain;
	}

	@Override
	public void delete(Long id) {
		findById(id);
		deliveryAddressRepostory.deleteById(id);
	}

	@Override
	public List<DeliveryAddress> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
