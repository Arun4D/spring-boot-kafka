package co.in.ad.study.kafka.service;

import co.in.ad.study.kafka.dto.OrderDetailsDto;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderConsumer {
    private final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    @Autowired
    private KafkaStreamsConfiguration kStreamsConfigs;

    @KafkaListener(topics = "ad-study-order-topic", groupId = "ad-event-group")
    public void consume(OrderDetailsDto orderDetailsDto) throws IOException {

        Serde<String> stringSerde = Serdes.String();
        JsonSerializer jsonSerializer = new JsonSerializer();
        jsonSerializer.setAddTypeInfo(false);

        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        jsonDeserializer.addTrustedPackages("za.co.sanlam.epsilon.entity.model");

        final Serde<OrderDetailsDto> jsonSerde = Serdes.serdeFrom(jsonSerializer, jsonDeserializer);

        final StreamsBuilder kStreamBuilder = new StreamsBuilder();
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore("ad-study-dw");
        KTable<String, String> table = kStreamBuilder.table(
                "ad-study-consumer-topic",
                Materialized.<String, String>as(storeSupplier)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(Serdes.String())
        );

        final Topology topology = kStreamBuilder.build();

        final KafkaStreams streams = new KafkaStreams(topology, kStreamsConfigs.asProperties());

        KStream<String, OrderDetailsDto> stream = kStreamBuilder.stream("ad-study-customer-topic", Consumed.with(Serdes.String(), jsonSerde));

        logger.info(String.format("#### -> Order Details message -> %s", orderDetailsDto));
        logger.info(String.format("#### -> Consumed message -> %s", orderDetailsDto));
    }
}
