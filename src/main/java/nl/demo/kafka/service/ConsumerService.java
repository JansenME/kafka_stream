package nl.demo.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static nl.demo.kafka.service.ProducerService.STREAM_TOPIC_NAME;

@Service
@Slf4j
public class ConsumerService {
    public void consumeStream() {
        Properties properties = createProperties();

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(STREAM_TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, Integer> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record -> log.info("{}. {} - {}", record.offset(), record.key(), record.value()));
        }
    }

    private Properties createProperties() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "demo");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");

        return properties;
    }
}
