# Command line phone book application

Application can store and read data from a text file and database aswell.

## file format and Domain: 
```ID;Name Surname;phone number```

`ID` -> Auto generated sequence number
`Name Surname` --> Name and Surname
`phone number` --> sequence of numbers, you can define some format but it is not important

ex:
```
1;Keanu Reevs; 001222222222
2;Madonna; 001333333333
3;Will Smith; 001444444444
4;Johnny Depp; 001555555555
5;Lana Del Rey; 001666666666
```

Use object `Person`: 
```
class Person {
  private String firstName;
  private String lastName;
  private String phoneNumber;
}
```

## Logic
* Read data from file and write to file only once , when you start the application and when you close the application. 
* Keep data inside a Collection, select one according to your need. (List, Map, Set)
* use `stream` for searching, filtering and sorting operations
* Needed operations: 
```
1. Create person
2. Search person by Name and Surname
3. Search person by phone number
4. Edit person
5. Delete person
6. Exit
```
## Database connection
+ create database
  - ```sql
      create database phonebook;
    ```
+ grant privileges to user: 

  - ```sql 
      grant all privileges on phonebook.* to ph_user@'localhost' identified by 'ph_password'; 
    ```
+ switch to database
  - ```sql
      use phonebook;
    ```
+ create table
  - ```sql
      create table person(
        id int not null auto_increment primary key,
        first_name varchar(255),
        last_name varchar(255),
        phone varchar(255)
      )
    ```

### Package and Run
* package -- `mvn install`
* run -- `java -jar target/phonebook-1.0-SNAPSHOT.jar`
