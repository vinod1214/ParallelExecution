$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/parallel/login.feature");
formatter.feature({
  "name": "Regression Manager Login Test",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Regression Manager Login",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Scenario1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am given with RegMan Home Page",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginPage.i_am_given_with_RegMan_Home_Page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate the HomePage title",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginPage.i_validate_the_HomePage_title()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "check for oracle logo",
  "keyword": "And "
});
formatter.match({
  "location": "LoginPage.check_for_oracle_logo()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.uri("file:src/test/resources/parallel/regman.feature");
formatter.feature({
  "name": "Regression Manager Tests",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Regression Manager Remove",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Scenario3"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "i am provided with suites manager window",
  "keyword": "Given "
});
formatter.match({
  "location": "RemoveMRLFromSuite.i_am_provided_with_suites_manager_window()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "i expand run testMrl suite tree node",
  "keyword": "Then "
});
formatter.match({
  "location": "RemoveMRLFromSuite.i_expand_run_testMrl_suite_tree_node()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "i select and remove mrl from suite",
  "keyword": "Then "
});
formatter.match({
  "location": "RemoveMRLFromSuite.i_select_and_remove_mrl_from_suite()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});