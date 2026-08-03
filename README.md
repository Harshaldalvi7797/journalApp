# JournalApp

Spring Boot REST API for journal entries, backed by MongoDB.

## Stack

- Java 17
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data MongoDB
- MongoDB (`journalDb` / `journal_entries`)

## What we completed

- Added `spring-boot-starter-data-mongodb` (version managed by Boot parent)
- Connected MongoDB with Boot 4 properties (`spring.mongodb.*`)
- Created layered flow: **Controller → Service → Repository**
- Mapped `JournalEntry` as a Mongo document with `ObjectId` as `@Id`
- Built CRUD APIs on `/journal/v2` using `MongoRepository`
- MongoDB auto-generates `ObjectId` on create (no `id` in POST body)
- Update loads the existing entry, changes fields, and saves the **same** document (no duplicate insert)

## Project structure

```
controller/
  HealthCheckController.java
  JournalEntryController.java      # v1 (in-memory HashMap)
  JournalEntryControllerV2.java    # v2 (MongoDB)
entity/
  JournalEntry.java
repository/
  JournalEntryRepository.java
services/
  JournalEntryServices.java
```

## Configuration

`src/main/resources/application.properties`:

```properties
spring.application.name=JournalApp
spring.mongodb.host=localhost
spring.mongodb.port=27017
spring.mongodb.database=journalDb
```

> Spring Boot 4 uses `spring.mongodb.*` (not `spring.data.mongodb.*`).

## Run

1. Start MongoDB on `localhost:27017`
2. Start the app:

```bash
./mvnw spring-boot:run
```

App runs at `http://localhost:8080`

## API (v2 — MongoDB)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/journal/v2` | Get all entries |
| POST | `/journal/v2` | Create entry |
| GET | `/journal/v2/id/{id}` | Get by ObjectId |
| PUT | `/journal/v2/id/{id}` | Update by ObjectId |
| DELETE | `/journal/v2/id/{id}` | Delete by ObjectId |

### Create (do not send `id`)

```json
POST /journal/v2
{
  "title": "hello",
  "content": "my first journal"
}
```

### Update

```json
PUT /journal/v2/id/{objectId}
{
  "title": "updated title",
  "content": "updated content"
}
```

## Notes

- Database/collection are created on the **first successful save**, not on app startup
- Keep `id` as `ObjectId` in entity, repository, and path variables
- For update: modify the found entry and call `saveEntry(old)`, not `saveEntry(newEntry)`
