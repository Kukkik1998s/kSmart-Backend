
# kSmart Backend

## What is this?
Backend of kSmart application, also provide web interface for doctor can use from web browser.


## Getting Started
### 1. Requirements

 - [Java SDK 11](https://adoptopenjdk.net/installation.html)
 - [MongoDB](https://docs.mongodb.com/manual/administration/install-community/)
 - [node.js](https://nodejs.org/en/)
 - [vue.js](https://cli.vuejs.org/guide/installation.html)
 -  Gmail account for application


### 2. Clone Repository
      $ git clone https://github.com/Kukkik1998s/kSmart-Backend.git
     

### 3. Build and Run 
The project divided into 2 applications
#### 3.1 kSmart Spring Boot
##### 3.1.1 Set up the following properties in application.properties
 - jwt.secret
 - spring.data.mongodb.database  
 - spring.data.mongodb.host 
 - spring.data.mongodb.port
 - spring.mail.from.email
 - spring.mail.username
 - spring.mail.password

##### 3.1.2 Build

    mvn clean install

##### 3.1.3 Run

    mvn spring-boot:run

#### 3.2  kSmart Web

##### 3.2.1 Project setup

    npm install
##### 3.2.2 Compiles and hot-reloads for development

    npm run serve
##### 3.2.3 Compiles and minifies for production

    npm run build

 

