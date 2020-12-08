# About the project:
VaccNow is an healthcare organization managing the process of Covid-19 vaccine to public,
so that the VaccNow is planning to build multi their digital channels for consuming a modern API for basic features. 
# Used Technologies: 
- **Spring-Boot:** For exposing APIs and modifying the DB 
- **Angular 9:** For Web view
- **H2** For in memory DB so it be easy to run the project 

# ERD: 
![Alt text](ERD.jpg?raw=true "ERD")

# Exposed APIs: 
- **Path:** /branch , /branch?branch=1

    - **Method:** Get 

    - **Description:** this method is responsible for getting All the branches with its details 
    and getting details of a spastic branch 
    
- **Path:** /branch/mock-up ,

    - **Method:** Get 

    - **Description:** this method is responsible for getting All only list of branches names and ids 
- **Path:** /branch/available-time-slots ,
 
     - **Method:** Get 
 
     - **Description:** this method is responsible for getting all the available time slots 
     at spastic for a branch  
     
- **Path:** /vaccination/applied , /vaccination/applied?branch=1, /vaccination/applied?branch=1&dateFrom=2020-12-01
 , /vaccination/applied?branch=1&dateFrom=2020-12-01&dateTo=2020-12-30
 
     - **Method:** Get 
 
     - **Description:** this method is responsible for getting all applied vaccination 
     for per branch and  per time period
- **Path:** /vaccination/confirmed , /vaccination/confirmed?branch=1, /vaccination/confirmed?branch=1&dateFrom=2020-12-01
                                      , /vaccination/confirmed?branch=1&dateFrom=2020-12-01&dateTo=2020-12-30
 
     - **Method:** Get 
 
     - **Description:** this method is responsible for getting all confirmed vaccination 
     for per branch and  per time period

- **Path:** /vaccination/schedule 

     - **Method:** Post 
 
     - **Description:** this method is responsible for scheduling a vaccination
     
     - **body:** ``` {
                         "vaccinationDay": "2020-12-08",
                         "branchVaccine": "1",
                         "timeSlot": "1",
                         "payingType": "c",
                         "clintEmail":"forallthings93@gmail.com"
                     }``` 


- **Path:** /payment-methods 

     - **Method:** Get 
 
     - **Description:** this method is responsible for getting all available payment methods

# Web View:

![Alt text](webViewScreenShoots/1.png?raw=true "1")
![Alt text](webViewScreenShoots/1.png?raw=true "2")
![Alt text](webViewScreenShoots/1.png?raw=true "3")
![Alt text](webViewScreenShoots/1.png?raw=true "4")
![Alt text](webViewScreenShoots/1.png?raw=true "5")

# Putting Project on rails:

This spring boot with embed tomcat and inMemory DB with some mock ups values so there is no special configuration required  
