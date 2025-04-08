@Normal_Mixed_Despatch
Feature: As a user to verify the Normal Mixed Despatch

  @NDCBGODESP001 @complete
  Scenario: To check that boxed and hanging elements for the same consignment can be loaded across two different trailers for the same route

    Given An order with Auto Boxed and Auto Hanging elements with same consignment to be loaded on different trailers , DST workplace for boxed and RSH lane for hanging.

     @GB_DESPATCH_003 @complete
  Scenario: To Complete loading for a route with hanging URNs Dolly Stacks and Manual Pallets onto 1 trailer 

    Given An order with Auto Boxed,Manual pallet and Auto Hanging elements with same consignment to be loaded on same trailer , DST workplace for boxed and RSH lane for hanging.
    