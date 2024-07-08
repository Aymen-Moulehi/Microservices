package tn.esprit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {

    List<Planet> findAllByGalaxyId(int galaxyId);
}
