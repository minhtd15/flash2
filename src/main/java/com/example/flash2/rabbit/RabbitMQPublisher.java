package com.example.flash2.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void sendMessage(String message) {
    rabbitTemplate.convertAndSend("receiveQueue", message);
    System.out.println("Message sent to RabbitMQ: " + message);
  }
}

