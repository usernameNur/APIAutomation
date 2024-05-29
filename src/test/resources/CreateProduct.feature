Feature: Creates a product
  Background:
    Given base url "https://backend.cashwise.us/api"/"
  Scenario: user successfully creates a product
    And I have access
    And I have the endpoint "myaccount/products"
    And I have "product_title" with "dine" in request body
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "2" in request body
    And I have "category_id" with "2" in request body
    And I have "product_description" with "uzbek plov" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "product_title" with "dine" in response body
    Then I delete the product

  Scenario: verify product title is null
    And I have access
    And I have the endpoint "myaccount/products"
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "2" in request body
    And I have "category_id" with "2" in request body
    And I have "product_description" with "uzbek plov" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "product_title" with null in response body
    Then I delete the product
