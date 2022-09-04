# Authentication service
## How to run
```bash
$ docker compose up
$ ./gradlew bootRun
```

## Specification
Authentication service generates information about transactions.
Each transaction contains following information:
 - ID (integer)
 - Timestamp
 - Type (string)
 - Actor (string)
 - Transaction data (key-value map of strings)

The transactions must be collected by a new service.
The service should receive the data at HTTP interface, store them in SQL database and make them available via the HTTP interface.

Implement the service for CRUD (Create Update Delete) and search operations.
Suggest and design what the search operation should look like to be usable.

Implement it as a Spring application using MySQL database.
