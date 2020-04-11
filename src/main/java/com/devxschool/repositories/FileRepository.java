package com.devxschool.repositories;

import com.devxschool.models.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.CREATE;

public abstract class FileRepository {

    public static List<Person> readAllPersonsFromFile(String filePath) {
        File file = new File(filePath);
        List<Person> personList = new ArrayList<Person>();
        if (file.exists()) {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Person p = parsePerson(line);
                    personList.add(p);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }

    private static Person parsePerson(String line) {
        String[] splitedString = line.split(";");
        String[] nameSurname = splitedString[1].split(" ");
        return new Person(Long.parseLong(splitedString[0]), nameSurname[0], nameSurname[1], splitedString[2]);
    }

    public static void writeAllPersonToFile(String filePath, List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        personList.stream().forEach(p -> {
            sb.append(p.getID()+";"+p.getFirstName()+" "+p.getLastName()+";"+p.getPhoneNumber()).append("\n");
        });

        byte data[] = sb.toString().getBytes();

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get(filePath), CREATE, TRUNCATE_EXISTING))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
