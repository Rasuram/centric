product catalog
---
````
* This is a sample product centric repo, the goal of ths repo is to manage products.  
* I built an application with a RESTful services with the basic CRUD operations.
* The application is connected to H2 database. 
* Used swaggers to describe the API structure .
````

I used the following technologies:

* Swagger 2.6.1
* Spring Boot 2.3.1.RELEASE
* java 1.8
* Spring Data
* H2 Database
* maven for build

How to run?
---

1. Compile the project with the following command:

   ```mvn clean install```

2. The project is a Spring Boot Application, so you can run inside of your ide or from terminal with the following
   command:

   ```mvn spring-boot:run```

Swagger
---

* You can access from the following URL:

  [http://localhost:8080/products/swagger-ui.html][1]

H2 Database
---

* If you prefer you can use any database client, else, you can access from the following URL:

1. Go to: [http://localhost:8080/products/h2-console][2]
2. Setting the following parameters:

```
Driver class : org.h2.Driver
JDBC URL     : jdbc:h2:mem:test
User Name    : sa
Password     :
```

3. Click on `Test Connection` button, this should return `Test successful`
4. Click on `Connect` button.
5. Now you can see the `PRODUCT` table.

``
swagger access  
http://localhost:8080/swagger-ui.html#/product-controller 

``
http://localhost:8080/h2-console
