Feature: Testing different products on the Best Api Application

  Scenario: Check if the products application is accessed by the users
    Given I am on Homepage of application products
    When User send a GET Request to list endpoint products
    Then User can get back a valid status code 200 of product

  Scenario: Check if User can add  products in the application
    Given I am on Homepage of application products
    When User can create new product using POST method in products application
    Then User can get back a valid status code 201 of product

  Scenario: Check if User can update  products in the application
    Given I am on Homepage of application products
    When User can create new product using PUT method in products application
    Then User can get back a valid status code 200 of product

  Scenario: Check if User can Delete  products in the application
    Given I am on Homepage of application products
    When User can Delete new product using DELETE method in products application
    Then User can get back a valid status code 200 of product
    And User verify that the product is deleted successfully from product