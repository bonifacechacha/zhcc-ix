package tz.go.mohz.zhcc.integration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "zhil")
public class ZHILProperties {

  @NotBlank
  private String apiUrl;

  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
