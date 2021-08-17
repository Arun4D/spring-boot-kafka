package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.assemble.OrderDetailsAssembler;
import co.in.ad.study.kafka.core.model.EventEntity;
import co.in.ad.study.kafka.dto.OrderDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private OrderDetailsAssembler orderDetailsAssembler;

    @KafkaListener(topics = "ad-study-producer-topic", groupId = "ad-event-group")
    public void consume(EventEntity eventEntity) throws IOException {
        /*ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(eventEntity.getData());
        OrderDetailsDomain orderDetails = om.readValue(s, OrderDetailsDomain.class);*/

        OrderDetailsDto orderDetailsDto = orderDetailsAssembler.assembleOrderDetailsDto(eventEntity);
        logger.info(String.format("#### -> Order Details message -> %s", orderDetailsDto));

        logger.info(String.format("#### -> Consumed message -> %s", eventEntity));
    }
}
