
@Replen_Pick
Feature: As a user to verify the Replen_Pick

@Replen_Pick @complete
  Scenario: To Check that when an order is released that requires more stock that the current SCS_MIN stock holding that a replenishment move is created from BCS to SCS (via PPP if not pick ready)


    Given An order with quantity greater than the SCS stock and to replenish SCS stock from BCS and the excess stock in ISC should move to SCS or to ETB if empty 

