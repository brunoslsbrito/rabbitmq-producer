package com.britosw.producerapi.service;

import com.britosw.producerapi.config.PaymentAMQPConfig;
import com.britosw.producerapi.model.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMachineToRabbit() {
        try {
            Payment payment = Payment.builder().client("Bruno Brito").uc("21312").value(100.0).build();
            String json = new ObjectMapper().writeValueAsString(payment);
            rabbitTemplate.convertAndSend(PaymentAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
