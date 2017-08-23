@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to pre receive the stock

  @boxed_pre_receiving_direct_po_validate_complinace_flag_uploaded
  Scenario Outline: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO "<PreAdviceID>" should be in "Released" status
    And the PO line should have sku, quantity due details
    When I update the compliance flag in database
    Then the compliance details should be updated

    Examples: 
      | PreAdviceID  |
      | PO2138927931 |
      
      @boxed_pre_receiving_fsv_po_validate_complinace_flag_uploaded
  Scenario Outline: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO "<PreAdviceID>" should be in "Released" status
    And the PO line should have sku, quantity due details
    When I update the compliance flag in database
    Then the compliance details should be updated

    Examples: 
      | PreAdviceID  |
      | 25300121679 |
