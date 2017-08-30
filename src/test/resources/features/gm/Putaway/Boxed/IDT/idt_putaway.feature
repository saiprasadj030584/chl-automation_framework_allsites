@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @idt @boxed_putaway_idt_putaway_validate_mezz_shelving_putaway @complete
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have MEZZ sku, quantity due details IDT
    When I perform receiving for all skus at location "<Location>" for IDT
    #And the goods receipt should be generated for IDT received stock in inventory transaction
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    #When I perform normal returns putaway
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

    Examples: 
      | PalletId            | ASN        | Location |
      | 0055000263806510923 | 0000004692 | REC002   |

  @boxed @putaway @idt @boxed_putaway_idt_sampling_qa_build @complete
  Scenario Outline: Validate Sampling/QA Pallet build
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the upi should have sku, quantity due details
    When I perform receiving for all skus at location "<Location>" for IDT
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

    Examples: 
      | PalletId             | ASN        | Location |
      | 00050456000576345900 | 0000001733 | REC002   |

 