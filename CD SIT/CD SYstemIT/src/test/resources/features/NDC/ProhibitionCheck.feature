@Prohibition_Check
Feature: As a user to verify the Prohibition check


     @GB_DESPATCH_004 @complete
  Scenario: To Check that for NDC orders, prohibited stock is preferred, and for franchise customers non prohibited stock is preferred 

    Given Two orders one of which is franchise and other is NDC, loading is done is two different trailers followed by shipping
    