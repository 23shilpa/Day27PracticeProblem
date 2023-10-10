import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters (omitted for brevity)

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", firstName, lastName, address, city, state, zip, phoneNumber, email);
    }
}

public class AddressBookIO {
    private static final String FILE_PATH = "address_book.csv";

    public static void main(String[] args) {
        List<Contact> contacts = loadContactsFromFile();
        displayContacts(contacts);

        // Adding a new contact
        Contact newContact = new Contact("Alice", "Johnson", "789 Oak St", "Chicago", "IL", "60601", "555-789-1234", "alice@example.com");
        contacts.add(newContact);

        // Save the updated contact list to the file
        saveContactsToFile(contacts);
    }

    private static List<Contact> loadContactsFromFile() {
        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String address = parts[2];
                    String city = parts[3];
                    String state = parts[4];
                    String zip = parts[5];
                    String phoneNumber = parts[6];
                    String email = parts[7];

                    Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    private static void saveContactsToFile(List<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayContacts(List<Contact> contacts) {
        System.out.println("Contacts in Address Book:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}