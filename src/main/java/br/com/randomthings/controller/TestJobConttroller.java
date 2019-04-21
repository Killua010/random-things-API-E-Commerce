package br.com.randomthings.controller;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.job.TestingJob;

@RestController
@RequestMapping("/test")
public class TestJobConttroller {
	private Scheduler scheduler;
	
	@PostConstruct
	public void init() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
	}

	@RequestMapping(value = "/start/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> test(@PathVariable("id") String id) throws SchedulerException{
		JobDetail detail = JobBuilder.newJob(TestingJob.class).withIdentity(id).build();
		detail.getJobDataMap().put("ID", id);
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setName(id);
		Date now = new Date();
		now.setTime(now.getTime() + (1000 * 5));
		trigger.setStartTime(now);
//		trigger.setRepeatInterval(3000);
		trigger.setRepeatCount(0);
		trigger.afterPropertiesSet();
		JobKey jobKey = new JobKey(id);
		if(scheduler.checkExists(jobKey)) {
			scheduler.deleteJob(jobKey);
		}
		scheduler.scheduleJob(detail, trigger.getObject());
		scheduler.start();
		
		return ResponseEntity.ok("hi");
	}
	
	@RequestMapping(value = "/stop/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> test2(@PathVariable("id") String id) {
		try {
			JobKey jobKey = new JobKey(id);
			System.err.println(scheduler.deleteJob(jobKey));

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok("hello");
	}
	
}
