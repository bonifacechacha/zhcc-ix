package tz.go.mohz.zhcc.integration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ZFDAProductCreateDTO {

  private String sku;

  private String atcCode;

  private String baseUom;

  private String dosageForm;

  private String genericName;

  private String gpcCode;

  private String manufacturerLocation;

  private String manufacturerName;

  private String productDescription;

  private String productForm;

  private String routeOfAdministration;

  private String shelfLifeAfterDilution;

  private String shelfLifeAfterFirstOpening;

  private String shelfLifeBeforeOpening;

  private Integer shelfLifeDays;

  private String strength;

  private String unspscCode;

  private String zfdaCertificateNumber;

  private String zfdaIdentifier;

}
