The essence of this project is to use an API from a website that provides information about various tenders and their details. The goal is to retrieve, process, and store this data in a database, which can, for example, be deployed using a Docker container.
The system consists of three main components:
1. Database Layer – Stores the processed tender data and supports CRUD operations.
2. Backend (Controller Module) – Acts as an intermediary between the database and other components, handling data retrieval, modifcation, deletion, and addition.
3. Frontend Module – Communicates with the backend to fetch and display the tender data on a web page, providing a user-friendly interface.
Tech Stack: Java, Spring Boot (Web, JPA, Web Services), Hibernate, MariaDB/MySQL, Thymeleaf, Docker, Selenium, JUnit, Mockito, REST Assured.
