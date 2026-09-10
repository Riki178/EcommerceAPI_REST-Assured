Feature: Validating login feature

@login
Scenario Outline: Verify user can successfully login or not
	Given Login payload with "<userId>"
	When user calls "LoginAPI" with "post" request
	Then user extracts login credentials from response
	Then user got the success status code 200
	And "message" in response body is "Login Successfully"
	
Examples:
	| userId                  |
	| deysayan1301@gmail.com  |
	

@addProduct	
Scenario: Verify user can create a product
	Given Create product payload
	When user calls "CreateProductAPI" with "post" request
	Then user got the success status code 201
	Then user get the productID
	And "message" in response body is "Product Added Successfully"
	

@placeOrder	
Scenario: Verify user can place an order
	Given Place order payload with "<country>"
	When user calls "CreateOrderAPI" with "post" request
	Given User  gets the orderID
	Then user got the success status code 201
	And "message" in response body is "Order Placed Successfully"
	
Examples:
	| country |
	| India   |

	
@deleteOrder
Scenario: Verify user can delete the order
	Given Delete order payload
	When user calls "DeleteOrderAPI" with "delete" request
	Then user got the success status code 200
	And "message" in response body is "Orders Deleted Successfully"


@deleteProduct
Scenario: Verify user is able to delete the product
	Given Delete product payload
	When user calls "DeleteProductAPI" with "delete" request
	Then user got the success status code 200
	And "message" in response body is "Product Deleted Successfully"
	


