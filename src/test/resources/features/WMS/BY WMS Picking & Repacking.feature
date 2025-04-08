@BY_WMS @Picking_Functionality
Feature: BY WMS Picking Functionality
  This Feature is to enable various scenarios
  from which a user can chose to perform tests
  specific for picking across all sites.

  @01 @Allocation @BY_WMS_Login_to_SystemAllocation
  Scenario: Doing system allocation for the SKUs that are ordered
    Given Login to the JDA screen
    Then Go to "System Allocation"
    And allocate the order with order id "6706866645"

  @02 @OrderPreparation @BY_WMS_Login_to_OrderPreparation
  Scenario: doing order preparation for the required SKUs
    Given Login to the JDA screen
    Then Go to "order preparation"
    Then prepare orders loaded with consignment name and order id "CONSIGN12345" "6706866643"

  @01 @Allocation @BY_WMS_Login_to_SystemAllocation_for_new_features
  Scenario: Doing system allocation for the SKUs that are ordered
    Given Login to the JDA screen
    Then Go to "System Allocation"
    And allocate the order with order id for new features"5000000001"

  @02 @OrderPreparation @BY_WMS_Login_to_OrderPreparation_for_new_features
  Scenario: doing order preparation for the required SKUs
    Given Login to the JDA screen
    Then Go to "order preparation"
    Then prepare orders loaded with consignment name and order id for new features"C5" "56000039"

  @03 @Clustering @BY_WMS_Login_to_Clustering_GroupID
  Scenario: Cluster an order
    Given Login to the JDA screen
    Then Go to "Clustering"
    And I cluster the "BOXED" order for "5885"

  @04 @Clustering @BY_WMS_Login_to_Clustering_ConfigID
  Scenario: Cluster an order
    Given Login to the JDA screen
    Then Go to "Clustering"
    And I cluster the order with a config id "ConfigID" and  "SITEID"

  @05 @StockCheck @BY_WMS_Login_to_Stock_check_list_generation
  Scenario: Generate list id for stock check
    Given Login to the JDA screen
    Then Go to "Stock Check List Generation"
    And I enter the tag id to generate list id "TAGID"

  @06 @StockCheck @BY_WMS_All_Login_to_new_Stock_check
  Scenario: Login until stock check Screen
    Given Login to terminal
    And Go to Main menu
    And Perform Stock Check for "Location id","qty"

  @07 @StockCheck @BY_WMS_All_Login_to_existing_Stock_check
  Scenario: Login until stock check Screen
    Given Login to terminal
    And Go to Main menu
    And Perform Stock Check for "List id","Location id","Qty"

  #####################################Picking Scenarios to Follow##############################
  #Generic scenarios for NDC Total Cases - 28 Cases
  #Input param 1 - "Trolley"
  #Input param 2 - "7401"/"5665" - currently not included should be tested after HUB"
  #Input param 3 - type "ListID/Order/Consignment"
  #Input param 4 - Values
  @BY_WMS_All_Login_Till_Picking_For_NDC
  Scenario: Swindon and Welham NDC Picking
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform "Trolley" Pick in "7401" for "Order" with value "6706866576"

  #Stoke and West thurrock
  @JDA_WMS_All_Login_Till_Picking_For_NDC's
  Scenario: Stoke and WT
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform NDC "Hang" "Retail" Pick in "5649" for "ListID" with value "PK120063445"

  #Generic scenarios for RDC - 28 Cases
  #Input param 1 - "Box"/"Hang"
  #Input param 2 - "Retail"/"NonRetail"
  #Input param 3 - "7278"/"6868"
  #Input param 4 - type "ListID/Order/Consignment"
  #Input param 5 - Values
  @09 @Picking @BY_WMS_All_Login_Till_Picking_For_RDC
  Scenario: RDC
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform RDC "Box" "NonRetail" Pick in "6868" for "Order" with value "8171100658"

  #Multiple list Input scenario
  @10 @Picking @BY_WMS_All_Login_to_Picking_Using_Multiple_ListID
  Scenario: Trolley Picking Using Multiple ListID
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking for the container With Multiple ListID
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @11 @Picking @BY_WMS_All_Login_to_NonRetail_Picking_Multiple_ListID
  Scenario: Trolley Picking Using Multiple ListID
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking for NOn Retail container With Multiple ListID
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @12 @Picking @BY_WMS_All_Login_to_RDC_Box_Retail_Picking_With_Multiple_List
  Scenario: RDCBox Retail Picking Using Consignment
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking Rdc Box Retail With Multiple List
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @13 @Picking @BY_WMS_All_Login_to_RDC_Hang_Retail_Picking_With_Multiple_List
  Scenario: RDCHang Retail Picking Using Consignment
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking Rdc Hang Retail With Multiple List
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @14 @Picking @BY_WMS_All_Login_to_RDC_Box_NonRetail_Picking_With_Multiple_List
  Scenario: RDCBox Non Retail Picking Using Consignment
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking Rdc Box NonRetail With Multiple List
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @15 @Picking @BY_WMS_All_Login_to_RDC_Hang_NonRetail_Picking_With_Multiple_List
  Scenario: RDCHang Retail Picking Using Multiple list
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform picking Rdc Hang NonRetail With Multiple List
      | LIST_ID     |
      | TRLL1211055 |
      | TRLL1211062 |

  @16 @stock_adjustment @BY_WMS_Existing_Stock_Adjustment
  Scenario: Login till Existing Stock adjustment
    Given Login to the JDA screen
    And Go to "Stock Adjustment"
    And Do Existing Stockadjustment for "000000000022198345","2AA10A02","525","Stock Count"

  @17 @stock_adjustment @BY_WMS_New_Stock_Adjustment
  Scenario: Login Until New Stock Adjustment
    Given Login to the JDA screen
    And Go to "Stock Adjustment"
    And Do New Stock Adjustment "000000022526286003","CA01ED","200","PALLET","Stock Count"

  #@17 @stock_adjustment @BY_WMS_New_Stock_Adjustment
  #Scenario: Login Until New Stock Adjustment
  #Given Login to the JDA screen
  #And Go to "New Stock Adjusment"
  #And Do New Stock Adjustment for "000000000022198345","2AA10A02","700","PALLET","Stock Count"
  #Repacking for Hand RDT Functionalities for all C&H Sites
  @Repack
  Scenario: Repacking
    Given Login to terminal
    And Go to Main menu
    And Select Repacking
    And Perform Repacking using Site "3641" and ContainerID "06579509992560740103"

  @Repacking_for_Hemel
  Scenario: Repacking
    Given Login to terminal
    And Go to Main menu
    And Select Repacking
    And Perform Repacking using URN "55714451067530920701407110102310"

  #TOTE CONSOLIDATION
  @Repack_with_pallet_and_container
  Scenario: Repacking
    Given Login to terminal
    And Go to Main menu
    And Select Repacking for tote consolidation
    And Perform Repacking using Site "5885",To_ContainerID "07706100598340566503823400000010", From_ContainerID "07706100598060566507223500000010"
    And Do Data Validation after Repack

  #TOTE CONSOLIDATION
  @PartialRepack_with_pallet_and_container_upc_qty
  Scenario: Repacking
    Given Login to terminal
    And Go to Main menu
    And Select Repacking for tote consolidation
    And Perform PartialRepacking using Site "5665",To_ContainerID "74016100600020353304911200000010", From_ContainerID "74016100600190353304913100000010",UPC "09772915",Qty "2"
    And Do Data Validation after Repack

  #Parameters
  #site id
  #Order,Consignment,Container,Tag,Sku
  @UNPICK @BY_WMS_Login_to_UnPick
  Scenario: Doing system allocation for the SKUs that are ordered
    Given Login to the JDA screen
    Then Go to "Unpicking and Unshipping"
    And unpick the order with tagid or consignment or sku or container or order and siteid "<tagid>" "<consignment>" "<sku>" "<container>" "<order>" "<site id>"

  #Parameters
  #site id
  #Order,Consignment,Container,Tag,Sku
  #numberoflines
  @UNPICK @BY_WMS_Login_to_UnPick_for_partial
  Scenario: Doing system allocation for the SKUs that are ordered
    Given Login to the JDA screen
    Then Go to "Unpicking and Unshipping"
    And unpick the order with tagid or consignment or sku or container or order and siteid,numberoforder "<tagid>" "<consignment>" "<sku>" "<container>" "<order>" "<site id>" "<numberoflines>"

  #input param 1 -Site id(5885/5649)
  #input param 2 - UPC(separated by space for multiple upc inputs)
  #input param 3 - Qty to do PickException(separated by space for multiple qty inputs)
  #input param 4 - Exception option(1-Damaged stock,2-Not Enough,,3-Split Pick)
  #input param 5 - inputtype(ListID/Order/Consignmnet)
  #input param 6 - valu(listid/orderid/consignment values)
  @JDA_WMS_All_Login_Till_PickException_For_NDC
  Scenario: Stoke and WT
    Given Login to terminal
    And Go to Main menu
    And Select Container Pick
    And Perform PickException for "5885", "07997389 02420615", Qty "1 2",Option "2" for "Order" with value "9710729727"
