# Workout Logger

A RESTful backend application built with **Java and Spring Boot** for creating, managing, and tracking workouts. The application provides REST API endpoints for managing workouts, exercises, sets, repetitions, weight, and exercise progress.

The application uses **PostgreSQL** for persistent data storage, **Spring Data JPA/Hibernate** for database access, and **Docker** for containerization. The application is deployed using **Render**.

## Features 🚀

* Create, retrieve, update, and delete workouts
* Create, retrieve, update, and delete exercises
* Add exercises to workouts
* Remove exercises from workouts
* Maintain exercise order within a workout
* Add sets with repetitions and weight
* Remove sets from exercises
* Search workouts by date range
* Search exercises by name
* Filter exercises by category
* Track exercise progress over time
* Calculate maximum weight and repetitions at maximum weight
* Calculate total exercise volume
* Pagination for workout and exercise results
* Request validation using Jakarta Validation
* Resource-not-found and invalid-request handling
* PostgreSQL database persistence
* Multi-stage Docker build
* Docker Compose development environment
* Deployed application accessible through a REST API

## How It Works 🧠

The application follows a layered architecture that separates HTTP requests, business logic, and database operations.

```text
Client
  │
  │ HTTP Request
  ↓
Controller Layer
  │
  ↓
Service Layer
  │
  ↓
Repository Layer
  │
  ↓
PostgreSQL Database
```

### Controller Layer

The controllers define the REST API endpoints and receive HTTP requests from clients.

The application contains controllers for:

* Workouts
* Exercises
* Workout exercises
* Set entries

Controllers pass requests to the appropriate service rather than directly interacting with the database.

### Service Layer

The service layer contains the application's business logic.

For example, when adding an exercise to a workout, the service verifies that both the workout and exercise exist and prevents duplicate exercise ordering within the workout.

The service layer also handles validation such as checking that a workout has a date and that an exercise has a name before it is created.

### Repository Layer

Repositories are responsible for interacting with the PostgreSQL database through Spring Data JPA.

The service classes use repository methods for operations such as retrieving records, searching by fields, checking whether relationships already exist, and saving or deleting entities.

## Data Model 🗃️

The application is organized around four primary entities:

```text
Workout
   │
   │ 1-to-many
   ↓
WorkoutExercise
   │
   ├──────────────→ Exercise
   │
   │ 1-to-many
   ↓
SetEntry
```

### Workout

Represents an individual workout session.

A workout contains information such as:

* Date
* Name
* Notes
* Exercises performed

### Exercise

Represents an exercise that can be included in workouts.

Exercises contain information such as:

* Name
* Category
* Equipment

### WorkoutExercise

Represents an exercise being performed as part of a specific workout.

It connects a `Workout` and an `Exercise` and stores the exercise's position within the workout.

The application retrieves workout exercises according to their `orderIndex`.

### SetEntry

Represents an individual set performed for a workout exercise.

Each set stores:

* Set number
* Repetitions
* Weight

The service prevents duplicate set numbers for the same workout exercise and rounds weight values to two decimal places.

## REST API 🔌

### Workouts

| Method   | Endpoint               | Description                   |
| -------- | ---------------------- | ----------------------------- |
| `GET`    | `/api/workouts`        | Retrieve workouts             |
| `GET`    | `/api/workouts/{id}`   | Retrieve a workout by ID      |
| `POST`   | `/api/workouts`        | Create a workout              |
| `PUT`    | `/api/workouts/{id}`   | Update a workout              |
| `DELETE` | `/api/workouts/{id}`   | Delete a workout              |
| `GET`    | `/api/workouts/search` | Search workouts by date range |

Workout retrieval supports pagination, while the search endpoint retrieves workouts between two specified dates.

### Exercises

| Method   | Endpoint                       | Description                  |
| -------- | ------------------------------ | ---------------------------- |
| `GET`    | `/api/exercises`               | Retrieve exercises           |
| `GET`    | `/api/exercises/{id}`          | Retrieve an exercise by ID   |
| `POST`   | `/api/exercises`               | Create an exercise           |
| `PUT`    | `/api/exercises/{id}`          | Update an exercise           |
| `DELETE` | `/api/exercises/{id}`          | Delete an exercise           |
| `GET`    | `/api/exercises/search`        | Search exercises by name     |
| `GET`    | `/api/exercises/filter`        | Filter exercises by category |
| `GET`    | `/api/exercises/{id}/progress` | Retrieve exercise progress   |

Exercise retrieval supports pagination. Exercise searches use case-insensitive matching for names and categories.

### Workout Exercises

| Method   | Endpoint                                                  | Description                       |
| -------- | --------------------------------------------------------- | --------------------------------- |
| `GET`    | `/api/workouts/{workoutId}/exercises`                     | Retrieve exercises in a workout   |
| `POST`   | `/api/workouts/{workoutId}/exercises`                     | Add an exercise to a workout      |
| `DELETE` | `/api/workouts/{workoutId}/exercises/{workoutExerciseId}` | Remove an exercise from a workout |

When an exercise is added, the API accepts an exercise ID and an order index. The service verifies that the referenced workout and exercise exist before creating the relationship.

### Sets

| Method   | Endpoint                                               | Description   |
| -------- | ------------------------------------------------------ | ------------- |
| `GET`    | `/api/workout-exercises/{workoutExerciseId}/sets`      | Retrieve sets |
| `POST`   | `/api/workout-exercises/{workoutExerciseId}/sets`      | Add a set     |
| `DELETE` | `/api/workout-exercises/{workoutExerciseId}/sets/{id}` | Remove a set  |

The API accepts the set number, repetitions, and weight when creating a set.

## Exercise Progress 📈

The application includes an exercise progress endpoint that analyzes previous workout data.

For each workout containing a selected exercise, the service calculates:

* Maximum weight used
* Repetitions performed at the maximum weight
* Total volume

Total volume is calculated as:

```text
Weight × Repetitions
```

The progress service iterates through the recorded sets and builds an `ExerciseProgressDto` containing the workout ID, workout date, maximum weight, repetitions at maximum weight, and total volume.

This allows the API to provide historical performance information for individual exercises.

## Example Workflow 🏋️

A typical workout can be created and populated through the API using the following workflow:

```text
Create Exercise
      ↓
Create Workout
      ↓
Add Exercise to Workout
      ↓
Add Sets
      ↓
Record Reps + Weight
      ↓
Retrieve Workout
      ↓
Track Exercise Progress
```

For example:

```text
Push Day
│
├── Bench Press
│   ├── Set 1: 10 reps × 135 lb
│   ├── Set 2: 8 reps × 145 lb
│   └── Set 3: 6 reps × 155 lb
│
└── Shoulder Press
    ├── Set 1: 10 reps × 50 lb
    └── Set 2: 8 reps × 55 lb
```

## Validation & Error Handling ⚠️

The application uses validation and service-level checks to prevent invalid data and invalid relationships.

Examples include:

* Workouts require a date.
* Exercises require a name when created.
* Workout exercises must reference existing workouts and exercises.
* Duplicate exercise order indexes are prevented within a workout.
* Duplicate set numbers are prevented within a workout exercise.
* Set deletion verifies that the set belongs to the specified workout exercise.
* Missing resources result in a `ResourceNotFoundException`.

For example, before adding an exercise to a workout, the service checks that both referenced resources exist.

The controllers also use Jakarta Validation for workout and exercise request bodies.

## Pagination 📄

Workout and exercise collection endpoints support Spring Data pagination through `Pageable`.

This allows clients to request a subset of results instead of retrieving every record at once.

## Docker 🐳

The application uses a **multi-stage Docker build**.

### Build Stage

The first stage uses Maven with Java 21 to compile the Spring Boot application and create the executable JAR.

```text
Maven + Java 21
      ↓
Compile Application
      ↓
Create Spring Boot JAR
```

### Runtime Stage

The second stage uses Eclipse Temurin Java 21 to run the application.

Only the generated JAR is copied from the build stage into the runtime image.

```text
Build Container
      ↓
Spring Boot JAR
      ↓
Runtime Container
      ↓
Application on Port 8080
```

This separates the build environment from the runtime environment and avoids carrying the Maven build environment into the final application container.

## Docker Compose

Docker Compose is used to coordinate the application and PostgreSQL database during local development.

```text
┌─────────────────────┐
│   Spring Boot App   │
│       Port 8080     │
└──────────┬──────────┘
           │
           │ JDBC
           ↓
┌─────────────────────┐
│     PostgreSQL      │
│       Port 5432     │
└─────────────────────┘
```

## Running Locally 💻

### Prerequisites

* Java 21
* Maven
* Docker
* Docker Compose

### Clone the Repository

```bash
git clone https://github.com/blakeu-dev/workout-logger.git
cd workout-logger
```

### Run with Docker Compose

Configure the required environment variables in a `.env` file.

Then run:

```bash
docker compose up --build
```

The application runs on:

```text
http://localhost:8080
```

### Stop the Application

```bash
docker compose down
```

## Testing 🧪

The REST API can be tested using **Postman** or another HTTP client.

Example request:

```http
GET /api/workouts
```

The API returns workout data stored in the PostgreSQL database.

Additional endpoints can be tested using the REST API routes documented above.

## Technologies Used 🛠️

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Jakarta Validation**
* **Maven**
* **Docker**
* **Docker Compose**
* **Postman**
* **Render**

## Deployment ☁️

The application is deployed using **Render**.

Docker packages the Spring Boot application and its runtime environment, allowing the application to be deployed consistently outside the local development environment.

## Future Improvements 🔮

* Add user authentication and authorization
* Build a frontend interface
* Add workout history visualization
* Add personal record tracking
* Add additional progress analytics
* Add automated unit and integration tests
* Improve centralized API error handling
* Add Swagger/OpenAPI documentation
* Add CI/CD automation
* Add additional workout statistics

## 📄 License

MIT License
