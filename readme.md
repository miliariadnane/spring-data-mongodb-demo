
## MongoDB for class

- In Jpa we use the `@Entity` annotation to tell Spring that this is a class that we want to persist in the database. 
- In MongoDB we use the `@Document` annotation.
- We can also specify the name of Document in the `@Document` annotation using the `collection` parameter => `@Document(collection = "users")`
- If we don't specify a name, the name of the class will be used.

### MongoRepository

- We can use the `MongoRepository` interface to perform CRUD operations on our documents.
- `MongoRepository` interface extends the `ListCrudRepository` interface, also `PagingAndSortingRepository`.
- `MongoRepository` interface has the following methods:
    - `T save(T entity)`
    - `List<T> findAll()`
    - `Optional<T> findById(ID id)`
    - `void delete(T entity)`
    - `void deleteById(ID id)`
    - `boolean existsById(ID id)`





















































