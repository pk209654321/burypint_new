package com.sscf.burypoint.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sscf.burypoint.dto.BuryPointDto;

@Component
public class BuryPointSend {
	@Autowired
	private AmqpTemplate template;
	
	public void sendMsg(BuryPointDto dto,String que) {
		
		template.convertAndSend(que,dto);
	}
}
