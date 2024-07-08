package tn.esprit.apiclient.planet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("PLANET")
public interface PlanetClient {
    @GetMapping("/api/v1/planets/galaxy/{id}")
    ResponseEntity<List<Planet>> getPlanetsByGalaxyId(@PathVariable("id") int galaxyId);
}
