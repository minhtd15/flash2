package com.example.flash2.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue queue() {
    return new Queue("queue");
  }
}

