@OutboundOrder
Feature: Outbound Order
  As a warehouse user
  I want to order till dispatch

  @boxed_outbound_order_till_despatch_store_order_full_cancel @boxed @store_order @outbound_order_till_despatch @complete
  Scenario Outline: Short store order - full cancel
    Given I have logged in as warehouse user in JDA dispatcher GM application
    And I check the status as "Released" for given "<OrderId>"
    When I navigate to system allocation page
    And I allocate the stocks
    And the status should be turned as "Allocated" in order header
    When I navigate to move task management
    And update the status in move task
    And I delete the order
    When I navigate to Order mangagement
    And update the status as cancelled
    Then verify the status as "Released --> Cancelled" in ITL

    Examples: 
      | OrderId    |
      | 4764303868 |

