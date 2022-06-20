
Feature: Testing different Category on the Best Api Application

  Scenario: Check if the Category application is accessed by the users
    Given I am on Homepage of application category
    When User send a GET Request to list endpoint category
    Then User can get back a valid status code 200 of category

  Scenario: Check if User can add  new Category in the application
    Given I am on Homepage of application category
    When User can create new category using POST method in category application
    Then User can get back a valid status code 201 of category

  Scenario: Check if User can update Category in the application
    Given I am on Homepage of application category
    When User can create new category using PUT method in category application
    Then User can get back a valid status code 200 of category

  Scenario: Check if User can Delete  products in the application
    Given I am on Homepage of application category
    When User can Delete new category using DELETE method in category application
    Then User can get back a valid status code 200 of category
    And User verify that the category is deleted successfully from category