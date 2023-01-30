# Description 

A demo repository showcasing the capabilities of Spring Data MongoDB, including CRUD operations, custom queries, and integration with a MongoDB database. The project provides a practical and hands-on approach to learning how to use Spring Data MongoDB in a Java-based web application.

## 1. MongoDB for class

- In Jpa we use the `@Entity` annotation to tell Spring that this is a class that we want to persist in the database. 
- In MongoDB we use the `@Document` annotation.
- We can also specify the name of Document in the `@Document` annotation using the `collection` parameter => `@Document(collection = "users")`
- If we don't specify a name, the name of the class will be used.

## 2. MongoRepository

- We can use the `MongoRepository` interface to perform CRUD operations on our documents.
- `MongoRepository` interface extends the `ListCrudRepository` interface, also `PagingAndSortingRepository`.
- `MongoRepository` interface has the following methods :
    - `T save(T entity)`
    - `List<T> findAll()`
    - `Optional<T> findById(ID id)`
    - `void delete(T entity)`
    - `void deleteById(ID id)`
    - `boolean existsById(ID id)`

## 3. DBRef and Collection Relationships

- We can use the `@DBRef` annotation to reference other documents in the database, and create a **relationship** between them.
- We can use the `@DBRef` annotation in the following way:
    - `@DBRef`
    - `@DBRef(lazy = true)` => _Lazy loading_ means that the referenced document will be loaded only when it is accessed.
    - `@DBRef(lazy = false)` => _Eager loading_ means that the referenced document will be loaded when the parent document is loaded.
    - `@DBRef(lazy = true, db = "databaseName")` => we can specify the database name in which the referenced document is stored.
    - `@DBRef(lazy = true, db = "databaseName", collection = "collectionName")` 

## 4. Document queries

- One of the most common ways to query mongodb with spring data is by making use of the `@Query` annotation and criteria queries.
- To achieve that we can use ``MongoTemplate``.

### 4.1 MongoTemplate

- **MongoTemplate** is pre-configured high level mongodb database access utility which provides :
  - **advanced object mapping** functionality 
  - fluent **straightforward interface for interacting with DB**.

- Some features offered by mongoTemplate :

  * Support for automatic mapping of POJOs to MongoDB documents using **Jackson based object mapper**
  * Support bulk operations
  * Handle connection pool (connection pooling is a technique that improves the performance of a database by reusing a connection to the database instead of opening a new connection for each database call)
  * Support transaction

#### Examples :

##### 4.1.1 Search data using criteria queries

```java
public class SearchCustomerService {
  private final MongoTemplate mongoTemplate;

  public List<Customer> searchCustomerByName(String name) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    
    List<Customer> customers = mongoTemplate.find(query, Customer.class);
    return customers;
  }
}
````

* Search Customer By Name Starting With AND Salary Greater Than :

```java
public class SearchCustomerService {
  private final MongoTemplate mongoTemplate;
  
  public List<Customer> searchCustomerByNameStartingWithAndSalaryGreaterThan(String name, BigDecimal salary) {
      Query query = new Query();
      query.addCriteria(Criteria.where("name").regex("^" + name).and("salary").gt(salary));
      List<Customer> customers = mongoTemplate.find(query, Customer.class);
      return customers;
  }
}
```

* More search operators :
  - Less than : `Criteria.where("salary").lt(salary)`
  - Less than or equal to : `Criteria.where("salary").lte(salary)`
  - Greater than : `Criteria.where("salary").gt(salary)`
  - Greater than or equal to : `Criteria.where("salary").gte(salary)`
  - Not equal to : `Criteria.where("salary").ne(salary)`
  - In : `Criteria.where("salary").in(salary)`
  - Not in : `Criteria.where("salary").nin(salary)`
  - All : `Criteria.where("salary").all(salary)`
  - Size : `Criteria.where("salary").size(salary)`
  - Exists : `Criteria.where("salary").exists(salary)`

##### 4.1.2 Sorting data

```java
public class SortCustomerService {
  private final MongoTemplate mongoTemplate;

  public List<Customer> sortByField(String fieldName) {
    Query query = new Query();
    query.with(Sort.by(Sort.Direction.ASC, fieldName));
    List<Customer> customers = mongoTemplate.find(query, Customer.class);
    return customers;
  }
}
```

##### 4.1.2 Pagination and sorting

```java
public class SortCustomerService {
    private final MongoTemplate mongoTemplate;

    public List<Customer> sortAndPageByField(String fieldName, int page, int size) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, fieldName));
        query.with(pageable);
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }
}
```

### 4.2 Query Methods

- Mongodb provides auto-generated query methods for basic CRUD operations.

- ``List<Customer> findAllByName(String name)``
- ``List<Customer> findAllByNameAndSalary(String name, BigDecimal salary)``
- ``List<Customer> findAllByNameIgnoreCase(String name)`` -> ignore case while searching (exe : Ignore the name in parameter)
- ``List<Customer> findAllByNameAndSalaryGreaterThan(String name, BigDecimal salary)``
- ``List<Customer> findCustomerByHeightBetween(BigDecimal min, BigDecimal max)``
- ``List<Customer> findCustomerByNameOrderByAddresses(String name, Pageable pageable)``

#### 4.2.1 Query Methods with @Query

```java
public interface CustomerRepository extends MongoRepository<Customer, String> {
  @Query("{ 'name' : ?0 }")
  List<Customer> findCustomerByName(String name);
  
  @Query("{ 'name' : ?0, 'salary' : ?1 }")
  List<Customer> findCustomerByNameAndSalary(String name, BigDecimal salary);
  
  @Query("{ 'name' : ?0, 'salary' : { $gt : ?1 } }")
  List<Customer> findCustomerByNameAndSalaryGreaterThan(String name, BigDecimal salary);
}
```












































