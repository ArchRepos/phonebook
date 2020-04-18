package com.devxschool.services;

import com.devxschool.models.Person;
import com.devxschool.repositories.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PhoneBookService implements Service<Person> {

    private List<Person> personList;
    String filePath;

    public PhoneBookService(String filePath){
        this.filePath = filePath;
        this.personList = FileRepository.readFromFile(filePath);
    }

    @Override
    public Optional<Person> getByID(String id) {
        return personList.stream().filter(person -> person.getID().equals(id)).findFirst();
    }

    @Override
    public Optional<Person> getByNameAndSurname(String name, String surname) {
        return personList.stream().filter(
                person -> person.getFirstName().equals(name) &&
                        person.getLastName().equals(surname)).findFirst();
    }

    public List<Person> getAll() {
        return personList;
    }

    @Override
    public List<Person> searchByFilter(String filter) {
        return personList.stream().filter(
                person -> person.getFirstName().toLowerCase().startsWith(filter.toLowerCase()) ||
                person.getLastName().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return personList.size();
    }

    @Override
    public void save(Person person) {

        if (person.getID() == null){
            person.setID(UUID.randomUUID().toString());
            personList.add(person);
        } else {
            Optional<Person> optionalPerson = getByID(person.getID());
            if (optionalPerson.isPresent()) {
                personList.remove(optionalPerson.get());
                personList.add(person);
            } else {
                System.out.println("No such person.");
            }
        }
    }

    @Override
    public void delete(String id) {
        Optional<Person> optionalPerson = getByID(id);
        if (optionalPerson.isPresent()) {
            personList.remove(optionalPerson.get());
        } else {
            System.out.println("No Such Person");
        }
    }

    public void flushAllRecordsToFile(){
        FileRepository.writeToFile(filePath, personList);
    }
}
