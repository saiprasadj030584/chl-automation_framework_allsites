@master_data_article_setup
Feature: Master Data ArticleSetup
  As a warehouse user
  I want to setup articles via master data interfaces and one time activities

  @pack_congig_creation_for_SKUs
  Scenario Outline: Validate whether pack config can be created for SKUs
    Given the SKU "<SKU>" is loaded in warehouse
    When I create config with TagVolume  "<TagVolume>" and TrackingLevel1  "<TrackingLevel1>"
    Then I link SKU "<SKU>" with configId in Pack Config Linking
    And Pack Config should be displayed for SKU in Pack Config Setting

    Examples: 
      | SKU                              | TagVolume | TrackingLevel1 |
      | 58850005786180077010057861800100 |         1 | EA             |
      | 58850007286180077010072861800100 |         1 | SINGLES        |
      | 58850006086180077010060861800100 |         1 | METRIC         |
