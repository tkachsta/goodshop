Bookshop

How to run application:
1. Download and unzip archive in any folder;
2. Run in IntelliJ IDEA or another compiler:
    1. First start - in application.yaml file you have to configure 
       ddl-auto: create, sql:init:mode: never;
    2. Second start - in application.yaml file you have to configure  
       ddl-auto: update, sql:init:mode: always;
    3. Third and next runs - in application.yaml you have to configure 
       ddl-auto: update, sql:init:mode: never;
3. Enter in your web-browser http://localhost:8072/api/swagger-ui/index.html to get access to available end-points;
4. Using /api/registration/buyer-reg register user admin@gmail.com and assign gim admin role in SQL table `user_groups`.
5. Now you can access all endpoints. Keep in mind some endpoints requires basic authentication.