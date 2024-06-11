Feature: Buy Product from Amazon ECommerce site

  Scenario Outline: Verify user is able to search and add products to cart
    Given Amazon URL and Product List
    When Searched for "<product_name>" and added "<product_number_in_search_results>" product to cart
    Then Verify product is added to cart
    And Verify product price is identical
    And Verify subtotal is identical to product page

    Examples:
    | product_name | product_number_in_search_results |
    | headphones, keyboards | 1, 1                    |
