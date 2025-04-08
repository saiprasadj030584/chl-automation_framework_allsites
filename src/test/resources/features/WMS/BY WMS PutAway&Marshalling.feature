@BY_WMS @putaway_Marshalling_Functionality
Feature: BY WMS Putaway
	This Feature is to enable various scenarios
  from which a user can chose to perform tests
  specific for Putaway .
					#PUTAWAY TEST CASES 
#BY_WMS_West_Thurrock_Login_to_PutAway                   - Login to terminal and perform normal putaway for a pallet
#BY_WMS_West_Thurrock_Login_to_PutAway_Multiple_Pallet   - Login to terminal and perform normal putaway for multiple pallet
#BY_WMS_West_Thurrock_Login_to_PutAway_with_location     - Login to terminal and perform normal putaway for a pallet to given location
#BY_WMS_Stoke_Login_to_PutAway                           - Login to terminal and perform normal putaway for a pallet        
#BY_WMS_Stoke_Login_to_PutAway_Multiple_Pallet           - Login to terminal and perform normal putaway for multiple pallet
#BY_WMS_Stoke_Login_to_PutAway_with_location             - Login to terminal and perform normal putaway for a pallet to given location
#BY_WMS_Swindon_Login_to_PutAway                         - Login to terminal and perform normal putaway for a pallet
#BY_WMS_Swindon_Login_to_PutAway_Multiple_Pallet         - Login to terminal and perform normal putaway for multiple pallet
#BY_WMS_RDC_Login_to_PutAway                             - Login to terminal and perform normal putaway for a pallet
#BY_WMS_RDC_Login_to_PutAway_With_location							 - Login to terminal and perform normal putaway for a pallet to given location
#BY_WMS_RDC_Login_to_PutAway_Multiple_Pallet             - Login to terminal and perform normal putaway for multiple pallet
#BY_WMS_Welham_Login_to_PutAway													 - Login to terminal and perform normal putaway for a pallet
#BY_WMS_Welham_Login_to_PutAway_Multiple_Pallet   			 - Login to terminal and perform normal putaway for multiple pallet

#Consolitated putaway test cases
#BY_WMS_Package_Login_to_PutAway
  # First parameter  - SiteId
   # Second Parameter - Tag or pallet
   
   
       
   #Consolitated Putaway Scenario for all sites 
   # First parameter  - SiteId
   # Second Parameter - Tag or pallet 
  @BY_WMS_Package_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for "5665" "00055426006559119013" 
    
    # For individual Site
      #Inputs - TagID
  @BY_WMS_West_Thurrock_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for WestThurrock "2156"
    
  @BY_WMS_West_Thurrock_Login_to_PutAway_Multiple_Pallet 
   Scenario: Normal Putaway Using multiple pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for WestThurrock to Multiple Pallet
    | Pallet_ID                 |
    | 00050456001538401955      |
    | 2405                      |  
    
    #Inputs - TagID and Location
   @BY_WMS_West_Thurrock_Login_to_PutAway_with_location 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for WestThurrock "2611" to "C6275I"
    
 #PUTAWAY TEST CASES FOR STOKE DC
 #Inputs - TagID 
  @BY_WMS_Stoke_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Stoke "2156"

	@BY_WMS_Stoke_Login_to_PutAway_Multiple_Pallet 
   Scenario: Normal Putaway Using multiple pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Stoke to Multiple Pallet
    | Pallet_ID                 |
    | 00050456001538401955      |
    | 2405                      | 
    
    #Inputs - TagID and Location
   @BY_WMS_Stoke_Login_to_PutAway_with_location 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Stoke "2611" to "C6275I"
    
  #PUTAWAY TEST CASES FOR Swindon NDC 
   #Inputs - TagID 
  @BY_WMS_Swindon_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Swindon "00055426006559119012"
    
  @BY_WMS_Swindon_Login_to_PutAway_Multiple_Pallet 
   Scenario: Normal Putaway Using multiple pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Swindon to Multiple Pallet
    | Pallet_ID                 |
    | 00050456001538401955      |
    | 2405                      | 
    
 #PUTAWAY TEST CASES FOR RDC's
  #Inputs - TagID 
  @BY_WMS_RDC_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for RDC "4725"
    
    #PUTAWAY TEST CASES FOR RDC's
     #Inputs - TagID and Location
  @BY_WMS_RDC_Login_to_PutAway_With_location 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for RDC "4725" to "C6275I"
    
   @BY_WMS_RDC_Login_to_PutAway_Multiple_Pallet 
   Scenario: Normal Putaway Using multiple pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for RDC to Multiple Pallet
    | Pallet_ID                 |
    | 00050456001538401955      |
    | 2405                      | 
    
    #PUTAWAY TEST CASES FOR Welham NDC 
     #Inputs - TagID 
  @BY_WMS_Welham_Login_to_PutAway 
   Scenario: Normal Putaway Using pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Welham "00055426006559119012"
    
   @BY_WMS_Welham_Login_to_PutAway_Multiple_Pallet 
   Scenario: Normal Putaway Using multiple pallet
    Given Login to terminal
    And Go to Main menu
    And Select Normal Putaway
    And Perform Putaway for Welham to Multiple Pallet
    | Pallet_ID                 |
    | 00050456001538401955      |
    | 2405                      | 
    
    #Marshalling
    @Marshalling 
	   Scenario: Login until Marshal Screen
	   Given Login to terminal    
	   And Go to Main menu  
	   #for Swindon  and Stoke enter from location and for hemel enter To locaton
	   And Perform perform Marshalling "Siteid","palletid","location"
 
    