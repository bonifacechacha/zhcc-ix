package tz.go.mohz.zhcc.integration;

import jakarta.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "zhcc")
public class ZHCCProperties {

  @NotBlank
  private String clientId;

  @NotBlank
  private String clientSecret;

  @NotBlank
  private String tokenUrl;

  @NotBlank
  private String apiUrl;

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  private List<String> requiresLocale = new LinkedList<>();
}
