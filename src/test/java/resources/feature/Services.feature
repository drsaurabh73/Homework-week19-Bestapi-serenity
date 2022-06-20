@Services
Feature: Testing different Category on the Best Api Application

  Scenario: Check if the Services application is accessed by the users
    Given I am on Homepage of application services
    When User send a GET Request to list endpoint services
    Then User can get back a valid status code 200 of services

  Scenario: Check if User can add  new Services in the application
    Given I am on Homepage of application services
    When User can create new services using POST method in services application
    Then User can get back a valid status code 201 of services

  Scenario: Check if User can update Services in the application
    Given I am on Homepage of application services
    When User can create new services using PUT method in services application
    Then User can get back a valid status code 200 of services

  Scenario: Check if User can Delete  products in the application
    Given I am on Homepage of application services
    When User can Delete new services using DELETE method in services application
    Then User can get back a valid status code 200 of services
    And User verify that the service is deleted successfully from services