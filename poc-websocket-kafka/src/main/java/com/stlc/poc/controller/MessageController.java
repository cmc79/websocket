package com.stlc.poc.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.stlc.poc.model.MessageRequest;
import com.stlc.poc.model.MessageResponse;

@Controller
public class MessageController {


	@MessageMapping("/message")
	@SendTo("/topic/messages")
	public MessageResponse message(MessageRequest message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new MessageResponse("Received message: " + message.getName());
	}
	
}
