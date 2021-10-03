# muin_backend
무인 매장 관리 시스템 서버 API입니다.

|UML|ERD|
|:---:|:---:|
|![usecase_diagram](https://user-images.githubusercontent.com/30483337/133754793-bfde776b-4396-4adc-be04-deb1a61c1bd5.jpeg)|![erd5](https://user-images.githubusercontent.com/30483337/133754753-67218c00-8652-4763-ae73-4e77743f13ce.jpg)|


### FE에서 사용할 때
> src/main/resources/application-auth.yml
```java
app:
    oauth2:
        authorizedRedirectUris:
            - http://localhost:3000/oauth2/redirect
```
`authorizedRedirectUris` 값을 확인하고 포트를 3000번 or 자신이 사용하는 포트로 바꿉니다.