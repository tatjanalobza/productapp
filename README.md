# productapp

Application to view products and add products:  
REST microservice with 2 endpoints - POST, GET. 

Swagger used to document the service and it's endpoints. 

The service was built using Maven, Spring Boot, Spring JDBC template, H2 in-memory database. I have also used lombok to make the code compact, Javax for validating the name field (non blank) and the currentPrice field (non negative).

Component tests are written using JUnit framework.
