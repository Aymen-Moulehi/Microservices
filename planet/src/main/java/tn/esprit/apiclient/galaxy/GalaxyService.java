package tn.esprit.apiclient.galaxy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.error.ErrorCode;
import tn.esprit.error.exception.ServerSideCustomException;

@RequiredArgsConstructor
@Service
public class GalaxyService {
    private String galaxyMicroserviceUrl;
    private final RestTemplate restTemplate;
    private final Environment env;

    @PostConstruct
    public void setupData() {
        galaxyMicroserviceUrl = env.getProperty("galaxy-microservice-url");
    }
    public Galaxy getGalaxyById(int galaxyId) {
        String targetUrl = galaxyMicroserviceUrl + "/" + galaxyId;
        Galaxy galaxy;
        try {
            galaxy = restTemplate.getForObject(targetUrl , Galaxy.class);
        } catch (Exception exception) {
            throw new ServerSideCustomException("Error while calling the Galaxy microservice", ErrorCode.CALLING_GALAXY_SERVER.getCode());
        }
        return galaxy;
    }

    public Boolean isExistGalaxy(int galaxyId) {
        String targetUrl = galaxyMicroserviceUrl + "/isExist/" + galaxyId;
        Boolean isExistGalaxy;
        try {
            isExistGalaxy = restTemplate.getForObject(targetUrl , Boolean.class);
        } catch (Exception exception) {
            throw new ServerSideCustomException("Error while calling the Galaxy microservice", ErrorCode.CALLING_GALAXY_SERVER.getCode());
        }
        return isExistGalaxy;
    }
}
