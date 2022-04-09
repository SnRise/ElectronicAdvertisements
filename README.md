# ElectronicAdvertisements
Spring boot + graphQL ElectronicAdvertisements service

Сервис позволяет создавать/просматривать/удалять электронные объявления о товарах/услугах, фильтровать их по категории/региону/создателю

Реализована регистрация пользователя с помощью JWT-токена


Как запускать:
1. Запускаем локально PostgreSql, создаем пользователя и базу, меняем в application.properties название БД и логин/пароль от базы
2. Собираем проект с помощью ./mvnw package из корня
3. Запускаем проект с помощью ./mvnw spring-boot:run
4. Тестовые данные и скрипты для создания таблиц находятся в src/main/resources/postgresql, заполняем базу
5. http://localhost:8081/graphiql - UI для отправки запросов graphQL
6. Схема запросов находится в src/main/resources/graphql

<h2>Примеры запросов:</h2>

```
mutation {
   createUser(email: "<email!>", name: "<name!>", password: "<password!>") {    // Создание нового пользователя
      id
   }
   
   login(email: "<email!>", password: "<password!>") {   // Вхождение в учетную запись
      token    // Токен, с помощью которого можно отправлять http запросы с авторизацией
   }
   
   logout   // Выход из учетной записи
   
   createAdvertisement(categoryId: <categoryId!>, regionId: <regionId!>, price: <price.00!>, description: "<description!>") {   // Создание нового объявления, доступно только авторизованным пользователям
      id
   }
   
   deleteAdvertisement(advertisementId: <advertisementId>)    // Удаление объявления, доступно только владельцам объявления
   
   createCategory(name: "<name!>") {   // Создание новой категории, доступно только админу
      id
   }
   
   createRegion(name: "<name!>") {   // Создание нового региона, доступно только админу 
      id
   }
}

query {
    advertisements(limit: <limit>, offset: <offset>) {
       ...
    }
    advertisementsByUserId(userId: <ID!>) {
       ...
    }
    advertisementsByRegionId(regionId: <ID!>) {
       ...
    }
    advertisementsByCategoryId(categoryId: ID!) {
       ...
    }
}
```

В тестовых данных содержится несколько регионов, категорий и объявлений, а также учетные записи обычного пользователя и админа.

Логин/пароль админа: admin@example.com / admin


Логин/пароль обычного пользователя: testuser@gmail.com / test

