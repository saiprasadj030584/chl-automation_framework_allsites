@Receiving
Feature: Receiving
  As a Exit DC user should be able to login
  so that I validate the  master data setup
  with Pre-receiving
   
@CI_EXIT @Receiving @TC01_To_check_if_the_batch_and_expiry_date_are_captured_in_exit_dispatcher_green_recieve
  Scenario Outline: Batch and expiry date check for green receive
    Given Data to be inserted in preadvice header,order header and UPI receipt with ASN "Released","NONRETAIL","5542","<ASN>" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory
    And Check Qty received is updated in Pre-advice line

 Examples: 
      | SKU                |  ASN        | 
      | 000000000060000801 |  0001331208 |