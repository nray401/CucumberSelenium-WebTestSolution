@fromWishList
Feature: Verify lowest price item in my cart after adding the four items in wishlist

  Scenario: Check the lowest price item added and verify that in your cart.
    Given I add four different products to my wishlist
    When I view my wishlist table
    Then I find total four selected items in my wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart