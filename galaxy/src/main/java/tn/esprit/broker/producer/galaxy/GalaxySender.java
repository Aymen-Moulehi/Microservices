package tn.esprit.broker.producer.galaxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableKafka
@SuppressWarnings("unused")
@Slf4j
public class GalaxySender {
    private final KafkaTemplate<Integer, String> template;

    public GalaxySender(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void publish(String galaxyName, int galaxyId) {
        log.info("Galaxy object with id {} sent to Kafka topic", galaxyId);
        this.template.send("galaxy", galaxyId, galaxyName);
    }
}
