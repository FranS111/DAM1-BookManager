## Features

### Author Management
- Add new authors to the database.
- Retrieve author information by name.
- Modify existing author details.
- Remove authors from the database.
- List all authors stored in the database.

### Book Management
- Add new books to the database.
- Retrieve book information by title.
- Modify existing book details.
- Remove books from the database.
- List all books stored in the database.
- Fetch distinct book genres.

## Technology Stack

- **Programming Language**: Java
- **Database**: MySQL
- **Libraries**: 
  - JDBC for database connectivity
  - `java.sql` for managing SQL queries and statements

## Project Structure
- **`controller/`**: Contains logic for interacting with the database for authors and books.
- **`dao/`**: Manages the connection to the MySQL database.
- **`model/`**: Defines the data structure for `Author` and `Book` entities.

## Database Schema

### Authors Table

| Field          | Type       | Description              |
|----------------|------------|--------------------------|
| `id_Author`    | INT        | Primary key, unique ID   |
| `name_auth`    | VARCHAR    | Author's first name      |
| `surname_auth` | VARCHAR    | Author's last name       |
| `country`      | VARCHAR    | Author's country         |

### Books Table

| Field         | Type       | Description                     |
|---------------|------------|---------------------------------|
| `id_Book`     | INT        | Primary key, unique ID          |
| `isbn`        | BIGINT     | Book's ISBN number             |
| `title`       | VARCHAR    | Book's title                   |
| `genre`       | VARCHAR    | Book's genre                   |
| `pages`       | INT        | Number of pages                |
| `id_Author`   | INT        | Foreign key referencing `Authors`|
