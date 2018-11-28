@Sprint02
Feature: Receiving
  As a Exit DC user should be able to login
   so that I validate the  master data setup
   with Pre-receiving

  @Receiving @TC01_Batch_and_Expiry_Date_Check
  Scenario: To Check if the Batch and Expiry date are captured in EXIT dispatcher
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542"
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

  @Receiving @TC02_Client_ID_Auto_populated
  Scenario: To verify GS128 - Screen - Default Client Auto populated
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And Verify scan URN screen displayed

  @Receiving @TC03_Compliance_Check
  Scenario: Compliance Check - Happy path - All the required fields are valid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And I navigate to Order header screen to verify the status in Ready to Load
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory
    And Check Qty received is updated in Pre-advice line
    And Check the Orderline must be allocated

  @Receiving @TC04_Validate_Compliance_check_tDept_is_null_or_invalid
  Scenario: To validate Compliance Check - T-Dept is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

  @Receiving @TC05_Validate_Compliance_check_Stroke_category_is_null_or_invalid
  Scenario: To validate Compliance Check - Stroke Category is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the stroke category to make the stock as RED stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then stroke category is validated as NULL
				
  @Receiving @TC06_Validate_Compliance_check_commodity_code_is_null_or_invalid
  Scenario: To validate Compliance Check - Commodity Code is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the commodity Code to make the stock as RED stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then commodity Code is validated as NULL

  @Receiving @TC07_Validate_Compliance_check_check_weight_is_null_or_invalid
  Scenario: To validate Compliance Check - Weight is NULL or less than 0.00 and = 999
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then check weight is validated as null

  @Receiving @TC08_Validate_Compliance_supplier_declaration_validity_is_null_or_in_the_past
  Scenario: To validate Compliance Check - Supplier Declaration Validity is NULL or in the past
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the supplier declaration validity to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then Supplier Declaration is validated to be null or in past

  @Receiving @TC09_Validate_Compliance_supplier_record_does_not_exist
  Scenario: To validate Compliance Check - Supplier Record does not exist
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then supplier record does not exist
    
    
    @Receiving @TC10_Direct_receiving_Happy_path_Non_Trusted_Boxed_NonProhibited_inventory
    Scenario: To validate direct receiving happy path non-trusted-non-prohibited inventory
    
    
    @Receiving @TC031_Location_verification
  	Scenario Outline: Find the Location ZONE setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-LocationZone & Click
    And Click on Query
    And click execute
    Then Verify the LocationZone "<LocationZone>" displayed

    Examples: 
      | LocationZone |
      | BLACKB       |
      | REDB         |
      
     @Receiving @TC032_Verify_GS1_Receiving_screen
     Scenario: To verify GS1 Receiving screen is displayed
     Given I login as warehouse user in putty
     Then I select user directed option in main menu
     And I select Receiving menu