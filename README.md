<a name="readme-top"></a>

[![LinkedIn][linkedin-shield]][linkedin-url]

<h3 align="center">Backend Application with Integration tests</h3>

  <p align="center">
    This project is a custom homework assigment that I recieved through an application.
    <br />
    <a href="https://github.com/szigetvarikati/backend"><strong>Explore the docs »</strong></a>
    <br />
    <br />
   

  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
         <li><a href="#built-with">Features</a></li>
            <li><a href="#built-with">Technologies used</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

HomeworkSound is a sound management system designed to handle various sound files efficiently. This backend component is built using Spring Boot and integrates with a PostgreSQL database to store sound-related data. It provides RESTful API endpoints for creating, retrieving, updating, and deleting sound entries.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Features

- **Create Sound:** Add new sound entries with details such as name, data, and extension.
- **Retrieve Sound:** Get information about specific sound entries or list all available sounds.
- **Update Sound:** Modify the details of existing sound entries.
- **Delete Sound:** Remove sound entries from the system.

### Technologies Used

- **Spring Boot:** A Java-based framework for building robust and scalable applications.
- **PostgreSQL:** A powerful open-source relational database system.
- **JUnit:** A testing framework for Java to ensure code reliability.
- **MockMvc:** A testing framework for Spring MVC applications.
- **ObjectMapper:** A library for converting Java objects to JSON and vice versa.


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

Follow this quide to run and try the application.

### Installation

1. Clone the repo
   ```sh
   $ git clone https://github.com/szigetvarikat/backend.git
   $ cd backend
   ```
2. Set up your PostgreSQL database and update application.properties with the database connection details.
   
3. Configure environment variables.
     
4. Build the project: `mvn clean install`

   
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## API Endpoints

- **List All Sounds:** `POST /api/sounds/all`
- **Get Sound by ID:** `POST /api/sounds/get/{id}`
- **Create New Sound:** `POST /api/sounds/create`
- **Update Sound by ID:** `POST /api/sounds/update/{id}`
- **Delete Sound by ID:** `POST /api/sounds/delete/{id}`

## Testing

The project includes unit tests and integration tests to ensure the reliability of the backend functionalities. You can run the tests using Maven:

```bash
mvn verify
```
<p align="center">
  <img src="https://imgur.com/X24k9Gj.png">
  <img src="https://imgur.com/CoU16Ek.png">
</p>

<!-- CONTACT -->
## Contact

Katalin Szigetvári - szigetvarikati@gmail.com

Project Link: https://github.com/szigetvarikati/backend

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/szigetvarikati/backend.svg?style=for-the-badge
[contributors-url]: https://github.com/szigetvarikati/backend/graphs/contributors
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/katalin-szigetvári-9829519a
[product-main]: https://imgur.com/a/jEvI3mU
