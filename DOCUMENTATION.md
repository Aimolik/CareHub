
<h2>Running project locally</h2>

Prerequisites:
 - Docker
 - Java 17+
 - Npm
 - http-server

Before running the backend, there needs to be a database to connect to.

A local database container can be started with this command in the terminal:

```bash
docker run -d -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=admin --name postgres postgres
```

To connect to the database, use 'user' as user and 'admin' as password for authentication.
Once it is running, start the CareHubApplication file found in CareHubBackend/src/main/java/com/group2/CareHub/CareHubApplication.java. 
This application should automatically connect to the postgres container with the credentials.

To start the frontend, run this in the terminal at CareHubFrontend file:
This command can be installed using npm.
```bash
http-server -p 8081
```

The frontend should be accessible at localhost:8081
