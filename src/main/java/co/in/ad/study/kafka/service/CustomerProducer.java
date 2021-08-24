package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private static final Logger logger = LoggerFactory.getLogger(CustomerProducer.class);

    @Value("${study.kafka.topic.customer}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, CustomerDto> customerKafkaTemplate;

    public void sendMessage(CustomerDto customerDto) {
        logger.info(String.format("#### -> Producing message -> %s", customerDto));
        this.customerKafkaTemplate.send(topic, customerDto);
    }
}
