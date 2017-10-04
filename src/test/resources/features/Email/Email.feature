@tag
Feature: As a user I have to trigger the automation email

  @test_auto
  Scenario: demo
    Given demo file

  @Email
  Scenario: Triggering automation email
    Given Insert the metrics details in automation metrics DB
    Then I trigger email to all the stakeholders
