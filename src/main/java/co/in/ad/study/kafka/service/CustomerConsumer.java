package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerConsumer {
    private final Logger logger = LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(topics = "ad-study-customer-topic", groupId = "ad-event-group")
    public void consume(CustomerDto customerDto) throws IOException {

        logger.info(String.format("#### -> Customer Details message -> %s", customerDto));
        logger.info(String.format("#### -> Consumed message -> %s", customerDto));
    }
}
