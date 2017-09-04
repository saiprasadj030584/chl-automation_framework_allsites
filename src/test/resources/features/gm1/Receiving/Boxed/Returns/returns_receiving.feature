@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_verification_movement_label_field_blind_receiving @receiving @returns @boxed @complete
  Scenario Outline: Verification of movement label field in the blind receiving screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      | 95580085370650011050230212341238 | 0000838629 | REC003   |

  @boxed_receiving_returns_receiving_returns_single_ASN_multiple_URRN @receiving @returns @boxed @complete
  Scenario Outline: Verify receiving for single ASN holds many URRN
    Given the multiple UPI "<PalletId>" of type "Boxed" and ASN "<ASN>" should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi

    Examples: 
      | PalletId                                                          | ASN        | Location | Condition |
      | 58850008387380077010083865900300,58850008387380077010083856100300 | 0000838748 | REC003   | Y         |
  
  @boxed_receiving_returns_verify_due_receipt_date_upi_management @receiving @returns @boxed @complete
  Scenario Outline: Verify the ASN in the UPI management and check the due and receipt  date  along with ASN
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    And I check the inventory for transaction update
    When I navigate to UPI Management screen
    And I search with ASN in UPI Management screen
    Then the due date and receipt date should be displayed for the ASN

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008387380077010083873800300 | 0000838738 | REC003   | Y         |
      
       @boxed_receiving_returns_receiving_with_single_supplier @returns @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and unique UPC with one supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' 
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for normal upc with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850004220650077010027343300600 | 0000133216 | REC003   | Y         |
      
      @boxed_receiving_returns_receiving_unique_upc_n_condition @returns @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and unique UPC, quantity defaulted as '1' and perfect condition as 'N'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for normal upc with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850004395640077010027343300600 | 0000478916 | REC003   | N         |
