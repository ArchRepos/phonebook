package com.devxschool.services;

import com.devxschool.models.Person;
import com.devxschool.repositories.FileRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceImpl implements Service<Person> {

    private List<Person> personList;
    String filePath;

    public ServiceImpl(String filePath){
        this.personList = FileRepository.readAllPersonsFromFile(filePath);
        this.filePath = filePath;
    }

    @Override
    public Optional<Person> getByID(Long id) {
        return personList.stream().filter(person -> person.getID() == id).findFirst();
    }

    @Override
    public List<Person> searchByName(String filter) {
        return personList.stream().filter(person -> person.getFirstName().toLowerCase().startsWith(filter.toLowerCase()) ||
                person.getLastName().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return personList.size();
    }

    @Override
    public void save(Person person) {
        if (person.getID() == null) {
            person.setID(personList.size() + 1L);
            personList.add(person);
        } else {
            Optional<Person> optionalPerson = getByID(person.getID());
            if(optionalPerson.isPresent()) {
                personList.remove(optionalPerson);
                personList.add(person);
            } else {
                System.out.println("No Such person, Sorry!");
            }
        }
    }

    @Override
    public void delete(Person person) {
        Optional<Person> optionalPerson = getByID(person.getID());
        if(optionalPerson.isPresent()){
            personList.remove(person);
        } else {
            System.out.println("No Such person Sorry!");
        }
    }

    public void flushAllToFile(){
        if(personList.size() > 0){
            FileRepository.writeAllPersonToFile(filePath, personList);
            System.out.println(personList.size() + " Records are written!");
        } else System.out.println("Nothing to write!");
    }
}
