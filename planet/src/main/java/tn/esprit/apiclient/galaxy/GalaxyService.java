package tn.esprit.apiclient.galaxy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.error.ErrorCode;
import tn.esprit.error.exception.ClientSideCustomException;
import tn.esprit.error.exception.ServerSideCustomException;

@RequiredArgsConstructor
@Service
@SuppressWarnings("unused")
public class GalaxyService {
    private String galaxyMicroserviceUrl;
    private final RestTemplate restTemplate;
    private final Environment env;

    @PostConstruct
    public void setupData() {
        galaxyMicroserviceUrl = env.getProperty("galaxy-microservice-url");
    }
    public Galaxy getGalaxyById(int galaxyId, String token) {
        String targetUrl = galaxyMicroserviceUrl + "/" + galaxyId;
        Galaxy galaxy;
        try {
            HttpEntity<String> entity = getHttpEntity(token);
            ResponseEntity<Galaxy> response = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, Galaxy.class);
            galaxy = response.getBody();
        } catch (Exception exception) {
            throw new ServerSideCustomException("Error while calling the Galaxy microservice", ErrorCode.CALLING_GALAXY_SERVER.getCode());
        }
        return galaxy;
    }

    public Boolean isExistGalaxy(int galaxyId, String token) {
        String targetUrl = galaxyMicroserviceUrl + "/isExist/" + galaxyId;
        Boolean isExistGalaxy;
        try {
            HttpEntity<String> entity = getHttpEntity(token);
            ResponseEntity<Boolean> response = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, Boolean.class);
            isExistGalaxy = response.getBody();
        } catch (Exception exception) {
            throw new ServerSideCustomException("Error while calling the Galaxy microservice", ErrorCode.CALLING_GALAXY_SERVER.getCode());
        }
        return isExistGalaxy;
    }

    private HttpEntity<String> getHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

}
