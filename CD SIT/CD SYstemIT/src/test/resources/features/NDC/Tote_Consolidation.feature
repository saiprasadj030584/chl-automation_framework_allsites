@Tote_Consolidation
Feature: As a user to verify Tote Consolidation


     @NDC_CONS_001 @complete
  Scenario: To Check VC986 parameters are available and Consolidation operates as required 

    Given SCS Module to initiate consolodation which consolidation is done by  picking workplaces and switching ON the PTT work station
    
       @NDC_CONS_002 @complete
  Scenario: To perform SCS Consolidation stock adjustment 

    Given SCS Module to initiate consolodation which consolidation is done by switching ON the PTT work station and amending the quantity of consolidation
    