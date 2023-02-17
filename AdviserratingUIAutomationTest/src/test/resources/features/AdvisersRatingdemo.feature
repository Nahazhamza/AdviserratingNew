@AdviserRating
Feature: Demo

  Scenario: Check adviser's profile page displays correct practice And addresses
    Given I Navigate to "AdviserratingURL"
    When I Click on Adviser Tab
    And Enter "Brett Dillon" on the Search box
    And Iterate the dropdown and select "Brett Dillon"
    Then The current Page URL which should contain "Brett-Dillon" 
    And The Advisers profile image is displayed with src url contains "brett-andrew-dillon"
    And The name is "Brett Dillon"
    And The Advisor name is "Saige Financial Planning Pty Ltd" 
    And The location will be "Erina, NSW 2250"
    And About tab is "About Brett"
    When The google map is displayed with the pin to the address in "Erina, NSW 2250"
    Then The Map URL center parameter value should contains "center=-33.4386,151.3868"
    And The  practice name is displayed under the map is 'Saige Financial Planning Pty Ltd'
    And The practice logo is displayed
    And Address check is performed
    

    
    
