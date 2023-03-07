package tz.go.mohz.zhcc.integration.zhccix;

import jakarta.inject.Inject;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tz.go.mohz.zhcc.integration.service.ZHCCService;

@SpringBootTest
@Log
public class ZHCCServiceIntegrationTest {

  @Inject
  private ZHCCService zhccService;

  @Test
  public void shouldRetrieveOauthToken(){
    var token = zhccService.getToken();
    Assertions.assertThat(token).containsKey("access_token");
  }

  @Test
  public void shouldFindProduct(){
    var product = zhccService.findProduct("100036").getBody();
    Assertions.assertThat(product.get("values")).isNotNull();
  }
}
