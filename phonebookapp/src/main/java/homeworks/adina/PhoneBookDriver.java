package homeworks.adina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class PhoneBookDriver {
    static List<Person> contactList;

    static {
        try {
            contactList = ContactsDb.readContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PhoneBookDriver() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        Person p = new Person(7, "lsku", "Rsmdevs", "0022222");

        searchPersonByNameAndSurname(p);
        createPerson(p);
        System.out.println(searchById(7));
        Person editedPerson = new Person(7, "Johny", "Travolta", "123456");
        editPerson(editedPerson);
        System.out.println(searchById(7));
        deleteContact(4);
        System.out.println(contactList);
        searchPersonByPhoneNumber(" 001333333333");
        ContactsDb.saveNewContactList();

    }

    public static void createPerson(Person person) {

        contactList.add(person);
    }

    public static void deleteContact(int id) {
        contactList.removeIf(p -> p.getId() == id);
    }

    public static void searchPersonByNameAndSurname(Person person) {
        contactList.stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName()) && p.getLastName()
                        .equalsIgnoreCase(person.getLastName()))
                .forEach(System.out::println);
    }

    public static void searchPersonByPhoneNumber(String phoneNumber) {
        contactList.stream()
                .filter(person -> person.getPhoneNumber().equals(phoneNumber))
                .forEach(System.out::println);
    }

    public static void editPerson(Person person) {
        contactList.stream()
                .filter(p -> p.getId() == person.getId())
                .forEach(p -> {
                    p.setId(person.getId());
                    p.setPhoneNumber(person.getPhoneNumber());
                    p.setLastName(person.getLastName());
                    p.setFirstName(person.getFirstName());
                });
    }

    public static Person searchById(int id) {
        return contactList.stream()
                .filter(person -> person.getId() == id)
                .findFirst().orElse(null);
    }



}
