Feature: QR Labelling
  This Feature is to enable various scenarios
  from which a user can chose to perform tests 
  related to QR labelling 
  
#QR_Rec_TC01_Load_Data_For_crossdock_receiving           - This scenario Load data and receive only one URN with One UPC Commented From "I Login"	because of the time lag not sufficient between loading and receiving. Un comment if need be#
#QR_Rec_TC02_Load_Data_For_crossdock_receiving_for_Multi - This scenario Load data for multi upcs and multi orders  Will load to sku_data and load into Webservices log table#
#QR_Rec_TC03_Read_Logfile_and_Receive										 - This will read the crossdock.log file and receive the URNs#
#QR_Rec_TC04_Input_Pallet_And_URN_to_receive						 - Give a pallet ID to receive.                              #
#QR_Marshal_TC05_Input_Marshal_The_Pallet_to_QRlane			 - This scenario will Marshal the given pallet. Input Pallet required#
#QR_Marshal_TC06_Input_Marshal_The_Pallet_to_QRlane_Bulk - This Scenario will Marshal al the pallets which are destined to (to_loc_id in move task) to QRLANE#
#QR_Update_QRId_TC07_Update_QRID_Auto_for_Added_records  - This Scenario will update Unique QRIDs for a given Pallet and urn#  
#QR_Print_QR_TC08_Print_QR_label												 - This Scenario will print the QR label for the given urn however exception scenarios needed to be handled in the code#
#QR_Repack_TC09_Repack_rejectedurn_with_given_pallet		 - This Scenario will Repack the reject QR to an existing pallet#
#QR_Repack_TC10_Repack_rejectedurn_with_new_Pallet		 	 - This Scenario will Repack the reject QR to a new Pallet#
#QR_Repack_TC11_Consignment_linking_procedure		  			 - This scenario is to automate consignment linking Procedure#

  
  @QR_Rec_TC01_Load_Data_For_crossdock_receiving
 Scenario: Create Data for Crossdock receiving
    Given load data using datatable
      | SOURCE_ID | HUB_ID | CUSTOMER_ID | SKU_ID             | UPC    | SUPPLIER_ID| LINE_ID | QTY_DUE | ODN | URN_ADVICE | STO | ASN_ID | MASTER_URN |
      |      7401 |    5542|        5571 | 000000060194452002 |02058245|      M00133|      10 |      10 |   1 |          1 |   1 |      1 | null       |
      
    And Data loaded into Webservices log for crossdock
    And I Update order line on userdef fields
    #And I login as warehouse user in putty
    #And I select user directed option in main menu
    #And I select Receiving menu
    #And I enter URN and Pallet id
    #And I Validate data from UPI and OrderLine
    
    
   @QR_Rec_TC02_Load_Data_For_crossdock_receiving_for_Multi
  Scenario: Load sku data for multi entries
    Given Load multiple SKU data records
    | SOURCE_ID | HUB_ID | CUSTOMER_ID | SKU_ID             | UPC    | SUPPLIER_ID| LINE_ID | QTY_DUE | ODN | URN_ADVICE | STO | ASN_ID | MASTER_URN |
    |      7401 |    5542|        5571 | 000000060194452002 |02058245|      M00133|      10 |      10 |   1 |          1 |   1 |      1 | null       |
    |      7401 |    5542|        5571 | 000000060194452003 |02068978|      M00133|      20 |      10 |   1 |          1 |   1 |      1 | null       |
    And Multi Data loaded into Webservices log for crossdock
    And I Update order line on userdef fields for Multi
    
   
   
   @QR_Rec_TC03_Read_Logfile_and_Receive   
  Scenario: Read Data for Crossdock receiving
    Given Read Pallet Id Data from crossdock log and receive
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I read and enter URN and Pallet id
    And I Validate data from UPI and OrderLine 
    
       
      
   @QR_Rec_TC04_Input_Pallet_And_URN_to_receive
  Scenario: Input pallet to Receive
   Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Pallet id with "55719509990730013307299965500210"
    And I Validate data from UPI and OrderLine  
    
     
   @QR_Marshal_TC05_Input_Marshal_The_Pallet_to_QRlane
 Scenario: Marshal and Load
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Marshal Menu
    And I enter Pallet from Input to proceed Marshalling to QRLANE with "P1099170"    
    
  
  	@QR_Marshal_TC06_Input_Marshal_The_Pallet_to_QRlane_Bulk   
 Scenario: Bulk Marshal the Pallets to QRLane
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I enter Pallet to proceed Bulk Marshalling to QRLANE
    
  
  @QR_Update_QRId_TC07_Update_QRID_Auto_for_Added_records
 Scenario: Update QRId for recently added Records in Mands Table
    Given Update QRId with Random number "P8980765","55719509990300013307299959700210" 
    
   
   @QR_Print_QR_TC08_Print_QR_label
 Scenario: Print QR Label
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Asset Return Menu
    And I Fetch all required data for given URN "55719509990300013307299959700210"
    And I Print DM Label for each quantitiy    
 
   
   @QR_Repack_TC09_Repack_rejectedurn_with_given_pallet
 Scenario: Repack to given Pallet
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Repacking Screen
    And I complete Repack process with given Urn and PalletID "55719509990070013307299957600210","P1765684"    
    
   
   @QR_Repack_TC10_Repack_rejectedurn_with_new_Pallet
 Scenario: Repack to given Pallet
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Repacking Screen
    And I complete Repack process with given Urn and New PalletID "55719509990090013307299957800210"
    
 ##################################################
    
    #To Be Tested#
   @QR_Repack_TC11_Consignment_linking_procedure 
 Scenario: Link the Consignment, Pallet, Trailer
  Given Login to JDA Dispatcher web screen
  And I create a consignment
  And drop the same consignment
  And I create Trailer
  And I create DockDoor
  Then I login as warehouse user in putty
  And I link the pallet and consignment with given PalletId "P1530767"
  And Refresh application
  And Login to JDA Dispatcher web screen
  And I link consignment with trailer
  And I close the consignment
    
   @QRI05_Load_the_Pallet_to_the_Trailer
 Scenario: Load the Pallet to the trailer  
 
 
 #############################################################
 
   #NA- Not necessary scenario now as it will be effective only when user scans the label# 
   @QRI06_Post_Audit_check_for_URN
 Scenario: Post Audit Check Printed QR Label
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Asset Return Menu
    And I Fetch all required data for given URN "55719509990300013307299959700210"
    And I Audit DM Label for each quantitiy
 
 
 
 
 @Sprint04 @ConsignmentLinking @Repacking @TC22_Validate_repack_after_consignment_closure
  Scenario Outline: SN22_Validate repack after consignment closure
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Refresh application
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    Then I login as warehouse user in putty
    And I repack the consignment
    And validate the message is displayed

    Examples: 
      | SKU                |
      | 000000000021071852 |
     
        #Test#
    @QRI03_Test
  Scenario: Read Data for Crossdock receiving
    Given Data loaded into Webservices log for crossdock   
    ##And I read and enter URN and Pallet id    
    
        
    #In Progress# 
    @QRI01_Load_Data_For_crossdock_receiving_for_Multi_from_CSV
   Scenario: Load sku data for multi entries from CSV
    Given Load multiple SKU data records from CSV
    #And Multi Data loaded into Webservices log for crossdock
 