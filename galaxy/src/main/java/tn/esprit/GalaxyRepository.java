package tn.esprit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends JpaRepository<Galaxy, Integer> {
}
