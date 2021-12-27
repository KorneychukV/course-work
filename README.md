# First start
docker-compose up

# Keycloak
open page: http://localhost:8082/ (login: admin, pass: admin)
configure realm

# Server app
open in terminal dir "prof"
run command:  ./mvnw compile quarkus:dev

# Client app
open in terminal dir "from-to-education"
run command: npm install
run command: ng serve

# Launch app
open page: http://localhost:4200/
