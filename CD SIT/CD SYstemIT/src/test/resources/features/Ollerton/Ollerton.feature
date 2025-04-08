@Ollerton
Feature: As a user to verify the GB_Ollerton cases

  @Ollerton_Hanging_Dispatch @SanityCheck @complete
  Scenario: To Process a Hanging UPC through to Despatch
    Given A received hanging data sku is to be putaway to final location and to create a transfer order and to despatch the Transfer order through donington receiving
