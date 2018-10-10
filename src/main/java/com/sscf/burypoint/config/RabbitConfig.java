package com.sscf.burypoint.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sscf.burypoint.constant.ConstantInter;
import com.sscf.burypoint.constant.ConstantsUtil;
import com.sscf.burypoint.controller.BaseController;

@Configuration
public class RabbitConfig {
	/*
	 * //直连交换机
	 * 
	 * @Bean public DirectExchange defaultExchange() { return new
	 * DirectExchange("user"); }
	 */
	@Bean
	public Queue queue() {
		return new Queue(ConstantInter.QUEUE_BURY_POINT);
	}

}
