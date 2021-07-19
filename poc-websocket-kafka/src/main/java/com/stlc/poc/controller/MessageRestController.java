package com.stlc.poc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stlc.poc.model.MessageRequest;
import com.stlc.poc.model.MessageResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MessageRestController {

	private SimpMessagingTemplate template;

	public MessageRestController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@PostMapping("/messages")

	public ResponseEntity<List<MessageResponse>> addList(@RequestBody final List<MessageRequest> messages) {

		log.info("Entry addList messages: {}", messages);

		List<MessageResponse> list = messages.stream().map(m -> new MessageResponse("Received message: " + m.getName()))
				.collect(Collectors.toList());
		list.stream().forEach(m -> this.template.convertAndSend("/topic/messages", m));

		return ResponseEntity.status(HttpStatus.CREATED).body(list);

	}

	@PostMapping("/message")

	public ResponseEntity<MessageResponse> add(@RequestBody MessageRequest message) {
		log.info("Entry add messages: {}", message);

		this.template.convertAndSend("/topic/messages", new MessageResponse("Received message: " + message.getName()));
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new MessageResponse("Received message: " + message.getName()));

	}

}
