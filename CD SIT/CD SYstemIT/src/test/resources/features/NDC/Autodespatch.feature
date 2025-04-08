@GB_Autodespatch
Feature: As a user to verify the GB_Autodespatch

  @AUTOBOX_DESPATCH_01 @ANDC @complete
  Scenario: To check the multiple orders have same consignment and shipped on a same trailer
    Given Two NDC orders with same route/consignment

  @BOXHANG_DESPATCH_01 @ANDC @complete
  Scenario: To check Boxed and Hanging SKUs are shipped on two different trailers/vehicles
    Given one NDC order with Boxed Sku and Hanging SKU