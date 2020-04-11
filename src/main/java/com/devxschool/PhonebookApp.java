package com.devxschool;

import com.devxschool.models.Person;
import com.devxschool.repositories.FileRepository;
import com.devxschool.services.ServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class PhonebookApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length < 1) {
            System.out.println("Usage: PhoneBookApp fileStorePath");
            System.exit(0);
        } else {
            String filePath = args[0];
            ServiceImpl phoneBookService = new ServiceImpl(filePath);

            while (true) {
                drawInterface();
                String command = scanner.nextLine();
                switch (command) {
                    case "4":
                        phoneBookService.flushAllToFile();
                        System.exit(0);
                        break;
                    case "1":
                        System.out.println("Please enter Name");
                        String name = scanner.nextLine();
                        System.out.println("Please enter LastName");
                        String lastName = scanner.nextLine();
                        System.out.println("Please enter PhoneNumber");
                        String phoneNumber = scanner.nextLine();
                        Person p = new Person(null, name, lastName, phoneNumber);
                        phoneBookService.save(p);
                        break;
                    case "2":
                        System.out.println("Please enter Name");
                        String filter = scanner.nextLine();
                        List<Person> pList = phoneBookService.searchByName(filter);
                        System.out.println("I found " + pList.size() + " records!");
                        pList.stream().forEach(person -> System.out.println(person.getID() + " " + person.getFirstName() + " " + person.getLastName() + " " + person.getPhoneNumber()));
                        break;
                }

                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        phoneBookService.flushAllToFile();
                    }
                });

            }
        }
    }

    public static void drawInterface() {
        System.out.println("Phone Book App v0.1");
        System.out.println("1. Create Record");
        System.out.println("2. Find Record by Name or Surname");
        System.out.println("3. Search by Filter");
        System.out.println("4. Save and Exit");
    }
}
