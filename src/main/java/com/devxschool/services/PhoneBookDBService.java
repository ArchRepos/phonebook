package com.devxschool.services;

import com.devxschool.models.Person;
import com.devxschool.repositories.DataBaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneBookDBService implements Service<Person> {
    List<Person> personList = new ArrayList<>();
    @Override
    public Optional<Person> getByID(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> getByNameAndSurname(String name, String surname) {
        return Optional.empty();
    }

    @Override
    public List<Person> searchByFilter(String filter) {
        return null;
    }

    @Override
    public List<Person> getAll() {

        String query = "SELECT id,first_name,last_name,phone FROM person";

        try(Connection connection = DataBaseRepository.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Person p = new Person();

                p.setID(resultSet.getString(1));
                p.setFirstName(resultSet.getString(2));
                p.setLastName(resultSet.getString(3));
                p.setPhoneNumber(resultSet.getString(4));
                personList.add(p);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return personList;
    }

    @Override
    public long count() {
        int count = 0;
        String query = "SELECT id,first_name,last_name,phone FROM person";

        try(Connection connection = DataBaseRepository.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet !=null){
                resultSet.last();
                count = resultSet.getRow();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return count;
    }

    @Override
    public void save(Person person) {
        String query = "INSERT INTO person(id, first_name, last_name, phone) VALUES (?,?,?,?)";

        try (Connection connection = DataBaseRepository.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            DataBaseRepository.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {

    }
}
