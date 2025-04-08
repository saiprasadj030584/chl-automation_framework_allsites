#Author Dhivya N

@Ollerton_Putawaysequence&Locationsequence 
Feature: As a user to verify the GB_Ollerton cases

  @Putawaysequence @complete 
  Scenario: Validate Putaway sequence and if tasks inbetween can be skipped and done at the end
    Given As User done the data setup for the given sku
    And User perform receiving for different skus 
    When Putaway into same sort location & Perform final putaway,Putaway 1st SKU, Skip 2nd SKU by F8 and continue Putaway for remaining SKUs
    Then skipped SKU should be displayed for Putaway at the end 
          
    
  @Location_Putawaysequence @complete 
  Scenario: Validate Putaway sequence - the location identified by system should be earliest in putaway sequence among locations suits for other conditions (Dim, Volume)
    Given As User done the data setup for the given sku
    And As user login to HHT & user perform receiving for two different skus 
    When Perform putaway for first sku & then perform for second sku    
    Then Putaway location selected as per the putaway sequence
    
     
  @Locationvolume_Exceeds @complete 
  Scenario: Putaway not to be allowed when the Pallet Volume and Location Current Volume and Location allocated volume exceeds Location volume
    Given As User to launch RP WMS & User done the data setup for the given sku
    And As user login to HHT & user perform receiving for skus 
    When Perform putaway untill Locations Current Volume doesnt exceed Location volume  
    Then Putaway location has changed once location volume exceeds