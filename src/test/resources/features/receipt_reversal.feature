@receipt_reversal
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @complete
  Scenario Outline: Lock the inventory from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    #And I have received a Tag ID
#		When I navigate to receipt reversal page
#		And select Receipt Type as Pre-Advice and enter the tag id as "2000134405"
#		And I navigate to next screen and enter the reversal qty 
#		Then Inventory should be updated and I0808 ITL should be generated
