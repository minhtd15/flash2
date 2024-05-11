package com.example.flash2.rabbit;

import com.example.flash2.dto.YearlyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

  @Autowired
  private AmqpTemplate rabbitTemplate;
  @Autowired
  private ObjectMapper objectMapper;

//  public void sendMessage(String message) {
//    rabbitTemplate.convertAndSend("receiveQueue", message);
//    System.out.println("Message sent to RabbitMQ: " + message);
//  }

  public void sendMessage(YearlyResponse yearlyResponse, String queueName) throws Exception {
    try {
      String json = objectMapper.writeValueAsString(yearlyResponse);
      // Gửi chuỗi JSON thông qua RabbitMQ
      rabbitTemplate.convertAndSend("queue", json);
      System.out.println("Message sent to RabbitMQ: " + queueName);
    } catch (Exception e) {
      // Log the error
      System.err.println("Error sending message to RabbitMQ: " + e.getMessage());
      // You can throw a custom error or rethrow the exception
      throw new Exception("Failed to send message to RabbitMQ", e);
    }
  }
}

