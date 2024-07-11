package tn.esprit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.PlanetDto;
import tn.esprit.error.ErrorHandler;
import tn.esprit.error.ErrorEntity;
import tn.esprit.query.PlanetAddingRequest;
import tn.esprit.query.ServiceInfoResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planets")
@AllArgsConstructor
public class PlanetController {
    private final PlanetService planetService;
    private final Environment env;
    @PostMapping
    @CircuitBreaker(name = "addPlanetCircuitBreaker", fallbackMethod = "handlePlanetControllerError")
    public ResponseEntity<Planet> addPlanet(@RequestBody PlanetAddingRequest planetAddingRequest) {
        return new ResponseEntity<>(planetService.addPlanet(planetAddingRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{planetId}")
    @CircuitBreaker(name = "getPlanetCircuitBreaker", fallbackMethod = "handlePlanetControllerError")
    public ResponseEntity<PlanetDto> getPlanet(@PathVariable("planetId") int planetId) {
        return new ResponseEntity<>(planetService.getPlanetById(planetId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/galaxy/{galaxyId}")
    @CircuitBreaker(name = "getPlanetsByGalaxyIdCircuitBreaker", fallbackMethod = "handlePlanetControllerError")
    public ResponseEntity<List<Planet>> getPlanetsByGalaxyId(@PathVariable("galaxyId") int galaxyId) {
        return new ResponseEntity<>(planetService.getPlanetsByGalaxyId(galaxyId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/info")
    @CircuitBreaker(name = "getServiceInfo", fallbackMethod = "handlePlanetControllerError")
    public ResponseEntity<ServiceInfoResponse> getServiceInfo() {
        String serviceName = env.getProperty("service-name");
        String description = env.getProperty("description");
        return new ResponseEntity<>(new ServiceInfoResponse(serviceName, description), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<ErrorEntity> handlePlanetControllerError (Exception exception) {
        return ErrorHandler.handleException(exception);
    }
}
