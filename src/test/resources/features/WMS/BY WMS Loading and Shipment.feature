@BY_WMS @Loading_Shipment_Functionality
Feature: BY WMS Loading and Shipment Functionality
	This Feature is to enable various scenarios
  from which a user can chose to perform tests
  specific for Loading and Shipping across all sites.
  
  #Inputs - TrailerID, Site Id except Hemel, 
  
  
	  @DockSchedule @Loading
	  Scenario: Book a dock
	  Given Login to the JDA screen
	  Then Go to "Trailer Maintenance"
	  Then I create a trailer "TRAILER-004" for site "5665"
	  Then Go to "Dock Scheduler Screen"
	  Then I book a dock for site "5665" and order "6685926896" for trailer "TRAILER-004"
	  
	  @PerformVehicleLoading @Loading
	  Scenario: Perform Vehicle Loading
	  Given Login to terminal
	  Then I navigate to Vehicle Loading screen
	  And I load the trailer "TRAILER-004" for order "6685926896" for site "5665"
	  
	  @PerformLoadingForMultipleOrders
	  Scenario: Load all the orders in a consignment
	  Given Login to terminal
	  Then I navigate to Vehicle Loading screen
	  And I load the trailer "TRAILER-0101" for consignment "TEST-001" for site "3641"
	  
	  @CreateConsignmentforHEMEL @HEMEL
	  Scenario: Create Consignment and link
	  Given Login to the JDA screen
	  Then Go to "Consignment Maintenance"
	  Then I create consignment "CONSGN-7652" for HEMEL with address id "8468"
	  Then Go to "Consignment Drop"
	  Then I perform Consignment Drop for consignment with address id "8468"
	  
	  @Pallet_Consignment_Link @HEMEL
	  Scenario: Perform Pallet - Consignment Linking
	  Given Login to terminal
	  Then I navigate to Inventory Transaction screen
	  And I link the consignment "CONSGN-7652" with the pallet "P1605745"
	 
	  @PerformTrailerConsignmentLinking @HEMEL
	  Scenario: Perform Trailer Consignment Linking
	    Given Login to the JDA screen
	    Then Go to "Trailer Maintenance"
	    Then I create a trailer "TRAILER-07755" for site "5542"
	    Then Go to "Consignment Trailer Linking"
	    Then I perform consignment trailer linking for consignment "CONSGN-7652"
	    
	  @PerformVehicleLoadingForHEMEL @HEMEL
	  Scenario: Close Consignment and Perform Vehicle Loading
	   Given Login to terminal
	   Then I close the consignment "CONSGN-7652" for HEMEL
	   Then I perform Loading for pallet "P1605745" of order "43534534534"
	    
   @Trailer_shipping
    Scenario: Perfrom Shipping
	    Given Login to the JDA screen
	    Then Go to "Trailer shipping"
	    And I enter the site id Trailer id and seal id to perform shipment of trailer "site id","trailer id","seal id"
	
		@JDA_WMS_All_Login_to_hemel_shipment 
	   Scenario: Perform shipment in hemel
		   Given Login to terminal    
		   And Go to Main menu  
		   And Perform shipment in hemel "CONSIGNMENT","TRAILER"
	    