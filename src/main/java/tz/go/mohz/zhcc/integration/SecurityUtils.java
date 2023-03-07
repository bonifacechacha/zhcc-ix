package tz.go.mohz.zhcc.integration;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class SecurityUtils {

  public static HttpHeaders createBasicAuthorizationHeaders(String username, String password) {
    return new HttpHeaders() {{
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.encodeBase64(
          auth.getBytes(Charset.forName("US-ASCII"))
      );
      String authHeader = "Basic " + new String(encodedAuth);
      set("Authorization", authHeader);
    }};
  }
}
