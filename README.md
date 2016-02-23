Design and implement a RESTful API (including data model and the backing implementation) for money transfers between internal users/accounts.

To represent the system we have created User and Account POJOs.
There is a DB class for mocking the database. We have used MAP to create the in memeory data layer. There is a service layer which interacts with the DB and does the processing. We have considered all the CRUD operations.

We have created custom Exception framework to accomodate all the banking system related exceptions such as 
1. DataNotFoundException - This will handle all the scenarios where the given data in the request is not valid
2. InSufficientAmountException - This will handle the scenario wherein if there is a debit request and the account to be debited does not have enough balance.

In get url I have implemented the HATEAOS concept by providing the link to a url

UserResource.java is the main resouce file. UserService.java provides the implementation

Below is the list of different rest web api USER CRUD operations we have considered:

1)List down all users
GET http://localhost:8080/webservice/webapi/users/ 

2)Fetch the requested user
GET http://localhost:8080/webservice/webapi/users/{userId}

3)Create new user
POST http://localhost:8080/webservice/webapi/users/
Request Body
    {
        "firstName": "Nitesh",
        "lastName": "Kumar",
        "acount":
        {
            "accountNumber": 1,
            "amount": 100
        }
    }
    
4)Update the user
PUT http://localhost:8080/webservice/webapi/users/{userId}

5)Delete the user
DELETE http://localhost:8080/webservice/webapi/users/{userId}

6)Debit transaction
PUT http://localhost:8080/webservice/webapi/users/{userId}/credit
Credit the user's account with request amount passed in the body
It debit the user's account passed in the request body with the request amount
Request Body e.g.
<userAccount>
  <userId>2</userId>
  <amount>10</amount>
</userAccount>

7)Credit transaction
PUT http://localhost:8080/webservice/webapi/users/{userId}/debit
Debit the user's account with request amount passed in the body
It credit the user's account passed in the request body with the request amount
Request body
e.g.
<userAccount>
  <userId>2</userId>
  <amount>10</amount>
</userAccount>


