package tz.go.mohz.zhcc.integration.dto;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegistryProductCreateDTO {

  private String identifier;

  private boolean enabled;

  private String family;

  private List<String> categories = new LinkedList<>();

  private List<Value> values = new LinkedList<>();

  @Setter
  @Getter
  @NoArgsConstructor
  public static class Value {

    private String name;

    private List<Content> contents = new LinkedList<>();

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Content {

      public Content(Object data) {
        this.data = data;
      }

      private String locale;

      private String scope;

      private Object data;
    }
  }

}
