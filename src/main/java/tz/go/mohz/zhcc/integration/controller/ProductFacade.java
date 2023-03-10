package tz.go.mohz.zhcc.integration.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tz.go.mohz.zhcc.integration.dto.RegistryProductCreateDTO;
import tz.go.mohz.zhcc.integration.dto.ZFDAProductDTO;
import tz.go.mohz.zhcc.integration.mapper.ProductMapper;
import tz.go.mohz.zhcc.integration.service.ZHCCService;
import tz.go.mohz.zhcc.integration.service.ZHILService;

@Log
@Component
@RequiredArgsConstructor
public class ProductFacade {

  private final ZHCCService zhccService;

  private final ZHILService zhilService;

  private final ProductMapper productMapper;

  public void createRegistryProducts(List<RegistryProductCreateDTO> registryProductCreateDTOs) {

    for (RegistryProductCreateDTO registryProductCreateDTO : registryProductCreateDTOs) {
      if (ObjectUtils.isEmpty(registryProductCreateDTO.getIdentifier())) {
        registryProductCreateDTO.setIdentifier(UUID.randomUUID().toString());
      }
    }

    var payloads = productMapper.toRegistryProductCreatePayloads(registryProductCreateDTOs);
    zhccService.createProducts(payloads);
    zhilService.submitProducts(payloads);
  }

  public void createZFDAProducts(List<ZFDAProductDTO.CreateDTO> createDTOs) {
    createRegistryProducts(productMapper.toRegistryProductCreateDTOs(createDTOs));
  }

  public void updateZFDAProduct(String productId, ZFDAProductDTO.UpdateDTO updateDTO) {
    updateDTO.setSku(productId);

  }
}
