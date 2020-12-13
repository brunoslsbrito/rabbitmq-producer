package com.britosw.producerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@SuperBuilder
public class Payment {
    private UUID uuid;
    private Long id;
    private Double value;
    private String client;
    private String uc;
}
