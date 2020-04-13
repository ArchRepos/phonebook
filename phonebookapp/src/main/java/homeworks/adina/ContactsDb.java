package homeworks.adina;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsDb {


    public static List<Person> readContacts() throws IOException {
        File file = new File("phonebookapp/src/main/resources/adinaFiles/contactsDb.txt");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = bufferedReader.readLine();
        List<Person> contactList = new ArrayList<Person>();

        while (line != null) {
            String[] contactArray = line.split(";");
            String[] nameSurnameArray = contactArray[1].split(" ");

            Person contact = new Person();
            contact.setId(Integer.parseInt(contactArray[0]));
            contact.setPhoneNumber(contactArray[2]);
            contact.setFirstName(nameSurnameArray[0]);
            if (nameSurnameArray.length > 1) {
                contact.setLastName(nameSurnameArray[1]);
            }
            contactList.add(contact);
            line = bufferedReader.readLine();

        }
        return contactList;

    }

    public static void saveNewContactList() {
        File contactsFile = new File("phonebookapp/src/main/resources/adinaFiles/contactsDb.txt");

        try (
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(contactsFile)))) {
            PhoneBookDriver.contactList.forEach(person -> {
                StringBuilder contact = new StringBuilder();
                contact.append(person.getId()).append(";").append(person.getFirstName()).append(" ").append(person.getLastName()).append("; ")
                        .append(person.getPhoneNumber().trim());

                try {
                    bufferedWriter.write(contact.toString().toCharArray());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e){
            System.out.println("e");
        }
    }
}


