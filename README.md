# About the project:
This is a spring-boot project that exposes an API for validating a list of transactions records
that every record should has the following fields with value more than zero  
- id  
- transactionAmount
- fromId
- toId 

# Used Libraries: 
- **hibernate-validator:** for validating the data in the request body 
- **gson:** For streaming the list of the records from JSON file  one by one for saving the memory

# Exposed APIs: 
**Path:** /validate ```or /vaccNow/validate if depolyed on tomacat```

**Method:** Post 

**Description:** this method is responsible for validating list of transactions records  

**Implementation in details:** 
- Validate that the body exists and not empty
- Decrypt the body 
- Save the body to a file to save the memory
- Star parsing the body record by record and validate it
- Return HttpStatus if the body is ok
- Route to another service if the data is not valid 
> Note : We can use the following as a valid body for calling the method ```{
                                                                                "data": "[{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874},{\"id\":123,\"transactionAmount\":1234,\"fromId\":1234, \"toId\":9874}]"
                                                                            }```  

##Estimation:
- From half to full day for analysing the best solution
- From 1 to 2 days for full implementation
