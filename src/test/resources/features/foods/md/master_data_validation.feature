@master_data
Feature: Master data validation
  As a warehouse user
  I want to validate master data 
  So that I can use them for PO receiving putaway and allocation

  @complete @I005 @md
  Scenario Outline: Load the site details
    Given the address id "<addressId>"
    Then the address should have address type, name, address line1 and country details
    Then the CE warehouse type should be displayed
    Then the  site category should be displayed ,site flag should be updated as site

    Examples: 
      | addressId |
      |      0010 |

  @complete @I0035 @md
  Scenario Outline: Load the vendor details
    Given the address id "<addressId>"
    Then the address should have address type, name, address line1 and country details
    Then the CE warehouse type should be displayed
    Then site flag should be updated as vendor

    Examples: 
      | addressId |
      | F02007    |

  @complete @I016 @md
  Scenario Outline: Validate Supplier SKU table in JDA WMS dispatcher for I016
    Given the sku id "<skuId>" and supplier "<supplierId>"
    Then the supplier SKU details should be displayed

    Examples: 
      | skuId    | supplierId |
      | 20001265 | F02007     |

  @complete @I016 @md
  Scenario Outline: Validate Supplier SKU table in JDA WMS dispatcher when no records are found
    Given the sku id "<skuId>" and supplier "<supplierId>"
    Then no records should be displayed

    Examples: 
      | skuId    | supplierId |
      | 20001265 | D02007     |

  @complete @I016 @md
  Scenario Outline: Validate kit line table in JDA WMS dispatcher for BOM Article
    Given the Sku id "<SkuId>"
    Then the kit line details should be displayed

    Examples: 
      | SkuId    |
      | 20001590 |

  @complete @I016 @md
  Scenario Outline: Validate whether Article data is successfully loaded into SKU table
    Given the Sku id "<SkuId>"
    Then the SKU description,product group, EAN, UPC, allocation group, each quantity, tag merge fields should be displayed in settings1 tab
    Then the new product field should be displayed
    Then the C&E warehouse type, C&E VAT code, C&E SKU, C&E alcoholic strength fields should be displayed
    Then the expiry required should be displayed
    Then the base UOM, SAP creation status should be displayed

    Examples: 
      | SkuId    |
      | 20001249 |

  @complete @I016 @md
  Scenario Outline: Validate Pack config table in JDA WMS dispatcher for I016
    Given the pack config id "<pack config ID>"
    Then the tag volume, volume at each details should be displayed
    Then the tracking levels and ratios should be displayed
    Then the RDT tracking levels 1 and 2 should be displayed

    Examples: 
      | pack config ID |
      | 20001452O01    |
