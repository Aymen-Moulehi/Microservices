package tn.esprit;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients
public class GalaxyApplication {
    private final GalaxyRepository galaxyRepository;
    public static void main(String[] args) {
        SpringApplication.run(GalaxyApplication.class, args);
    }

    @PostConstruct
    public void setupData() {
        galaxyRepository.saveAll(
                Arrays.asList(
                        Galaxy.builder().name("Milky Way").build(),
                        Galaxy.builder().name("Andromeda").build()
                )
        );
    }
}