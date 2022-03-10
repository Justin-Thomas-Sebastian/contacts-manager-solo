import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.Input;

public class Main {
    static Input in = new Input();

    public static void main (String[] args) throws IOException {
        String directory = "contactsData";
        String fileName = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, fileName);

        // create directory if it does not exist yet
        if(Files.notExists(dataFile)){
            Files.createDirectories(dataDirectory);
        }

        // create contacts.txt if it does not exist yet
        if(Files.notExists(dataFile)){
            Files.createFile(dataFile);
        }

        // Initialize List<String> from file
        List<Contact> contactList = new ArrayList<>();
        List<String> contactListStr = Files.readAllLines(dataFile);

        // Create List<Contacts> from List<String>
        for(String contact : contactListStr){
            String[] contactElements = contact.split(" ");
            String firstName = contactElements[0];
            String lastName = contactElements[1];
            String contactNumber = contactElements[2];
            Contact newContact = new Contact(firstName, lastName, contactNumber);
            contactList.add(newContact);
        }

        boolean isRunning = true;
        while(isRunning){
            System.out.println(" ");
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            int userOption = in.getInt(1, 5);
            System.out.println(" ");

            switch(userOption) {
                case 1:
                    viewContacts(contactList);
                    break;
                case 2:
                    addContact(contactList);
                    break;
                case 3:
                    searchContact(contactList);
                    break;
                case 4:
                    deleteContact(contactList);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
            }
        }

        // Update text file with any changes made
        List<String> newContactListStr = new ArrayList<>();
        for(Contact contact : contactList){
            String contactLine = contact.getFirstName() + " " + contact.getLastName() + " " + contact.getContactNumber();
            newContactListStr.add(contactLine);
        }
        Files.write(dataFile, newContactListStr);
    }

    public static void viewContacts(List<Contact> contactList){
        System.out.println(" ");
        System.out.println("CONTACTS");
        System.out.println("_________");
        System.out.println("Name | Phone Number");
        for(Contact contact : contactList){
            System.out.printf("%s %s | %s\n", contact.getFirstName(), contact.getLastName(), contact.getContactNumber());
        }
        System.out.println(" ");
    }

    public static void addContact(List<Contact> contactList){
        System.out.print("Enter first name: ");
        String firstName = in.getString();
        System.out.print("Enter last name: ");
        String lastName = in.getString();
        System.out.print("Enter contact number: ");
        String contactNumber = in.getString();
        Contact newContact = new Contact(firstName, lastName, contactNumber);
        contactList.add(newContact);
        System.out.println("Added: " + newContact.getFirstName() + " " + newContact.getLastName());
    }

    public static void searchContact(List<Contact> contactList){
        System.out.println(" ");
        System.out.print("First Name: ");
        String fName = in.getString();
        System.out.print("Last Name: ");
        String lName = in.getString();
        System.out.println("Searching...");
        List<Contact> found = new ArrayList<>();
        for(Contact contact: contactList){
            if(fName.equalsIgnoreCase(contact.getFirstName()) || lName.equalsIgnoreCase(contact.getLastName())){
                found.add(contact);
            }
        }
        viewContacts(found);
    }

    public static void deleteContact(List<Contact> contactList){
        System.out.println("Contact to Delete.");
        System.out.print("First Name: ");
        String fName = in.getString();
        System.out.print("Last Name: ");
        String lName = in.getString();
        Contact toRemove = new Contact();
        for(Contact contact : contactList){
            if(fName.equalsIgnoreCase(contact.getFirstName()) && lName.equalsIgnoreCase(contact.getLastName())) {
                toRemove = contact;
                System.out.print("Deleted: ");
                System.out.println(contact.getFirstName() + " " + contact.getLastName());
            }
        }
        contactList.remove(toRemove);
    }
}