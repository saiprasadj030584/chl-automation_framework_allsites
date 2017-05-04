@pre_advice_lock
Feature: Pre-Advice Lock
  As a warehouse user
  I want to lock a product
  So that those product cannot be received

  @complete @lock_status
  Scenario Outline: Lock the product from unlocked status
    When I search the pre-advice id "<preAdviceId>" and SKU id "<skuId>" in pre-advice line maintenance page
    And I lock the record with lockcode as "<lockCode>"
    Then the record should be locked

    Examples: 
      | preAdviceId | skuId    | lockCode             |
      |  8050004583 | 21036013 | Code Approval        |
      |  8050004583 | 21036013 | Components Stock     |
      |  8050004583 | 21036013 | 1Damaged             |
      |  8050004583 | 21036013 | 1Expired             |
      |  8050004583 | 21036013 | Hampers Stock        |
      |  8050004583 | 21036013 | Head Office Request  |
      |  8050004583 | 21036013 | Incubation           |
      |  8050004583 | 21036013 | Outlets Stock        |
      |  8050004583 | 21036013 | Product Recall       |
      |  8050004583 | 21036013 | Return from RDC      |
      |  8050004583 | 21036013 | Return from Supplier |
      |  8050004583 | 21036013 | Supplier Damage      |
      |  8050004583 | 21036013 | Warehouse Damage     |
