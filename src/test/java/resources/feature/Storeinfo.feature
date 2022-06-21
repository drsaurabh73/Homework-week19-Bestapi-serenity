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

    Scenario: I verify following dat response
      Given I am on Homepage of application products
      When User send a GET Request to list endpoint stores
      Then verify that if the total is equal to 1566
      And  verify that if the stores of limit is equal to 10
      And check the single name in the Array list (Inver Grove Heights)
      And Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
      And Verify the storied=7 inside storeservices of the third store of second services
      And Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
      And Verify the state = MN of forth store
      And Verify the store name = Rochester of 9th store
      And Verify the storeId = 11 for the 6th store
      And Verify the serviceId = 4 for the 7th store of forth service

