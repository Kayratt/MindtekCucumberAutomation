@HR-6 @ui
Feature: Validating Order creation functionality

  @regression
  Scenario Outline: Validating calculate total functionality
    Given User navigates to application
    When log in with username "Tester" and password "test"
    And User clicks on "Order" module
    And User provides product "<Product>" and quantity <Quantity>
    Then User validates total is calculated properly <Quantity>
    Examples:
      | Product     | Quantity |
      | FamilyAlbum | 1        |
      | MyMoney     | 5        |

  @smoke @regression
  Scenario: Validating order creation functionality
    Given User navigates to application
    When  log in with username "Tester" and password "test"
    And User clicks on "Order" module
    And User creates an order with data
      | Product     | Quantity | Customer name | Street       | City    | State | Zip   | Card | Card Nr     | Exp date |
      | FamilyAlbum | 1        | John Doe      | 123v Dee rd. | Chicago | IL    | 12345 | Visa | 41241294912 | 12/21    |
    Then User validates error success message "New order has been successfully added."
    And User validates that created order are in the list of all orders







