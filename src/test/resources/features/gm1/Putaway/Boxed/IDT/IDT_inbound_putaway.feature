@inbound_putaway_IDT
Feature: Inbound Putaway IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_putaway_idt_validate_quantity @boxed @putaway @idt @complete
  Scenario Outline: Putaway process - Field Validation in JDA WMS for Boxed type
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed without entering IDT quantity
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PalletId                         | ASN          | Location |
      | 56490001389579299900395756000210 | PO0918766083 | REC001   |

  @boxed_putaway_idt_validate_location @boxed @putaway @idt @complete
  Scenario Outline: Putaway process - Field Validation in JDA WMS for Boxed type
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed without entering IDT location
    Then the warning message should be displayed

    Examples: 
      | PalletId                         | ASN          | Location |
      | 56490001339579299900395756000210 | PO0917862083 | REC001   |

  @boxed_idt_putaway_location_full @boxed @idt @putaway @complete
  Scenario Outline: Validate Putaway Logic for receiving singles when locations full
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed by entering less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction

    Examples: 
      | PalletId                         | ASN          | Location |
      | 56490001335578299900395756000210 | PO0915231058 | REC001   |

  @boxed_idt_putaway_location_override @boxed @idt @putaway
  Scenario Outline: Validate Override Putaway Location for IDT-Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" for IDT
    When I choose normal putaway
    And I proceed by overriding the location  "<Location>" for IDT
    And the goods receipt should be generated for putaway stock in inventory transaction for override

    Examples: 
      | PalletId                         | ASN        | Location |
      | 56490001042220299900398756000810 |0000003306 | REC001   |
