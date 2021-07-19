package com.stlc.poc.event;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stlc.poc.component.KafkaConsumer;
import com.stlc.poc.model.MessageResponse;
import com.stlc.poc.model.MessageRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageConsumer implements KafkaConsumer<MessageRequest>{
	
	private SimpMessagingTemplate template;
	
	public MessageConsumer(SimpMessagingTemplate template) {
		this.template = template;
	}


	@Override
	public void consume(MessageRequest payload) throws JsonMappingException, JsonProcessingException {
		
		 log.info("Message received: {}", payload);
		 this.template.convertAndSend("/topic/messages", new MessageResponse("Received message: " + payload.getName()));
		 
	}

}
