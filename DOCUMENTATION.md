
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
Once it is running, start the CareHubApplication file.

To start the frontend, run this in the terminal at CareHubFrontend file:
```bash
http-server -p 8181
```

The frontend should be accessible at localhost:8081
