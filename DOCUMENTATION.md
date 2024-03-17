
<h2>Running project locally</h2>

Before running the backend, there needs to be a database to connect to.

A local database container can be started with this command:

```bash
docker run -d -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=admin --name postgres postgres
```

To connect to the databse, use 'user' as user and 'admin' as password for authentication.
Once it is running, start the CareHubApplication file.
