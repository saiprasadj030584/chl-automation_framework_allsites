@Allocation
Feature: Allocation
  As a warehouse user
  I want to verify the Shipdock and Consignments are present
  So that the stocks are used for allocation

  @wip10
    Scenario: verify the Shipdock and Consignments in order header screen
    When I have the "9999200093" OrderId the shipdock and consignmnet should be displayed