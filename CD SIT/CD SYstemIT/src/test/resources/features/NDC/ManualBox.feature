@GB_ManualBox
Feature: As a user to verify the GB_Manualbox

  @GB_MANUALBOX_001 @ANDC @complete
  Scenario: The allocation should happen from the oldest inevntory for an NDC order
    Given An NDC order with a boxed sku

  @GB_MANUALBOX_002 @ANDC @complete
  Scenario: The allocation should happen from the oldest inevntory for an EDC order
    Given An EDC order with a boxed/Hanging sku

  @GB_MANUALBOX_003 @ANDC @complete
  Scenario: To check Same list id is generated for both the lines
    Given An order with two SKUs

  @GB_MANUALBOX_004 @ANDC  @complete
  Scenario: To trigger a replenishment task 
    Given A SKU with  pick face inventory less than the Resv inventory

