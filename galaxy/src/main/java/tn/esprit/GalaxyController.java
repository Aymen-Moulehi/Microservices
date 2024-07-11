package tn.esprit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.GalaxyDto;
import tn.esprit.error.ErrorEntity;
import tn.esprit.error.ErrorHandler;
import tn.esprit.query.GalaxyAddingRequest;
import tn.esprit.query.ServiceInfoResponse;


@RestController
@RequestMapping("/api/v1/galaxies")
@AllArgsConstructor
@RefreshScope
public class GalaxyController {
    private final GalaxyService galaxyService;
    private final Environment env;

    @PostMapping
    @CircuitBreaker(name = "addGalaxy", fallbackMethod = "handleGalaxyControllerError")
    public ResponseEntity<Galaxy> addGalaxy(@RequestBody GalaxyAddingRequest galaxyAddingRequest) {
        return new ResponseEntity<>(galaxyService.addGalaxy(galaxyAddingRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{galaxyId}")
    @CircuitBreaker(name = "getGalaxy", fallbackMethod = "handleGalaxyControllerError")
    public ResponseEntity<Galaxy> getGalaxy(@PathVariable("galaxyId") int galaxyId) {
        return new ResponseEntity<>(galaxyService.getGalaxyById(galaxyId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/isExist/{galaxyId}")
    @CircuitBreaker(name = "isExistGalaxy", fallbackMethod = "handleGalaxyControllerError")
    public ResponseEntity<Boolean> isExistGalaxy(@PathVariable("galaxyId") int galaxyId) {
        return new ResponseEntity<>(galaxyService.isExistGalaxy(galaxyId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/with-planets/{galaxyId}")
    @CircuitBreaker(name = "getGalaxyByIdWithRelatedPlanets", fallbackMethod = "handleGalaxyControllerError")
    public ResponseEntity<GalaxyDto> getGalaxyByIdWithRelatedPlanets(@PathVariable("galaxyId") int galaxyId) {
        return new ResponseEntity<>(galaxyService.getGalaxyByIdWithRelatedPlanets(galaxyId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/info")
    @CircuitBreaker(name = "getServiceInfo", fallbackMethod = "handleGalaxyControllerError")
    public ResponseEntity<ServiceInfoResponse> getServiceInfo() {
        String serviceName = env.getProperty("service-name");
        String description = env.getProperty("description");
        return new ResponseEntity<>(new ServiceInfoResponse(serviceName, description), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<ErrorEntity> handleGalaxyControllerError (Exception exception) {
        return ErrorHandler.handleException(exception);
    }
}
