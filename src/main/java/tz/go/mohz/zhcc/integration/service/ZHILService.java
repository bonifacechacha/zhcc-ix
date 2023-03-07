package tz.go.mohz.zhcc.integration.service;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tz.go.mohz.zhcc.integration.SecurityUtils;
import tz.go.mohz.zhcc.integration.ZHILProperties;

@Component
@RequiredArgsConstructor
public class ZHILService {

  private final ZHILProperties configurationProperties;

  private final RestTemplate restTemplate;

  public ResponseEntity<JsonNode> submitProducts(JsonNode products) {
    var headers = SecurityUtils.createBasicAuthorizationHeaders(
        configurationProperties.getUsername(),
        configurationProperties.getPassword()
    );
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    var request = new HttpEntity<>(products, headers);

    var response = restTemplate.exchange(
        configurationProperties.getApiUrl(),
        HttpMethod.POST,
        request,
        JsonNode.class
    );

    return response;
  }
}
