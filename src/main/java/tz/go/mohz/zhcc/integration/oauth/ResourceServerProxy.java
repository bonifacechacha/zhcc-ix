package tz.go.mohz.zhcc.integration.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tz.go.mohz.zhcc.integration.ZHCCProperties;
import tz.go.mohz.zhcc.integration.service.ZHCCService;

@Component
@RequiredArgsConstructor
public class ResourceServerProxy {

  public static final String AUTHORIZATION = "Authorization";

  private final ZHCCService ZHCCService;

  private final RestTemplate restTemplate;

  private final ZHCCProperties configurationProperties;

  public String callDemo() {
    var token = ZHCCService.getToken();

    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, "Bearer " + token.get("access_token"));
    HttpEntity<Void> request = new HttpEntity<>(headers);

    var response = restTemplate.exchange(
        configurationProperties.getApiUrl(), HttpMethod.GET, request, String.class);

    return response.getBody();
  }
}
