package com.spring.training.conc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class ConcConfig {

	Logger logger = LoggerFactory.getLogger(ConcConfig.class);

	@Bean
	public MyAsyncBean asyncBean() {
		return new MyAsyncBean();
	}

	@Scheduled(fixedRate = 2_000)
	public void scheduleTest() {
		try {
			Thread.sleep(3_000);
		} catch (Exception e) {
		}
		this.logger.info("Test schedule");
	}
}
