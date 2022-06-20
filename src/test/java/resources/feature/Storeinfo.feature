@Smoke
Feature: Testing different products on the Best Api Application

  Scenario: Check if the Store application is accessed by the users
    Given I am on Homepage of application products
    When User send a GET Request to list endpoint stores
    Then User can get back a valid status code 200 of stores

  Scenario: Check if User can add  new stores in the application
    Given I am on Homepage of application products
    When User can create new store using POST method in stores application
    Then User can get back a valid status code 200 of stores

  Scenario: Check if User can update  products in the application
    Given I am on Homepage of application products
    When User can create new store using PUT method in stores application
    Then User can get back a valid status code 200 of stores

  Scenario: Check if User can Delete  products in the application
    Given I am on Homepage of application products
    When User can Delete new product using DELETE method in stores application
    Then User can get back a valid status code 200 of stores
    And User verify that the product is deleted successfully from stores