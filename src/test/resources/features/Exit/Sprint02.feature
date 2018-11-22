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
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory
    And Check Qty received is updated in Pre-advice line
    And Check the Orderline must be allocated

    
    @inProgress @TC04_Validate_Compliance_check_tDept_is_null_or_invalid
  Scenario: To validate Compliance Check - T-Dept is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, Allocate and Pick for the Red lock code
  	
    
    @inProgress @TC05_Validate_Compliance_check_Stroke_category_is_null_or_invalid
  Scenario: To validate Compliance Check - Stroke Category is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, Allocate and Pick for the Red lock code
    
  
  @inProgress @TC06_Validate_Compliance_check_commodity_code_is_null_or_invalid
  Scenario: To validate Compliance Check - Commodity Code is NULL or invalid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, Allocate and Pick for the Red lock code
    
    

