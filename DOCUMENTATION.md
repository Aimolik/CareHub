
<h2>Running project locally</h2>

Before running the backend, there needs to be a database to connect to.

A local database container can be started with this command:

```bash
docker run -d -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=admin --name postgres postgres
```

Once it is running, start the CareHubApplication file.
