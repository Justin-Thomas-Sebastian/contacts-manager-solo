import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.nio.file.*;
import java.util.Scanner;
import util.Input;

public class Main {
    public static void main (String[] args) throws Exception {
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
            Input in = new Input();
            System.out.println(" ");
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.print("Enter choice (1 - 5): ");
            int userOption = in.getInt();
            System.out.println(" ");

            switch(userOption) {
                case 1:
                    viewContacts(contactList);
                    break;
                case 2:
                    Contact newContact = addContact(contactList);
                    String line = newContact.getFirstName() + " " + newContact.getLastName() + " " + newContact.getContactNumber();
                    contactListStr.add(line);
                    List<String> newContacts = new ArrayList<>();
                    newContacts.add(line);
                    Files.write(
                      dataFile,
                      newContacts,
                      StandardOpenOption.APPEND
                    );
                    break;
                case 3:
                    List<Contact> found = searchContact(contactList);
                    viewContacts(found);
                    break;
                case 4:
                    Contact removed = deleteContact(contactList);
                    String toRemoveStr = "";
                    for(String contact : contactListStr){
                        String[] contactElements = contact.split(" ");
                        if(contactElements[0].equalsIgnoreCase(removed.getFirstName()) &&
                        contactElements[1].equalsIgnoreCase(removed.getLastName())){
                            toRemoveStr = contact;
                        }
                    }
                    contactListStr.remove(toRemoveStr);
                    Files.write(
                      dataFile,
                      contactListStr
                    );
                    break;
                case 5:
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
            }
        }
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

    public static Contact addContact(List<Contact> contactList){
        Input in = new Input();
        System.out.print("Enter first name: ");
        String firstName = in.getString();
        System.out.print("Enter last name: ");
        String lastName = in.getString();
        System.out.print("Enter contact number: ");
        String contactNumber = in.getString();
        Contact newContact = new Contact(firstName, lastName, contactNumber);
        contactList.add(newContact);
        return newContact;
    }

    public static List<Contact> searchContact(List<Contact> contactList){
        Input in = new Input();
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
        return found;
    }

    public static Contact deleteContact(List<Contact> contactList){
        Input in = new Input();
        System.out.println("Contact to Delete.");
        System.out.print("First Name: ");
        String fName = in.getString();
        System.out.print("Last Name: ");
        String lName = in.getString();
        Contact toRemove = new Contact();
        for(Contact contact : contactList){
            if(fName.equalsIgnoreCase(contact.getFirstName()) && lName.equalsIgnoreCase(contact.getLastName())) {
                toRemove = contact;
            }
        }
        contactList.remove(toRemove);
        return toRemove;
    }
}