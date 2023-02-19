# movie-api
### Техническое задание
#### Описание:
REST приложение для хранения информации о фильмах.  
Язык: Kotlin или Java  
Framework: Spring boot  
База данных: H2 in memory  
Протокол: HTTP  
Формат обмена: JSON  
Модель данных:
- Название – text
- Описание – text
- Тип - text
- Жанр – text
- Дата выхода – date    

Тип должен быть - Полнометражный, короткометражный или сериал
#### Обязательные требования:
Эндпоинты для добавления:
- Одного фильма
- Нескольких фильмов

Эндпоинты для получения фильмов:
- По названию
- По типу
- По году выхода

Подключение actuator (`/actuator/health`)  
Путь до всех эндпоинтов должен начинаться с `/movie-api/`  
Централизованная обработка ошибок (например, `@ExceptionHandler`)  
База должна инициализироваться при запуске приложения (Миграция БД)  
Логирование событий приложения (например, успешное сохранение данных или попытка чтения)  
Логи приложения должны выводиться в консоль и записываться в файл  
Генерация документации в формате OpenAPI, желательно использовать springdoc  
Jar должен содержать все зависимости (fat jar)  
Приложение должно запускаться командой `java -jar`  
Добавить примеры запросов (желательно .http для Idea(HTTP Client), можно  Curl)  
Тесты  
Валидация входящих данных  
Реализация логирования через AOP (например, через аннотации)  
Пагинация  
Заполнение таблиц при запуске приложения   
Подробная документация OpenAPI (например, описание схем с примерами, эндпоинтов и т.п.)  
Подключение swagger-ui  
### Примеры запросов
Получить все фильмы с 'Sherlock' в названии:
```
curl -X 'GET' \
  'http://localhost:8080/movie-api?title=Sherlock' \
  -H 'accept: application/json'
```
Получить все полнометражные фильмы:
```
curl -X 'GET' \
  'http://localhost:8080/movie-api?type=full-length' \
  -H 'accept: application/json'
```
Получить все фильмы, вышедшие в 1989-м году:
```
curl -X 'GET' \
  'http://localhost:8080/movie-api?year=1989' \
  -H 'accept: application/json'
```
Поиск по названию, типу и году выхода:
```
curl -X 'GET' \
  'http://localhost:8080/movie-api?title=Sherlock&type=full-length&year=1989' \
  -H 'accept: application/json'
```
Пример добавления фильмов:
```
curl -X 'POST' \
  'http://localhost:8080/movie-api' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "title": "Forrest Gump",
    "description": "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.",
    "type": "full-length",
    "genre": "Comedy",
    "releaseDate": "1994-07-06"
  }
]'
```
Подробная документация OpenAPI (описание схем с примерами, эндпоинтов и т.п.):
```
http://localhost:8080/swagger-ui.html
```
### Сборка и запуск
Сборка проекта с помощью Maven:
```
mvn clean package
```
Запуск приложения:
```
java -jar target/movie-api-0.0.1-SNAPSHOT.jar
```
Логи доступны в файле `logs/spring-boot-logger.log`  
Миграция БД реализована библиотекой Flyway в файле `src/main/resources/db/migration/V1_0__CREATE_MOVIE_SCHEMA.sql`  
Заполнение таблиц при запуске производится из файла `src/main/resources/data.sql`