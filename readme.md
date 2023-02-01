# IdeationStorm

## Requirements

For building and running the application you need:

- [JDK 19](https://www.oracle.com/java/technologies/downloads/#java19)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/)

## Running the application locally

From the root directory, run the following commands

first, start up docker (If you are not on linux, make sure the Docker Desktop is running)

```shell
docker compose up -d
```
This will start up a container with mysql, Make sure port 3306 is open, it's possible that docker will run with something else running on port 3306, but the app will not run, so be sure to close anything running on port 3306.
If you have MySql installed and would like to use that instead, you can, although Docker is still required for the tests.
&nbsp;

&nbsp;

Once the docker container is running you can start the application by running the following command in the root directory.
This command will install the Maven depenancy and start the server.

```shell
mvn spring-boot:run
```

If you get to this point and the Docker conatainer is running, but the app won't connect to the database, try removing the following line from the `application.properties` file.
comment out `spring.datasource.password=password`

&nbsp;

&nbsp;

The second way requires an IDE like intelij to run and would be ran by hitting the play button/ execute the `main` method in the `com.ideationstorm.com.ideationstorm.ideationstormApplication` class from your IDE.  
Start the Docker Container by running `docker compose up` in the `ideationStorm-spring/src/main/docker` folder

The app will be open on `http://localhost:8080`

---

### Project Structure

```bash
ideationStorm-spring
├───postman
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com.ideationstorm.com.ideationstorm
│   │   │                   ├───auth
│   │   │                   ├───category
│   │   │                   ├───config
│   │   │                   ├───language
│   │   │                   ├───project
│   │   │                   └───user
│   │   └───resources
│   │       └───db.migrations
│   └───test
│       ├───java
│       │   └───com.ideationstorm.com.ideationstorm
│       │                   ├───auth
│       │                   ├───category
│       │                   ├───language
│       │                   ├───project
│       │                   └───user
│       └───resources
└───target
```

Notable location:

- `postman` folder contains the postman collection
- `com.ideationstorm.com.ideationstorm` contains the main application and the code for main application
- `resources` contains the `application.properties` file which can be thought of as a `.env` file.
- `resources/db.migration` contains the various migration scripts that flyway will run. The scripts will be ran in order.
- `test` all tests are created in the test folder.
- `target` Auto generated folder

---

## Testing

Tests can be ran by using the maven command

```shell
mvn test
```

Or by using an IDE and right-clicking the `java` folder that is nested under the `test` folder and selecting `run all tests`

---

## building

Run the following command

```shell
mvn package
```

The jar will be placed in the target folder.
If you want a `war` folder, you can add the following line to the `pom.xml` file: `<packaging>war</packaging>`

---

## Trouble Shooting

### **Error:** `no plugin found for prefix 'spring-boot' in current project an in the plugin group....`

There are two possible causes for this error, you either miss spelt `mvn spring-boot:run` or you are not running the command in the directory that contains the `pom.xml`

### **Error:** `mvn command not recognized`

Maven is not installed and needs to be in order to run this project. If you are using intelij you can run the maven commands by using the Maven side panel.

### **Error:** `Exception in thread "main" java.lang.UnsupportedClassVersionError: myProject has been compiled by a more recent version of the Java Runtime (class file version 63.0), this version of the Java Runtime only recognizes class file versions up to 52.0`

This project uses Java SE 19, and requires that JRE to be installed. If you are getting this error, you most likely have the wrong JRE selected in your PATH
This stack overflow should prove to be helpful in resolving this error https://stackoverflow.com/questions/10382929/how-to-fix-java-lang-unsupportedclassversionerror-unsupported-major-minor-versi

### **Error:** port 8080 in use

The ports are statically assigned, to fix the port in use problem you can do one of two things.  
Kill whatever is running on port 8080, the simplest way is by running

```shell
npx kill-port 8080
```

Or you can change what port the program runs on, this can be changed in the application.properties file located at `ideationStorm-spring/src/main/resources`

### **Error:** `Could not create connection to database server. Attempted to reconnect 3 times. Giving up.`

**Possible problems:** The database is not connected correctly. This application expects the database to be on port 3306.  
Did docker start up correctly?  
Is port 3306 currently in use by another program?  
**Possible solutions:**  
Kill whatever is running on port 3306. Note: you should identify what program is using the port before running the following command.

```shell
npx kill-port 3306
```

Rerun docker compose in the `ideationStorm-spring/src/main/docker` folder

```shell
docker compose up
```

Alternatively you can change the port that docker uses, by updating the `docker-compose` and the `application.properties` files.

---

### Error: starting Tomcat context

**Possible problems:**  
This is a very generic error and requires looking deeper into the stack trace. The most common instance of this when first starting a problem is database migration errors.  
The following error gives us a hint into what happened, although this particular example is contrived (I deleted the first migration script from the database to make this error happen)  
`Error creating bean with name 'flywayInitializer' defined in class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Validate failed: Migrations have failed validation  
Detected resolved migration not applied to database: 1.`  
**Possible solutions:**  
Background: Every time that the application runs, flyway checks the flyway_schema_history table in the database against the current db migrations folder located at `src/main/resources/db.migrations/`  
If the files have changes or migrations failed, then you'll need to reset the database. The easiest way to reset the database is deleting the docker container and start it up again, alternatively you can delete all the tables in the database.

In either instance, before running docker compose, it is helpful to reset flyway by running the following command:

```shell
mvn flyway:repair
```

This command will most likely return errors in the console, but it worked correctly in resetting flyway, you can now restart your docker and run the application again.
