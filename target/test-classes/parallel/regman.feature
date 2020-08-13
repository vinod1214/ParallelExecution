Feature: Regression Manager Tests

  #@Scenario2
  #Scenario: Regression Manager Add
    #Given i was provided with suites manager window
    #Then i expand tree node
    #Then i expand tree node13
    #Then i will drag and drop mrl

  @Scenario3
  Scenario: Regression Manager Remove
    Given i am provided with suites manager window
    Then i expand run testMrl suite tree node
    Then i select and remove mrl from suite
