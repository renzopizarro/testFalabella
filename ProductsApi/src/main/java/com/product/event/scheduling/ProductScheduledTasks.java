package com.product.event.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.product.event.scheduling.service.SchedulingService;

@Component
public class ProductScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ProductScheduledTasks.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
	@Autowired
	private SchedulingService schedulingService;

//	@Scheduled(fixedRate = 60000, initialDelay = 5000)
	@Scheduled(cron = "59 59 11 * * ?") 
	public void reportCurrentTime() {
		log.info("The timer has began {}", dateFormat.format(new Date()));
		schedulingService.manageProduct();
	}
}