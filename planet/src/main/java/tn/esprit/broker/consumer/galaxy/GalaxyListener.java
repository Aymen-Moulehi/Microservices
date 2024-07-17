package tn.esprit.broker.consumer.galaxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
@EnableKafka
@SuppressWarnings("unused")
@Slf4j
public class GalaxyListener {
    @KafkaListener(id = "galaxy-listener", topics = "galaxy")
    public void subscribe(String galaxyName) {
        log.info("Received Galaxy object from Kafka: {}", galaxyName);
    }
}
