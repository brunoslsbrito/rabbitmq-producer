package com.britosw.producerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @NotNull
    private Double value;
    @NotNull
    private String client;
    @NotNull
    private String uc;
}
