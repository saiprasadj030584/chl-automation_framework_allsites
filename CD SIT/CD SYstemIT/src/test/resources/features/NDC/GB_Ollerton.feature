@GB_Ollerton
Feature: As a user to verify the GB_Ollerton

  @GB_Ollerton_001 @ANDC @complete
  Scenario: To receive a Box Postal UPC
    Given A Boxed sku with Postal urrn which is to be received followed by dock booking

  @GB_Ollerton_002 @ANDC @complete
  Scenario: To receive a Hanging Postal UPC
    Given An Hanging sku with Postal urrn which is to be received followed by dock booking

  @GB_Ollerton_003 @ANDC @complete
  Scenario: To receive a Box URRN
    Given A Boxed sku with urrn which is to be received followed by dock booking

  @GB_Ollerton_004 @ANDC  @complete
  Scenario: To receive a Hanging URRN
    Given An Hanging sku with urrn which is to be received followed by dock booking

  @GB_Ollerton_005 @ANDC @complete
  Scenario: To Process a Hanging UPC through to Despatch
    Given A received hanging sku is to be putaway to final location and to create a transfer order and to ship the Transfer order

  @GB_Ollerton_006 @ANDC @complete
  Scenario: To Putaway a Box UPC to Sort Frame and Final Location
    Given A received Boxed sku is to be putaway to final location

  @GB_Ollerton_007 @ANDC @complete
  Scenario: To Pick and despatch a box UPC
    Given A received Boxed sku is to be Picked, marshalling to final location and to create a transfer order and to ship the Transfer orde
