package co.in.ad.study.kafka.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailsDto implements Serializable {
    private Long orderId;
    private Long itemId;
    private Long customerId;

}
