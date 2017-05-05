@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @wip1
  Scenario Outline: Putaway process in JDA WMS
    Given the tag id should be received
    When I login as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I select user directed option in main menu
    And I select normal putaway
    And I enter the tag and check string
    Then I should see the location zone
    
    Examples: 
      | Host                                    | Port  |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 | 
      
      
     #Given the tag id should be received
    #When I navigate to move task update
    #And I relase the tag
    #Then the tag id should be released
    #When I login as warehouse user in Putty with host "<Host>" and port "<Port>"
    #And I select user directed option in main menu
    #And I select normal putaway
    #And I enter the tag and check string
    #And I proceed to complete
    #Then I should be directed to putent page
    #When I navigate to location page
    #Then I should see the location zone
    #When I naviagate to inventory query page
    #Then I should see the location zone in inventory page
    #When I navigate to inventory transaction query page
    #Then I should see the from location, to location and final location for the tag
    #And I should see the uploaded status and uploaded file in the miscellaneous2 tab
