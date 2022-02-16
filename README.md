# order-tracking-service
order-tracking-service

Based on Java, SpringBoot, H2

# Build the service

Maven Build : Navigate to the root folder where pom.xml is present mvn clean install

# Swagger

Swagger documentation will run on http://localhost:8080/swagger-ui.html


# H2

H2 database will run on http://localhost:8080/h2-console

Driver Class:	org.h2.Driver

JDBC URL: jdbc:h2:mem:mydb

User Name: sa

Password: password


# About mannual testing

Out of the Swagger, required results could be obtained by posting below curls respectively on Postman;

curl --location --request POST 'localhost:8080/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderId":"",
    "customerId": "1",
    "amount": "100"
}'


curl --location --request POST 'localhost:8080/payment' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderId":"1",
    "amount":"110"
}'


curl --location --request POST 'localhost:8080/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderId":"",
    "customerId": "1",
    "amount": "50"
}'




