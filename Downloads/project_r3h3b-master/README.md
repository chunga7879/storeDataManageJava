# Managing Customers’ and Products’ Information in Department Store
The application manages the information of customers and products of department store.
I decided to make this application because I realized it is so important to manage information of customers and 
products in the store when I did part-time job, . 
This application is for big stores such as department store, but it can also be used in ***any*** stores which sells 
products to customers by changing customer levels and some constants. 
 
## Customers Information

For the management of customer information, it adds a customer to customer list and manages membership levels
of customer among *Silver*, *Gold* and *VIP*. It shows list of customers information in each customer levels and list 
of profits which each level has such as rate of points. Furthermore, it shows list of purchase history of each customer 
*(product name, price, purchase date)* by adding product information to list of purchase history when customer buys 
each product.

A *Customer information* list:
- Customer name
- Customer's telephone number
- Membership Level of Customer : *Silver*, *Gold*, *VIP*
- Profits for each level
- Purchase history
- Accumulated points 

## Products Information 
For the management of product, it adds a product to product list and set manager and floor to each product. It manages 
number of products sold and left and shows the list of products including name, price, number of products left, number 
of products sold, name of manager, floor. Furthermore, the list of products can be seen by **ranking** of amount of 
product sold. 

A *Product information* list:
- Product Name
- Product price
- The number of product sold
- The number of product left
- Manager
- floor

## User Stories
### Customer Information Management

- As a user, I want to be able to add a new customer to customer list (Start as Silver Grade).
- As a user, I want to be able to level up customer membership level if customer satisfy conditions.
- As a user, I want to be able to view the list of customer information 
(Name, telephone) in each level.
- As a user, I want to be able to add product name, price, the number(how many), and purchase date
when customer purchases a product to list of purchase history of each customer.
- As a user, I want to be able to sum up total cost the customer spent to buy products. 
- As a user, I want to be able to view a list of purchase history of each customer with total spent and total point
 in the last line.
- As a user, I want to be able to view a list of profits of each customer level.
- As a user, I want to be able to apply profits to each level customer.
- As a user, I want to be able to save customer list to file
- As a user, I want to be able to load my customer list from file
- As a user, when I select the quit option from the application menu, I want to be reminded to save my customer list to 
file and have the option to do so or not.
- s a user, when I start the application(customer), I want to be given the option to load my customerList from file.


### Products Information Management

- As a user, I want to be able to add a product to list of products.
- As a user, I want to be able to set a manager and floor to each product.
- As a user, I want to be able to add the number of purchased to the number of products sold when customers 
buy product.
- As a user, I want to be able to subtract the number of products sold from number of products to get the number 
of products left.
- As a user, I want to be able to view the list of products information.
- As a user, I want to be able to view the list of products by ranking of amount of product sold.
- As a user, I want to be able to save product list to file
- As a user, I want to be able to load my product list from file
- As a user, when I select the quit option from the application menu, I want to be reminded to save my product list to 
file and have the option to do so or not.
- s a user, when I start the application(product), I want to be given the option to load my productList from file.

#Phase 4: Task 2
- Include a type hierarchy in your code other than the one that uses the Saveable interface introduced in Phase 2.
  You must have more than one subclass and your subclasses must have distinct functionality.
  (supertype: Customer, subtype: SilverCustomer, GoldCustomer, VipCustomer) 
  They must therefore override at least one method inherited from a super type and override it in different ways 
  in each of the subclasses. (Override buyProduct method in each subtype classes in different ways)

#Phase 4: Task 3
- Refactoring purchaseInfo in buyProduct
- Change doViewCustomerInformationAllLevel method by using refactored methods.
- I already did refactoring in phase 3 in ViewCustomer class
