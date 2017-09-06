@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @idt @boxed_putaway_idt_putaway_validate_mezz_shelving_putaway @complete @ds
  Scenario: Validate Mezz/Shelving putaway
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have MEZZ sku, quantity due details IDT
    When I perform receiving for all skus at location "REC002" for IDT
    And the goods receipt should be generated for IDT received stock in inventory transaction
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

  @boxed @putaway @idt @boxed_putaway_idt_sampling_qa_build @complete @ds
  Scenario: Validate Sampling/QA Pallet build
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the upi should have sku, quantity due details
    When I perform receiving for all skus at location "REC002" for IDT
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

  @boxed_putaway_idt_validate_quantity @boxed @putaway @idt @complete @ds
  Scenario: Putaway process - Field Validation in JDA WMS for Boxed type
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed without entering IDT quantity
    Then the error message should be displayed as invalid quantity exception

  @boxed_putaway_idt_validate_location @boxed @putaway @idt @complete @ds
  Scenario: Putaway process - Field Validation in JDA WMS for Boxed type
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed without entering IDT location
    Then the warning message should be displayed

  @boxed_idt_putaway_location_full @boxed @idt @putaway @compete @ds
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed by entering less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction

  @boxed_idt_putaway_location_override @boxed @idt @putaway @compete @ds
  Scenario: Validate Override Putaway Location for IDT-Boxed
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed by overriding the location  "REC001" for IDT
    And the goods receipt should be generated for putaway stock in inventory transaction for override
