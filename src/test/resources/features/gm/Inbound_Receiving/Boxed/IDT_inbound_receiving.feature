@inbound_receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_inbound_receiving_idt_over_receiving @idt @inbound_receiving @boxed @complete
  Scenario Outline: Validate Over receiving for IDT - Boxed 
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Over Receiving" for all skus at location "<Location>" for IDT
    Then the error message should be displayed as excess over receipt

    Examples: 
      | PalletId             | ASN         | Location |
      | PO000504560005112791 | PO001004695 | REC001   |
      
      @idt_over_receiving_lockcod @idt @inbound_receiving @boxed @complete
  Scenario Outline: Validate Over receiving lockcode for IDT - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Over Receiving" for all Locked skus at location "<Location>" for IDT
    Then the error message should be displayed as excess over receipt
    And the ITL should be generated for IDT received stock in inventory transaction.

    Examples: 
      | PalletId                          | ASN         | Location |
      | 56490001060420299900398756000210 | PO001004616 | REC001   |

  @idt_under_receiving @idt @inbound_receiving @boxed
  Scenario Outline: Validate Under receiving for IDT - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Under Receiving" for all skus at location "<Location>" for IDT
    Then the inventory should be displayed for all tags received idt
    And the goods receipt should be generated for IDT received stock in inventory transaction
    Then the idt status should be displayed as "In Progress"

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001384579299900395756000210 | PO0917766083 | REC001   |

  @boxed_idt_putaway_validate_quantity @boxed @putaway @idt @complete
  Scenario Outline: Putaway process - Field Validation in JDA WMS for Boxed type
   # Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed without entering IDT quantity
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001389579299900395756000210| PO0918766083 | REC001   |
      
      @boxed_idt_putaway_validate_location @boxed @putaway @idt
  Scenario Outline: Putaway process - Field Validation in JDA WMS for Boxed type
   Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed without entering IDT location
    Then the warning message should be displayed

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001339579299900395756000210| PO0917862083 | REC001   |
      
