package com.fiap.lanchonete.dataprovider.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {

    private String title;
    private List<String> items;
    private String orderIdentifier;
    private BigDecimal totalAmount;
    private String paymentStatus;
}
