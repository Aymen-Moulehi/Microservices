package tn.esprit.dto;

import tn.esprit.apiclient.planet.Planet;

import java.util.List;

public record GalaxyDto (int id, String name, List<Planet> planets){
}