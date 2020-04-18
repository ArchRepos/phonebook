package com.devxschool;

import com.devxschool.models.Person;
import com.devxschool.services.PhoneBookService;

import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filePath = "myrecords.txt";
        PhoneBookService phoneBookService = new PhoneBookService(filePath);

        while (true){
            drawInterface();
            String command = input.nextLine();

            switch (Integer.parseInt(command)) {
                case 1:
                    System.out.println("FirstName please");
                    String firstName = input.nextLine();
                    System.out.println("LastName please");
                    String lastName = input.nextLine();
                    System.out.println("phone number please");
                    String phoneNumber = input.nextLine();
                    Person p = new Person();
                    p.setID(null);
                    p.setFirstName(firstName);
                    p.setLastName(lastName);
                    p.setPhoneNumber(phoneNumber);
                    phoneBookService.save(p);
                    break;
                case 2:
                    long count = phoneBookService.count();
                    if (count == 0L)
                        System.out.println("There are 0 records");
                    else {
                        List<Person> personList = phoneBookService.getAll();
                        personList.stream().forEach( person -> {
                            System.out.println(
                                    "ID :" + person.getID()+
                                    "\nFirstName: "+ person.getFirstName()+
                                    "\nLastName: "+ person.getLastName()+
                                    "\nPhoneNumber: " + person.getPhoneNumber());
                        });
                    }
                    break;
                case 3:
                    System.out.println("Please write initials of Name or Surname");
                    String filter = input.nextLine();
                    List<Person> personList1 = phoneBookService.searchByFilter(filter);
                    System.out.println("There are "+personList1.size()+" of records Found!");
                    personList1.stream().forEach( person -> {
                        System.out.println(
                                "ID :" + person.getID()+
                                        "\nFirstName: "+ person.getFirstName()+
                                        "\nLastName: "+ person.getLastName()+
                                        "\nPhoneNumber: " + person.getPhoneNumber());
                    });
                    break;
                case 4:
                    System.out.println("Please input and ID delete");
                    String id = input.nextLine();
                    phoneBookService.delete(id);
                    break;
                case 5:
                    phoneBookService.flushAllRecordsToFile();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }

    public static void drawInterface() {
        System.out.println("PhoneBookApp v0.0.1");
        System.out.println("1. Create a Record");
        System.out.println("2. List all Records");
        System.out.println("3. Search by Filter");
        System.out.println("4. Delete a Record");
        System.out.println("5. Save and Exit.");
    }
}
