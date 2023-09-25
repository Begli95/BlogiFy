# BlogiFy

Welcome to BlogiFy! This is a simple blogging platform built using Java and Spring Boot.

## Description

BlogiFy allows users to create and publish blog posts on various topics. Users can register, log in, create, edit, and delete blog posts.

## Features

- User registration and authentication
- Create, read, update, and delete blog posts

## Prerequisites

To run this project, you need to have the following installed on your machine:

- Java 17
- MySQL server

## Database Configuration

Follow these steps to configure the database for the project:

1. Start your MySQL server.
2. Create a new database with the name `blogify_db` and port `3306

## Installation

To run this project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/blogify.git`
2. Navigate to the project directory: `cd blogify`
3. Open the `application.properties` file located in the `src/main/resources` directory.
4. Update the following properties with your database connection details:
   - `spring.datasource.url=jdbc:mysql://localhost:3306/blogify_db`
   - `spring.datasource.username=your-username`
   - `spring.datasource.password=your-password`
5. Save the changes to the `application.properties` file.
6. Build the project: `./gradlew build`
7. Run the application: `./gradlew bootRun`
8. Open your web browser and visit: `http://localhost:8080`

## Contact

If you have any questions or suggestions, feel free to contact me at     saparov-begli@mail.ru
