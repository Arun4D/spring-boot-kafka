package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.core.model.EventEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${study.kafka.topic.producer}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, EventEntity> kafkaTemplate;

    public void sendMessage(EventEntity eventEntity) {
        logger.info(String.format("#### -> Producing message -> %s", eventEntity));
        this.kafkaTemplate.send(topic, eventEntity);
    }
}
