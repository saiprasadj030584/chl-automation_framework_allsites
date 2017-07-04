@pre_advice_lock
Feature: Pre-Advice Lock
  As a warehouse user
  I want to lock a product
  So that those product cannot be received

  @complete @po @pre_advice_lock
  Scenario Outline: Lock the product from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I search the pre-advice id "<preAdviceId>" and SKU id "<skuId>" in pre-advice line maintenance page
    And I lock the record with lockcode as "<lockCode>"
    Then the record should be locked

    Examples: 
      | preAdviceId | skuId    | lockCode             |
      |  2058206816 | 21036013 | Code Approval        |
      |  2058206816 | 21036013 | Components Stock     |
      |  2058206816 | 21036013 | 1Damaged             |
      |  2058206816 | 21036013 | 1Expired             |
      |  2058206816 | 21036013 | Hampers Stock        |
      |  2058206816 | 21036013 | Head Office Request  |
      |  2058206816 | 21036013 | Incubation           |
      |  2058206816 | 21036013 | Outlets Stock        |
      |  2058206816 | 21036013 | Product Recall       |
      |  2058206816 | 21036013 | Return from RDC      |
      |  2058206816 | 21036013 | Return from Supplier |
      |  2058206816 | 21036013 | Supplier Damage      |
      |  2058206816 | 21036013 | Warehouse Damage     |
