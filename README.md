**Day 1:**<br/>
Back-end: Java<br>
DB: postgresql<br>
Font-end: HTML, Javascript, JQuery<br>

As I know, Spring framework provides flexibility to configure beans in multiple ways.<br>
I will use Spring boot, Intelliji and postgres to build this application. I haven't had any knowledge about spring boot and intelliji so I need to search and learn it from online.

1. go start.spring.io to establish a spring boot project with some dependencies.
2. use Intelliji to import this project.<br><br>

After these two steps, I can start coding work.

1. Build an Inventory model class for this project use.
2. Build an Inventory Data Access class for this project access postgres DB
3. Build a service class for Controller class to access DB
4. Build a Controller class to receive and response the request from users.

**Day 2:**<br/>
Code the functions

2. Build an Inventory Data Access class for this project access postgres D
  - insert function
  - get product function by code
3. Build a service class for Controller class to access DB
  - add product function -> insert(DB)
  - get product by code function -> get product by code function(DB)
4. Build a Controller class to receive and response the request from users.
  - post function to get user imported file -> add product(service)
  - search function for user searc product by product code
  
  
**Day 3:**<br/>

2. Build an Inventory Data Access class for this project access postgres DB
  - update inventory function
3. Build a service class for Controller class to access DB
  - update inventory service
4. Build a Controller class to receive and response the request from users.
  - put mapping request function from user
  
HTML + CSS + JS:
- code some forms for search and update
- Use JQuery fetch request to DB without reload the page
- Use JQuery update html body without reload the page


**Implementation:**<br/> 
Test:
Haven't have times to learn use Unit Test so all testing is by myself.

Compile:
1. Use Docker to run postgresql DB in background.
- docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
2. Create a DB call demoDB in postgresql DB
3. Used "spring web" so I can use intellji compile my code with a Tomcat server.


