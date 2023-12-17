
# Code test - Mortage plan - Backend

demo repo for a codetest to crosskey.


## Deployment

To deploy this project run the build script included if you get permission issues make sure you can run it "chmod +x"

```bash
  ./RunMe
```

### To run them individualy without docker container
```bash
  ng build --watch (To build and develop frontend)
  mvn spring-boot:run (To run backend)
```

The build proccess will first build the angular frontend and move the built project into the worktest (backend) folder. and then download all neeed deps into the container then the project itself.

Access it at http://localhost:8080


## Features

- a modern REST API for loan data
- Unit testing for most cases we needed for this demo
- a basic frontend in angular to make the proeccess easyer for development.

## What is missing
- Security (i would liked to have inplented better security for the front end and backend using token "JWT" to better enchance the comunication between frontend and backend)
- same goes for saving the data. it is now just using h2 database for development usage. for a real world program this would never leave the the test repo, we would of course use a a decent database like mariadb/postgres or mongo maybe totaly depepnds on the usecase of the program.
- server is also not running https i could have made self signed certificates and just have tomcat use that but i did not see the need for that for this demo. for a real world use we would use real legit signed certificcated on a secure stable web server like nginx.

- the frontend is there mostly for making the development easyer so the focus has not been on that so it may not be the nicest looking around but it a good job for what i needed it to.
- Not hosted on AWS for this the hole thing would be in a real setup probably have the frontend and backend in diffrent repos or a monorepo and use github actions to do all tests needed and build and finaly run with terraform or simular on AWS. my time was limited also my knowlage is limited when it comes to this so i did not attempt to do this. 

## More
- lets talk in person for more details and questions regarding the project.