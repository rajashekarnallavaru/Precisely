@preciselyTests

Feature: Precisely test cases

  Background:
    Given the user navigate to url

  @preciselyTests1
  Scenario: 1-Test case1
    When user clicks on contact from menu
    When the user insert "rajashekar" as first name
    When the user insert "nallavaru" as last name
    When the user insert "precisely" as company name
    When the user insert "test@user.com" as email
    When the user insert "123-456-7890" as phone number
    When the user select "Canada" as country
    When the user select "Education" as industry
    When the user insert "comment" as comment
    And the user click on agree checkbox
    And the user click on submit button
    Then check for thank you text on screen

  @preciselyTests2
  Scenario: 1-Test case2
    When the user clicks on search button
    When the user enter "govern" in search text field
    When the user clicks on "Building Data Trust with Strategic Data Governance" from search results
    Then verify the link from text should be "https://www.infogix.com/solutions/regulatory-compliance/"