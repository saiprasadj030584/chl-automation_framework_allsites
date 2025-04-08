@DS
Feature: Triggering ds jobs

  @Execute_itl_ds @Data_Stage @Complete
  Scenario Outline: To be executed inventory transaction DS job
    Given import DS details and connect to DS server in "<region>" for the interface "<interface>"
    Then execute datastage job
    Then validate resultant status code

    Examples: 
      | region | interface  |
      | CATEA  | ITL_DS_JOB |

  @Execute_SAP_ds @Data_Stage @Complete
  Scenario Outline: To be executed sap common extract DS job
    Given import DS details and connect to DS server in "<region>" for the interface "<interface>"
    Then execute datastage job
    Then validate resultant status code

    Examples: 
      | region | interface      |
      | CATEA  | Common_Extract |

  @1Execute_gr_ds @Data_Stage @Complete
  Scenario Outline: To be executed goods receipt DS job
    Given import DS details and connect to DS server in "<region>" for the interface "<interface>"
    Then execute datastage job
    Then validate resultant status code

    Examples: 
      | region | interface |
      | CATEA  | GR_I0181  |

  @1Execute_Adjustment_ds @Data_Stage @Complete
  Scenario Outline: To be executed interface I102 DS job
    Given import DS details and connect to DS server in "<region>" for the interface "<interface>"
    Then execute datastage job
    Then validate resultant status code

    Examples: 
      | region | interface        |
      | CATEA  | Adjustment_I0102 |
