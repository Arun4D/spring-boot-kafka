package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.dto.OrderDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${study.kafka.topic.order}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, OrderDetailsDto> kafkaTemplate;

    public void sendMessage(OrderDetailsDto orderDetailsDto) {
        logger.info(String.format("#### -> Producing message -> %s", orderDetailsDto));
        this.kafkaTemplate.send(topic, orderDetailsDto);
    }
}
