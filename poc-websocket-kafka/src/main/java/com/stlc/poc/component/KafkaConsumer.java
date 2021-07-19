package com.stlc.poc.component;

import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface KafkaConsumer<T> {
    /**
     * Consumes a payload message from a specific topic
     * @param payload
     */
    @KafkaListener(topics = "${spring.kafka.topic.input}", groupId = "${spring.kafka.topic.input}")
    void consume(T payload) throws JsonMappingException, JsonProcessingException;
}
