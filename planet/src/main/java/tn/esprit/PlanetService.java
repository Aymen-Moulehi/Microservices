package tn.esprit;

import org.springframework.stereotype.Service;
import tn.esprit.apiclient.galaxy.Galaxy;
import tn.esprit.apiclient.galaxy.GalaxyService;
import tn.esprit.error.ErrorCode;
import tn.esprit.error.exception.ClientSideCustomException;

import java.util.List;
import java.util.Optional;

@Service
public record PlanetService(PlanetRepository planetRepository, GalaxyService galaxyService) {
    public Planet addPlanet(PlanetAddingRequest planetAddingRequest) {
        if (!galaxyService().isExistGalaxy(planetAddingRequest.galaxyId())) {
            throw new ClientSideCustomException(
                    "Galaxy with id " + planetAddingRequest.galaxyId() + " not found",
                    ErrorCode.GALAXY_NOT_FOUND.getCode()
            );
        }
        Planet planet = Planet.builder()
                .name(planetAddingRequest.name())
                .mass(planetAddingRequest.mass())
                .galaxyId(planetAddingRequest.galaxyId())
                .build();
        return planetRepository.save(planet);
    }

    public PlanetDto getPlanetById(int planetId) {
        Optional<Planet> planetOptional = planetRepository
                .findById(planetId);
        if (planetOptional.isEmpty()) {
            throw new ClientSideCustomException(
                    "Planet with id " + planetId + " not found",
                    ErrorCode.PLANET_NOT_FOUND.getCode()
            );
        }
        Planet planet = planetOptional.get();
        Galaxy galaxy = galaxyService.getGalaxyById(planet.getGalaxyId());
        return new PlanetDto(planetId, planet.getName(), planet.getMass(), galaxy);
    }

    public List<Planet> getPlanetsByGalaxyId(int galaxyId) {
        return planetRepository.findAllByGalaxyId(galaxyId);
    }
}
