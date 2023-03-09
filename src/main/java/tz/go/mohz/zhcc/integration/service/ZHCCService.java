package tz.go.mohz.zhcc.integration.service;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tz.go.mohz.zhcc.integration.SecurityUtils;
import tz.go.mohz.zhcc.integration.ZHCCProperties;

@Log
@Component
@RequiredArgsConstructor
public class ZHCCService {

  private final ZHCCProperties configurationProperties;

  private final RestTemplate restTemplate;

  public ResponseEntity<JsonNode> findProduct(String productCode) {

    var headers = prepareHttpHeaders(getAccessToken());
    var request = new HttpEntity<>(headers);

    var response = restTemplate.exchange(
        configurationProperties.getApiUrl() + "/products/" + productCode,
        HttpMethod.GET,
        request,
        JsonNode.class
    );

    return response;
  }

  public void createProducts(List<Map<String, Object>> products) {
    var token = getAccessToken();
    for (Map<String, Object> product : products) {
      createProduct(product, token);
    }
  }

  private ResponseEntity<String> createProduct(Map<String, Object> payload, String token) {
    log.info("** Creating : "+payload+" **");

    var headers = prepareHttpHeaders(token);
    var request = new HttpEntity<>(payload, headers);

    var response = restTemplate.exchange(
        configurationProperties.getApiUrl() + "/products",
        HttpMethod.POST,
        request,
        String.class
    );

    return response;
  }

  private HttpHeaders prepareHttpHeaders(String token) {
    var headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    return headers;
  }

  public Map getToken() {
    var authHeaders = SecurityUtils.createBasicAuthorizationHeaders(
        configurationProperties.getClientId(),
        configurationProperties.getClientSecret()
    );

    var authBody = Map.of(
        "grant_type", "password",
        "username", configurationProperties.getUsername(),
        "password", configurationProperties.getPassword()
    );

    var request = new HttpEntity<>(authBody, authHeaders);
    var response = restTemplate.exchange(
        configurationProperties.getTokenUrl(),
        HttpMethod.POST,
        request,
        Map.class
    );

    return response.getBody();
  }

  public String getAccessToken() {
    return (String) getToken().get("access_token");
  }

}
