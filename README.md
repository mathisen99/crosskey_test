
# Code test - Mortage plan - Backend

demo repo for a codetest to crosskey.


## Deployment

To deploy this project run the build script included if you get permission issues make sure you can run it "chmod +x"

```bash
  ./RunMe
```

### Running Components Individually (Without Docker Container)
```bash
ng build --watch  # Build and develop the frontend
mvn spring-boot:run  # Run the backend
```

The build process will first build the Angular frontend and move the built project into the 'worktest' (backend) folder. Afterward, it will download all necessary dependencies into the container.



## Features

- A modern REST API for loan data.
- Unit testing for most use cases required for this demo.
- A basic Angular frontend to facilitate development.

## What's Missing
While this demo showcases various functionalities, some aspects are missing:

- Security: Implement improved security measures, such as JWT token-based authentication, to enhance communication between the frontend and backend.
- Data Storage: Currently, H2 database is used for development purposes. For a real-world application, it is recommended to use a more robust database like MariaDB, PostgreSQL, or MongoDB, depending on the specific use case.
- HTTPS: The server does not run over HTTPS in this demo. For production use, it's essential to use legitimate SSL certificates and a secure web server like Nginx.
- The frontend's primary purpose is to facilitate development, so it may not have the most polished appearance, but it serves its intended function effectively.


## More
- lets talk in person for more details and questions regarding the project.