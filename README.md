# Case-Management
CMSC495 Capstone project

This project is a case management system to help police personnel track and manage imates. 

[![Build Status](https://travis-ci.com/ddanielr/Case-Management.svg?branch=master)](https://travis-ci.com/ddanielr/Case-Management)

## Requirements

Have Docker Toolbox installed on a Windows 10 machine
Install the JDBC driver jar from postgres (https://jdbc.postgresql.org/download.html)

## Running the project

Before you run the program, start the Docker quickstart terminal and take note of the IP address the terminal is running on. 
The default address is 192.168.99.100, if you are running this on a windows system then this default address is already set in the application.

Once you have recorded this IP address, run the following command to set the proper IP of the database:
```
export DATABASE_HOST="<docker IP>"
```

Next, start the database by running the docker-compose command. This command will start up the postgres database container and host it on port 5432. Ensure you run this command from the Docker Quickstart Terminal

```
 docker-compose up -d
```

Finally, run the java application
```
java -jar target/UMUC495-Case-Management-1.0.jar
```

