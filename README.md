# RoboDog2

## Story

After tiny, friendly aliens came to our planet, they would love dogs, and Codecool would be more than happy to help bring them joy through RoboDogs!  
As it would be history by now, they would wonder how to teach them. What else to show off on Intergalactic Bro Meetings, then _handshakes_ from four-legged friends?  
And what about creating more of them in tinier but adorable forms? Did someone say puppies? Also, how to organize their data better than a database?  
Our extraterrestrial friends asked us to create a better hierarchy in the source code and make it possible to produce puppies and teach tricks to the dogs.  
Plenty of work to do let's jump in!



## What are you going to learn?

- use DAOs
- create and establish a connection to an H2 database with a Spring Boot application using JDBC
- unit- and integration tests

## Tasks

1. Create a `repository` in the `dao` layer to store `Dog` objects in memory, by implementing the `DogDAO` interface. Call the implementation `DogMemDao`. Use similar logic used by `DogStorage` in the old `Robodog` project. Generate separate id-s for the new dogs. Use a thread safe solution for the next id generation.
    - All interface methods of `DogDAO` are implemented.
    - When storing new dogs, separate id's are generated.
    - Id's are generated in a thread-safe manner.

2. Create a new `DogService` class in the `service` layer. Rearrange your logic from the `DogCreator` class from the `robodog` project here. The class should respectively contain a `createRandomDog()` method, to create dogs with random `age`, `name`, and `breed`. Later on, you should keep your service updated, to establish a bridge between the `DAO` and the `controller`.
    - The application has a separate `service` layer for business logic, including the `DogCreator` class.
    - The `DogService` class has a method `createRandomDog()`, responsible for creating dog instances with random properties, including name.
    - The `DogService` class has a method `createRandomDog()`, responsible for creating dog instances with random properties, including age.
    - The `DogService` class has a method `createRandomDog()`, responsible for creating dog instances with random properties, including breed.
    - The `DogService` can serve out requests and `DAO` operations.

3. Create a repository in the `dao` layer to store `Dog` objects in a database, by implementing the `DogDAO` interface. Call the implementation `DogJdbcDao`.
    - All interface methods of `DogDAO` are implemented.
    - The `DogJdbcDao` is interchangeable with `DogMemDao` as they implement the same interface.

4. Have a `controller` layer, where you create `DogController`. You should have endpoints for every DAO method, but the controller should __not__ directly call them. Make sure, all routes mapped in this controller start with `/dog` (e.g. `/dog/{dog_id}. Try to solve it without repetition. Add an additional `GET` endpoint for `/dog/random` for creating, persisting and retreiving a random `Dog`.
    - The `DogController` class is located within the controller layer.
    - There are endpoints for every DAO method.
    - DAO methods are not directly called from the controller.
    - All endpoints start with `/dog`.
    - The `/dog` route is not repeated in every request mapping method annotation.
    - The `/dog/random` `GET` endpoint creates a new random dog.

5. Get ready to be amazed! The first step to teach tricks to dogs is actually modeling a trick. A `Trick` should have an `id` and a `name`.
    - The `Trick` class is located in the `model` layer.
    - The `Trick` class has an `id` and a `name` fields, with getters and setters.

6. Create an interface called `TrickDAO` in the `dao` layer. The interface should contain methods for CRUD operations.
    - Interface is located within the `dao layer`.
    - Interface provides methods for creating, reading (all tricks, and single tricks by id), updating, and deleting tricks.

7. Implement the `TrickDAO` interface methods in the `TrickJdbcDao` repository class.
    - All interface methods of `TrickDAO` are implemented.

8. Create a `TrickService` class in the `service` layer. This class should be responsible for business logic related to tricks.
    - The `TrickService` class is located in the `service` layer.

9. Create a `TrickController` located in the `controller` layer. You should have endpoints for every DAO method, but the controller should __not__ directly call them. Make sure, all routes mapped in the controller start with `/trick`. (e.g. `/trick/id/{trick_id}`).
    - The `TrickController` class is located within the controller layer.
    - There are endpoints for every DAO method.
    - DAO methods are not directly called from the controller.
    - All endpoints start with `/trick`.
    - The `/trick` route is not repeated in every request mapping method annotation.

10. Gaining skill at certain things is not easy at all - but never impossible! If dogs start to learn a trick, their skill at the given trick improves. Based on this, a Skill needs to have an `id` (for persisting purposes), a `dogId`, a `trickId`, and a `level`.
    - The `Skill` class is located in the `model` layer.
    - The `Skill` class has an `id`, `dogId`, `trickId`, and `level` fields, with getters and setters.

11. Create an interface called `SkillDAO` in the `dao layer`. The interface should contain methods for CRUD operations.
    - Interface is located within the `dao layer`.
    - Interface provides methods for creating, reading (all skills, and single skills by id, updating, and deleting skills.
    - The interface should also contain methods to query all dogs with a trick id.
    - The interface should return with a `Optional<Skill>` for a dog id and a trick id.

12. Implement the `SkillDAO`interface methods in the `SkillJdbcDao` class.
    - All interface methods of `SkillDao` are implemented.

13. Create a `SkillService` class in the `service layer`. This class should be responsible for business logic related to skills.
    - The `SkillService` class is located in the `service layer`.

14. Create a `SkillController` located in the `controller` layer. You should have endpoints for every DAO method, but the controller should __not__ directly call them. Make sure, all routes mapped in the controller start with `/skill`. (e.g. `/skill/{skill_id}`).
    - The `SkillController` class is located within the controller layer.
    - There are endpoints for every DAO method.
    - DAO methods are not directly called from the controller.
    - All endpoints start with `/skill`.
    - The `/skill` route is not repeated in every request mapping.

15. As intended, skills should not be fixed! A growth mindset also works for dogs -actually, their trainers too-. By incrementing the level field, dogs should be able to improve their skills and level up. We should not be too strict with them, so let's say, that after 3 _"trials"_, they know a skill, and can not go any higher. By sending a `GET` request at the endpoint `/skill/name/{trick_name}/dog/{dogid}` one should be able to check their process by using the dog id and the trick's name. By sending a `PUT` request at the endpoint `/skill/name/{trick_name}/dog/{dogid}` one should be able to update their process by using the dog id and the trick's name.
    - Reading and updating skills by dogId and trickName are implemented on `SkillDAO`.
    - Reading and updating skills by dogId and trickName are implemented in `SkillJdbcDao`.
    - Reading and updating skills by dogId and trickName are implemented in `SkillService`.
    - Reading and updating skills by dogId and trickName are implemented in `SkillController`.
    - Skill level cannot be higher than 3.

16. Pedigree can be interpreted as a family tree for dogs. A `Pedigree` should contain an `id`, `momId`, `dadId`, and a `puppyId`.
    - The `Pedigree` class is located in the model layer.
    - The `Pedigree` class has `id`, `momId`, `dadId` and `puppyId` fields, with getters and setters.

17. Create an interface called `PedigreeDAO` in the `dao layer`. The interface should contain methods for CRUD operations.
    - Interface is located within the `dao layer`.
    - Interface provides methods for creating, reading (all pedigree, and single pedigrees by id, updating, and deleting pedigree.

18. Implement the `PedigreeDAO`interface methods in the `PedigreeJdbcDao` class.
    - All interface methods of `PedigreeDAO` are implemented.

19. Create a `PedigreeService` class in the `service layer`. This class should be responsible for business logic related to pedigrees.
    - The `PedigreeService` class is located in the `service layer`.

20. To put a period on wild guessing about dog's mix of breed, it would be so fun to know the whole family tree - grannies, puppies, everyone! Create an endpoint at `/dog/{dog_id}/pedigree` where you can get all family members of a dog given by their ID.
    - Reading all relations of a dog implemented on `PedigreeDAO`.
    - Reading all relations of a dog implemented in `PedigreeJdbcDao`.
    - Reading all relations of a dog implemented in `PedigreeService`.
    - Reading all relations of a dog implemented in `PedigreeController`.

21. There can be situations, where the 1.0 versioned RoboDogs don't have their pedigrees registered. Create an endpoint at `/dog/{dog_id}/pedigree` where using a POST request, anyone can register them.
    - Creating a pedigree for a dog implemented on `PedigreeDAO`.
    - Creating a pedigree for a dog implemented in `PedigreeJdbcDao`.
    - Creating a pedigree for a dog implemented in `PedigreeService`.
    - Creating a pedigree for a dog implemented in `PedigreeController`.

22. Finally, technology advanced, so we can adore little RoboDogs too! Create an endpoint at `/dog/puppy`, where anyone can implement their tiny four-legged friends by sending a GET request. For creating a puppy, we will need to get the parents. The request should contain the `momId` and the `dadId`. The puppy should come with their age at 0. The puppy's breed should be a random choice between their parent's breed.
    - Creating a puppy implemented on `PedigreeDAO`.
    - Creating a pedigree for a dog implemented in `PedigreeJdbcDao`.
    - Creating a pedigree for a dog implemented in `PedigreeService`.
    - Creating a pedigree for a dog implemented in `PedigreeController`.
    - When a puppy is created, its age is 0.
    - When a puppy is created, its breed is randomly chosen from its parents.

23. _Who does this puppy belong?_ Let's find out! Create endpoints at `/dog/{dog_id}/pedigree/mom` and `/dog/{dog_id}/pedigree/dad` to find out who their parents are.
    - Looking for a dog's parent by ID implemented on `PedigreeDAO`.
    - Looking for a dog's parent by ID implemented in `PedigreeJdbcDao`.
    - Looking for a dog's parent by ID implemented in `PedigreeService`.
    - Looking for a dog's parent by ID implemented in `PedigreeController`.

24. Create an endpoint at `/dog/{dog_id}/pedigree/siblings` to get all dogs who share any parents. You should list all siblings and half-siblings too.
    - Looking for a dog's siblings by ID implemented on `PedigreeDAO`.
    - Looking for a dog's siblings by ID implemented in `PedigreeJdbcDao`.
    - Looking for a dog's siblings by ID implemented in `PedigreeService`.
    - Looking for a dog's siblings by ID implemented in `PedigreeController`.
    - The list contains the dogs who share only one of their parents.
    - There is no duplication in the list.

25. To make sure everything is fine, test all methods in DogJdbcDao (without mocking).
    - Have test for creating dogs.
    - Have test for listing all dogs.
    - Have a test for getting a dog.
    - Have a test for updating a dog.
    - Have a test for deleting a dog.

26. Test getting all pedigrees by dog ID. Test getting mom dog by puppy ID. Test getting dad dog by puppy ID. Test getting all siblings.
    - Have test for getting all pedigrees by dog ID.
    - Have test for getting mom dog by dog's ID.
    - Have test for getting dad dog by dog's ID.
    - Have test for getting siblings by dog's ID.

27. Create a unit test for creating a new puppy.
    - Every class except for the service responsible for this logic is mocked.
    - Breed choice based on parents' breed is checked.
    - Age checked to equal 0.

## General requirements

None

## Hints

- JdbcTemplate requires a RowMapper<T> for matching data coming from the database into a type that can describe data in Java. Implementing this RowMapper interface can come in handy, also make the code cleaner.
- It is not well-advised to include the object's ID (used for Primary Key for example) in responses. Look for a way to hide them from the serialized JSON.
- The project comes with pre-written database schema and sample data, both written in SQL.
- The database can be checked or modified at `http://localhost:8080/h2-console`. The `datasource url` (or `JDBC URL`), `username` and the `password` (if any) necessary is set in the `application.properties`.
- You can check your endpoints documented by Swagger on `http://localhost:8080/swagger-ui.html`.

## Background materials

- [How to use Spring JdbcTemplate](https://yashodgayashan.medium.com/how-to-use-spring-jdbctemplate-dec9e4476eaa)
- <i class="far fa-camera"></i> [Spring JDBC Template Tutorial](https://youtube.com/watch?v=0uLqdBpYAVA)
- [How to handle query parameters in Spring](https://baeldung.com/spring-request-param)
- <i class="far fa-book-open"></i> [Data Access Object (DAO) pattern tutorial](https://tutorialspoint.com/design_pattern/data_access_object_pattern.htm)
- [Spring JdbcTemplate testing](https://baeldung.com/spring-jdbctemplate-testing)
- <i class="far fa-candy-cane"></i> [The difference between integration and unit tests](https://stackoverflow.com/questions/10752/what-is-the-difference-between-integration-and-unit-tests)

