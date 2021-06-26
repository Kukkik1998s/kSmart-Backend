
# kSmart Spring Boot

## What is this?
Backend of kSmart application and kSmart website


## Getting Started
### 1. Java Environment Set Up

> This project use Java JDK 11


### 2. Clone Repository
      $ git clone https://github.com/Kukkik1998s/ksmart-springboot-mongodb.git
      $ cd ksmart-springboot-mongodb/

### 3. Build and Run 
3.1 Requirements

 - MongoDB
 - Gmail account for application.
 
3.2 Set up the following properties in application.properties
 - jwt.secret
 - spring.data.mongodb.database  
 - spring.data.mongodb.host 
 - spring.data.mongodb.port
 - spring.mail.from.email
 - spring.mail.username
 - spring.mail.password

3.3 Build

    mvn clean install

3.4 Run

    mvn spring-boot:run

