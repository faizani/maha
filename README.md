# maha

**API Request**
```
curl -X POST \
  http://localhost:8082/checkout \
  -H 'content-type: application/json' \
  -d '["001"]'
```
  
running the application (in the IDE): open and run the main class
  ```
  com.ecommerce.maha.MahaApplication
  ```

**Framework:**
* spring-boot 

**Dependencies:**
* spring-boot-starter-data-jpa
* spring-boot-starter-web
* spring-boot-starter-tomcat
* sping-boot-starter-test
* h2database
* lombok
* junit
* gson

**Testing**
* Test coverage by Unit Tests for service layer, usecase layer and Integration Tests from the api controller layer.
