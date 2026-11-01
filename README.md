# Drugstore Java Backend

A Java-based pharmacy management application built with Maven and JavaFX. The project models drug prescriptions, patient records, doctor access, adverse reactions, and interaction checking, with support for different persistence layers (JPA, JDBC, and mock data).

## Overview

This repository contains a desktop application for managing drugstore-related operations. It is organized around a layered architecture:

- Model layer for domain entities such as patients, doctors, drugs, prescriptions, and interactions
- DAO layer for data access abstraction
- Persistence layer for database connectivity
- Service layer for business logic
- View layer for the JavaFX interface
- Main entry point that launches the application

## Tech Stack

- Java
- Maven
- JavaFX
- Hibernate / JPA
- MariaDB JDBC driver
- JUnit 5

## Project Structure

```text
drugstore-java-backend/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ edu/jbussiere/bdeb/a10/
│  │  │     ├─ Main.java
│  │  │     ├─ control/
│  │  │     ├─ dao/
│  │  │     ├─ model/
│  │  │     ├─ persistence/
│  │  │     ├─ service/
│  │  │     └─ view/
│  │  └─ resources/
│  │     └─ config.properties
│  └─ test/
├─ sql/
│  ├─ tables.sql
│  └─ data.sql
├─ pom.xml
├─ LICENSE
├─ README
├─ README.md
└─ target/
```

## Main Components

- Main.java: application entry point
- view/: JavaFX user interfaces
- model/: core domain entities such as Drug, Patient, Prescription, Doctor, and DoseResult
- dao/: data access interfaces
- persistence/: persistence and DAOFactory setup
- service/: business logic and validation operations
- sql/: database schema and seed data

## Features

- Manage patient records
- Manage doctor and user access
- Handle drug information and prescriptions
- Detect or model drug interactions
- Store adverse drug reactions
- Support multiple data sources:
  - JDBC
  - JPA
  - Mock implementation

## Database Setup

The project includes SQL scripts in the `sql` directory:

- `sql/tables.sql` — database schema
- `sql/data.sql` — seed data

Before running the app, make sure your database is configured and the datasource is set correctly.

## Configuration

The app reads configuration from:

- `src/main/resources/config.properties`

The data source can be selected in `Main.java` by switching between:

```java
DAOFactory.useJPA();
DAOFactory.useJDBC();
DAOFactory.useMock();
```

Only one should remain active depending on the environment you want to test.

## Running the Project

From the project root:

```bash
mvn clean install
mvn javafx:run
```

If you are using a Java IDE, run the `Main` class directly after setting up the project dependencies.

## Requirements

- Java 26 (as configured in `pom.xml`)
- Maven
- A compatible database for the JDBC/JPA mode
- JavaFX runtime support

## License

This project is licensed under the terms of the included LICENSE file.

## Notes

This repository appears to be a university-style pharmacy management project used to demonstrate layered Java architecture with JavaFX and database access patterns.
