
# Spring Boot CRUD Operations with JPA

- This project demonstrates basic CRUD (Create, Read, Update, Delete) operations using Spring Boot with JPA (Java Persistence API). 
- I have created a survey in which there is an array of questions.



## Technologies Used

- **Spring Boot:** For building the application.
- **Spring Data JPA:** For data access and persistence.
- **H2 Database:** In-memory database for storing data during development.
- **Maven:** For project management and dependency resolution.


## Getting Started

- Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

 **Prerequisites**
- Java Development Kit (JDK) installed on your machine.
- Maven installed on your machine.

**Installing**

 - Clone the repository to your local machine:

```bash
  git clone https://github.com/KSH5856/JPA-CRUD.git
```

**Running the Application**
- Build the project using Maven:
```bash
  mvn clean install 
```
- Run the application:
```bash
  mvn spring-boot:run
```

- The application should now be running on http://localhost:8080.
## API Endpoints




 - GET /surveys : Retrieve all surveys

-  GET /surveys/{SurveyID} : Get survey By ID

 - GET /surveys/{SurveyID}/questions : Retrieve all questions in a survey

 - GET /surveys/Survey1/questions/question1 : Retrieve question by ID.

 - POST /surveys/post : Post survey 

 - POST /surveys/Survey1/questions : Post question by ID in survey

 - DELETE /surveys/Survey1/questions/questions1 : DELETE question by ID in survey

 - PUT /surveys/Survey1/questions/Question2 : Update question by ID in survey




## Testing

- You can test the API using tools like Postman or by writing JUnit tests



## Author

- Kashish Gupta

