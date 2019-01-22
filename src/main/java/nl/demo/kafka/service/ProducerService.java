package nl.demo.kafka.service;

import nl.demo.kafka.AccountGenerator;
import nl.demo.kafka.model.Account;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.stream.IntStream;

@Service
public class ProducerService {
    static final String STREAM_TOPIC_NAME = "Account2";
    Producer<String, Integer> producer;

    public void createProducer(int numberOfAccounts) {
        Properties properties = createProperties();

        producer = new KafkaProducer<>(properties);

        IntStream.range( 0 , numberOfAccounts)
                .parallel()
                .forEach( element -> {
                    Account account = AccountGenerator.generateAccount();

                    producer.send(new ProducerRecord<>(STREAM_TOPIC_NAME, account.createAccountKey(), account.getBalance()));

                });

        producer.close();
    }

    private Properties createProperties() {
        Properties properties = new Properties();

        //Host:port for the Kafka stream
        properties.put("bootstrap.servers", "localhost:9092");

        //If the request fails, the producer will retry one more time
        properties.put("retries", 1);

        //Serializers for the producer key-value
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        return properties;
    }

}
