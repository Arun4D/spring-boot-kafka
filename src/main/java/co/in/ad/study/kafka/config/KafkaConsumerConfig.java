package co.in.ad.study.kafka.config;

import co.in.ad.study.kafka.core.model.EventEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class
KafkaConsumerConfig {

    @Value("${study.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${study.kafka.topic.group}")
    private String groupId;


    @Bean
    public ConsumerFactory<String, EventEntity> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        JsonDeserializer<EventEntity> jsonDeserializer = new JsonDeserializer(EventEntity.class);
        jsonDeserializer.addTrustedPackages("*");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(EventEntity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventEntity> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EventEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
