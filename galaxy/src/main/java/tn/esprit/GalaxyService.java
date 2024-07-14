package tn.esprit;

import org.springframework.stereotype.Service;
import tn.esprit.apiclient.planet.Planet;
import tn.esprit.apiclient.planet.PlanetClient;
import tn.esprit.dto.GalaxyDto;
import tn.esprit.error.ErrorCode;
import tn.esprit.error.exception.ClientSideCustomException;
import tn.esprit.query.GalaxyAddingRequest;

import java.util.List;
import java.util.Optional;

@Service
public record GalaxyService(GalaxyRepository galaxyRepository, PlanetClient planetClient) {
    public Galaxy addGalaxy(GalaxyAddingRequest galaxyAddingRequest) {
        if (galaxyAddingRequest.name() == null) {
            throw new ClientSideCustomException(
                    "Galaxy name should not be null",
                    ErrorCode.GALAXY_NULL_NAME.getCode()
            );
        }
        Galaxy galaxy = Galaxy.builder()
                .name(galaxyAddingRequest.name())
                .build();
        return galaxyRepository.save(galaxy);

    }

    public Galaxy getGalaxyById(int galaxyId) {
        Optional<Galaxy> galaxyOptional = galaxyRepository
                .findById(galaxyId);
        if (galaxyOptional.isEmpty()) {
            throw new ClientSideCustomException(
                    "Galaxy with id " + galaxyId + " not found",
                    ErrorCode.GALAXY_NOT_FOUND.getCode()
            );
        }
        return galaxyOptional.get();
    }

    public GalaxyDto getGalaxyByIdWithRelatedPlanets(int galaxyId, String token) {
        Galaxy galaxy = getGalaxyById(galaxyId);
        List<Planet> planets;
        try {
            planets = planetClient.getPlanetsByGalaxyId(galaxyId, token).getBody();
        } catch (Exception e) {
            throw new ClientSideCustomException(
                    "Error while calling the Planet microservice",
                    ErrorCode.CALLING_PLANET_SERVER.getCode()
            );
        }
        return new GalaxyDto(galaxyId, galaxy.getName(), planets);
    }

    public Boolean isExistGalaxy(int galaxyId) {
        return galaxyRepository.findById(galaxyId).isPresent();
    }

}
