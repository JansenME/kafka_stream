package nl.demo.kafka.service;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static nl.demo.kafka.service.ProducerService.STREAM_TOPIC_NAME;

public class ProducerServiceTest {
    private MockProducer<String, Integer> producerMock;
    private ProducerService producerService;

    @Before
    public void setUp() {
        producerMock = new MockProducer<>(true, new StringSerializer(), new IntegerSerializer());
        producerService = new ProducerService();
    }

    @Test
    public void testProducer() {
        producerService.producer = producerMock;

        producerRecordList().forEach(record -> producerService.producer.send(record));

        List<ProducerRecord<String, Integer>> actual = producerMock.history();

        List<ProducerRecord<String, Integer>> expected = producerRecordList();

        Assert.assertEquals("Lists of producerRecords did not match", expected, actual);
    }

    @Test
    public void testCreateStream() {
        producerService.createProducer(5);
    }

    private List<ProducerRecord<String, Integer>> producerRecordList() {
        return Arrays.asList(
                new ProducerRecord<>(STREAM_TOPIC_NAME, "AccountKey1", 1),
                new ProducerRecord<>(STREAM_TOPIC_NAME, "AccountKey2", 2),
                new ProducerRecord<>(STREAM_TOPIC_NAME, "AccountKey3", 3),
                new ProducerRecord<>(STREAM_TOPIC_NAME, "AccountKey4", 4),
                new ProducerRecord<>(STREAM_TOPIC_NAME, "AccountKey5", 5));
    }
}