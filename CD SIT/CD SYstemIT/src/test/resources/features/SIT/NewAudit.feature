@NewAudit
Feature: Audit should be validated

  @ms_audit_message
  Scenario Outline: To be verify MQ To DB pattern
    Given I need to test the pattern for "<Source>" in region "<Destination>" and "<MSG>" and "<MSG_ID>" for interface "<Interface>"
    Then the validation of audit in MS Middleware should be successful

    Examples: 
      | Source | Destination | MSG        | MSG_ID         | Interface |
      | WMS    | SAP         | Message_id | 46240100548719 |       181 |

  @ms_audit_correlation
  Scenario Outline: To be verify MQ To DB pattern
    Given I need to test the pattern for "<Source>" in region "<Destination>" and "<MSG>" and "<MSG_ID>" for interface "<Interface>"
    Then the validation of audit in MS Middleware should be successful

    Examples: 
      | Source | Destination | MSG            | MSG_ID           | Interface |
      | SAP    | WMS         | Correlation_id | 0000001971899603 |       177 |
