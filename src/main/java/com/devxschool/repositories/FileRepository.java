package com.devxschool.repositories;

import com.devxschool.models.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

public abstract class FileRepository {

    public static List<Person> readFromFile(String filePath) {
        File file = new File(filePath);
        List<Person> personList = new ArrayList<Person>();
        if (file.exists()) {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){
                String line;
                while((line = br.readLine()) != null){
                    Person person = parseLine(line);
                    personList.add(person);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return personList;
    }

    private static Person parseLine(String line) {
        //ID;FirstName LastName;phoneNumber
        //1; John Smith; 222 222 222

        String[] parsedString1 = line.split(";");// [0] --> 1 [1] --> John Smith [2]--> 2222 222 222
        String[] parsedNameSurname = parsedString1[1].split(" "); // [0]-->John [1] -->Smith
        Person person = new Person();
        person.setID(parsedString1[0]);
        person.setFirstName(parsedNameSurname[0]);
        person.setLastName(parsedNameSurname[1]);
        person.setPhoneNumber(parsedString1[2]);
        return person;
    }

    public static void writeToFile(String filePath, List<Person> personList){
        StringBuilder sb = new StringBuilder();
        personList.stream().forEach(p -> {
            sb.append(
                    p.getID()+";"
                            +p.getFirstName()+" "
                            +p.getLastName()+";"
                            +p.getPhoneNumber()).append("\n");
        });
        //ID;Firstname LastName;PhoneNumber

        byte data[] = sb.toString().getBytes();

        try(OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get(filePath), CREATE,TRUNCATE_EXISTING))){
            out.write(data, 0, data.length);
        } catch (IOException e){
            System.err.println(e);
        }
    }
}
