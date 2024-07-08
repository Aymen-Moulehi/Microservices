package tn.esprit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.error.ErrorEntity;
import tn.esprit.error.ErrorHandler;


@RestController
@RequestMapping("/api/v1/galaxies")
@AllArgsConstructor
public class GalaxyController {
    private final GalaxyService galaxyService;

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

    public ResponseEntity<ErrorEntity> handleGalaxyControllerError (Exception exception) {
        return ErrorHandler.handleException(exception);
    }
}
