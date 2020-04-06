# Command line phone book application

Application will store and read data from a text file. 
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
