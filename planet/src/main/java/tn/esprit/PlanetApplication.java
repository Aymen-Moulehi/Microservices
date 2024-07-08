package tn.esprit;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class PlanetApplication {

    private final PlanetRepository planetRepository;
    public static void main(String[] args) {
        SpringApplication.run(PlanetApplication.class, args);
    }
    @PostConstruct
    public void setupData() {
        planetRepository.saveAll(
                Arrays.asList(
                        Planet.builder().name("Mercury").mass(3.285e23).galaxyId(1).build(),
                        Planet.builder().name("Venus").mass(4.867e24).galaxyId(1).build(),
                        Planet.builder().name("Earth").mass(5.972e24).galaxyId(1).build(),
                        Planet.builder().name("Mars").mass(6.4171e23).galaxyId(1).build(),
                        Planet.builder().name("Jupiter").mass(1.898e27).galaxyId(1).build(),
                        Planet.builder().name("Planet A").mass(3.285e23).galaxyId(2).build(),
                        Planet.builder().name("Planet B").mass(4.867e24).galaxyId(2).build(),
                        Planet.builder().name("Planet C").mass(5.972e24).galaxyId(2).build(),
                        Planet.builder().name("Planet D").mass(6.4171e23).galaxyId(2).build(),
                        Planet.builder().name("Planet E").mass(1.898e27).galaxyId(2).build()
                )
        );
    }
}