package com.britosw.producerapi.controller;

import com.britosw.producerapi.model.Payment;
import com.britosw.producerapi.model.PaymentDTO;
import com.britosw.producerapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity pay(@RequestBody @Valid PaymentDTO payload) {
        try {
            this.service.sendMachineToRabbit(payload);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
