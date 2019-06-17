package br.com.randomthings.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import br.com.randomthings.services.order.web.OrderServiceWeb;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;

@Service
public class OrderPaymantJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int test1 = new Double(Math.random() * 100).intValue() % 2;
		int test2 = new Double(Math.random() * 100).intValue() % 2;
		
		OrderServiceWeb service = (OrderServiceWeb) context.getJobDetail().getJobDataMap().get("SERVICE");
		
//		if(test1 == 0 && test2 == 0) {
//			service.reprovedOrder(context.getJobDetail().getJobDataMap().getLong("ID"));
//		} else {
			service.nextStep(context.getJobDetail().getJobDataMap().getLong("ID"));
//		}
		
	}

}
