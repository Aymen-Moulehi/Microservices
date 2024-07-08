package tn.esprit;

import tn.esprit.apiclient.galaxy.Galaxy;
import tn.esprit.error.ErrorEntity;

public record PlanetDto(int id, String name, double mass, Galaxy galaxy) {
}
