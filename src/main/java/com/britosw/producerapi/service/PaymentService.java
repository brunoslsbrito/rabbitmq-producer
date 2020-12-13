package com.britosw.producerapi.service;

import com.britosw.producerapi.config.PaymentAMQPConfig;
import com.britosw.producerapi.exception.ApplicationException;
import com.britosw.producerapi.message.Messages;
import com.britosw.producerapi.model.Payment;
import com.britosw.producerapi.model.PaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMachineToRabbit(PaymentDTO payload) {
        try {
            var json = new ObjectMapper().writeValueAsString(payload);
            rabbitTemplate.convertAndSend(PaymentAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(String
                    .format(Messages.MALFORMED_JSON_REQUEST, Payment.class.getSimpleName()));
        }
    }

}
