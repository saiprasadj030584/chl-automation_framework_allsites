@rpwms
Feature: As a RP user to be verify whether the given order is shipped

  @DespatchScenario
  Scenario Outline: To be verify the given order is shipped
    Given user logged in to the RPWMS application
    #Then Verify the given "<OrderNo>" is Shipped 
    And user navigate to Order header for the given "<OrderNo>"
    And user request to generate the listId
    And user request to perform picking 
    And user request to perform Marshalling
    And user navigate to dock scheduler screen to create new booking 
    And user request to perform yard check in for mapping the booking id
    And user request to perform vehicle loading
    When user navigate to Trailer Shipping screen
    Then the order should be moved to shipped status

    Examples: 
      | OrderNo    |
      | 5000234022 |
      
     @NDCDespatch
     Scenario: To be verify UK and Store orders in two different trailers
     Given user logged in to the RPWMS application
