@XDock
Feature: XDock
  As a warehouse user
  I want to perform Xdock of stocks

  @unique_boxed_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_upi_header_and_line @xDock @boxed
  Scenario: xdock
    Given the "Boxed" PO and UPI and ASN and order should be in Released status
   And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
    And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
   When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed

    
@unique_hanging_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_upi_header_and_line @xDock @Hanging
  Scenario: xdock
    Given the "Hanging" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
     
     @unique_boxed_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_multiple_upi_header_and_line @xDock @Hanging
  Scenario: xdock
    Given the "Hanging" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
     
@unique_hanging_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_multiple_upi_header_and_line @xDock @boxed
  Scenario: xdock
    Given the "Boxed" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
    
    @unique_flatpack_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_upi_header_and_line @xDock @Flatpack
  Scenario: xdock
    Given the "Flatpack" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
    
    @unique_flatpack_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_multiple_upi_header_and_line @xDock @Flatpack
  Scenario: xdock
    Given the "Flatpack" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed

     @unique_goh_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_multiple_upi_header_and_line @xDock @GOH
  Scenario: xdock
    Given the "GOH" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
    
     @unique_goh_inbound_receiving_x_dock_receiving_receving_using_asn_order_header_and_line_upi_header_and_line @xDock @GOH
  Scenario: xdock
    Given the "GOH" PO and UPI and ASN and order should be in Released status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed
    