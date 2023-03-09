package tz.go.mohz.zhcc.integration.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.go.mohz.zhcc.integration.dto.RegistryProductCreateDTO;
import tz.go.mohz.zhcc.integration.dto.ZFDAProductCreateDTO;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductFacade productFacade;

  @PostMapping
  public ResponseEntity onNewProduct(@RequestBody RegistryProductCreateDTO createDTO) {
    productFacade.createRegistryProducts(List.of(createDTO));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/bulk")
  public ResponseEntity onNewProducts(@RequestBody List<RegistryProductCreateDTO> createDTOs) {
    productFacade.createRegistryProducts(createDTOs);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/zfda")
  public ResponseEntity onNewZFDAProduct(@RequestBody ZFDAProductCreateDTO createDTO) {
    productFacade.createZFDAProducts(List.of(createDTO));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/zfda/bulk")
  public ResponseEntity onNewZFDAProducts(@RequestBody List<ZFDAProductCreateDTO> createDTOs) {
    productFacade.createZFDAProducts(createDTOs);
    return ResponseEntity.ok().build();
  }
}
