Here in this project we have 4 urls :

1. Add credit -> localhost:8080/leo-vegas/add-credit
2. Withdraw credit ->  localhost:8080/leo-vegas/withdraw-credit
3. Transactions History -> localhost:8080/leo-vegas/transaction-history
4. Credit inquiry -> localhost:8080/leo-vegas/credit-inquiry

Add credit and withdraw credit have the same payload in the body like below

{
"email":"jk@gmail.com",
"amount": 100,
"transactionId":124
}

In above payload we provide some information about the player (here its the email of the player) and the amount and
Transaction id none of them must be empty and the transactionId must be unique as well otherwise you get the error
like below

{
"message": "Transaction ID must be unique",
"code": "LV-104"
}

and if you dont provide the required data you get a proper error.
If you dont pass the email address in the request :

{
"email": "Email must not be empty"
}

For the transaction history and credit inquiry we only provide the email address of the player

You can see more in swagger-ui in the links below:

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/leo-vegas-doc