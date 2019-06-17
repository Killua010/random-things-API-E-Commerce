package br.com.randomthings.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;

@Service
public class ShoppingCartJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ShoppingCartWebService shoppingCartWeb = (ShoppingCartWebService) context.getJobDetail().getJobDataMap().get("SERVICE");
		shoppingCartWeb.cleanShoppingCart(context.getJobDetail().getJobDataMap().getLong("ID"));
	}

}
