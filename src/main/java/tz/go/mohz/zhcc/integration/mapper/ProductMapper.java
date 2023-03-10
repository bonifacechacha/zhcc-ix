package tz.go.mohz.zhcc.integration.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import tz.go.mohz.zhcc.integration.ZHCCProperties;
import tz.go.mohz.zhcc.integration.dto.RegistryProductCreateDTO;
import tz.go.mohz.zhcc.integration.dto.ZFDAProductDTO;

@Validated
@Mapper(componentModel = SPRING)
public abstract class ProductMapper {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ZHCCProperties zhccProperties;

  public abstract List<RegistryProductCreateDTO> toRegistryProductCreateDTOs(List<ZFDAProductDTO.CreateDTO> zfdaProductCreateDTO);

  public RegistryProductCreateDTO toRegistryProductCreateDTO(ZFDAProductDTO.CreateDTO zfdaProductCreateDTO) {
    var registryProductCreateDTO = new RegistryProductCreateDTO();
    registryProductCreateDTO.setIdentifier(zfdaProductCreateDTO.getSku());
    registryProductCreateDTO.setFamily("MOHZ");

    Map<String, Object> attributesMap = new LinkedHashMap<>(objectMapper.convertValue(zfdaProductCreateDTO, Map.class));

    for (String attributeName : attributesMap.keySet()) {
      var attributeValue = attributesMap.get(attributeName);
      if (attributeValue == null) {
        continue;
      }

      var content = new RegistryProductCreateDTO.Value.Content(attributeValue);
      if (zhccProperties.getRequiresLocale().contains(attributeName)) {
        content.setLocale("en_US");
      }

      var value = new RegistryProductCreateDTO.Value();
      var valueName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, attributeName);
      value.setName(valueName);
      value.setContents(List.of(content));

      registryProductCreateDTO.getValues().add(value);
    }

    return registryProductCreateDTO;
  }

  public abstract List<Map<String, Object>> toRegistryProductCreatePayloads(List<RegistryProductCreateDTO> registryProductCreateDTOs);

  public Map<String, Object> toRegistryProductCreatePayload(RegistryProductCreateDTO registryProductCreateDTO) {
    var payload = new LinkedHashMap<String, Object>();

    payload.put("identifier", registryProductCreateDTO.getIdentifier());
    payload.put("family", registryProductCreateDTO.getFamily());
    payload.put("categories", registryProductCreateDTO.getCategories());
    payload.put("values", toValuesMap(registryProductCreateDTO.getValues()));

    return payload;
  }

  public Map<String, List<Map<String, Object>>> toValuesMap(List<RegistryProductCreateDTO.Value> values) {

    var valuesMap = new LinkedHashMap<String, List<Map<String, Object>>>();

    for (RegistryProductCreateDTO.Value value : values) {
      valuesMap.put(value.getName(), toContentMapList(value.getContents()));
    }

    return valuesMap;
  }

  public abstract List<Map<String, Object>> toContentMapList(List<RegistryProductCreateDTO.Value.Content> content);

  public Map<String, Object> toContentMap(RegistryProductCreateDTO.Value.Content content) {
    var contentMap = new LinkedHashMap<String, Object>();
    contentMap.put("data", content.getData());
    contentMap.put("scope", content.getScope());
    contentMap.put("locale", content.getLocale());

    return contentMap;
  }
}
