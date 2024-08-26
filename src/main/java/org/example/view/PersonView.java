package org.example.view;

import org.beryx.textio.TextIO;
import org.example.service.PersonService;
import org.example.view.menuOptions.ApplicationMenu;
import org.example.view.menuOptions.PersonMenuOptions;

public class PersonView implements ApplicationMenu {

    private final TextIO textIO;
    private final PersonService personService;

    public PersonView(TextIO textIO, PersonService personService) {
        this.textIO = textIO;
        this.personService = personService;
    }


    @Override
    public void showMenu() {
        PersonMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nPerson Menu\n----------------------\n");
        while (selectedOption != PersonMenuOptions.BACK) {
            selectedOption = textIO.newEnumInputReader(PersonMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(PersonMenuOptions.BACK)
                    .read("Choose an Option");
            switch (selectedOption) {
                case ADD_PERSON -> addPerson();
                case UPDATE_PERSON -> updatePerson();
                case REMOVE_PERSON -> removePerson();
                case LIST_PERSONS -> listAllPersons();
                case BACK -> textIO.getTextTerminal().println("Going back");
            }
        }
    }
    public void addPerson(){
        // TODO add constraints to inputs
        textIO.getTextTerminal().println("Adding a Person");
        String name = textIO.newStringInputReader()
                .withPattern("^[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?\\s[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?$")
                .read("Enter person name: ");
        String phoneNumber = textIO.newStringInputReader()
                .withPattern("^\\+\\d{3}\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}$")
                .read("Enter phone number: ");
        String emailAddress = textIO.newStringInputReader()
                .withPattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                .read("Enter email address: ");

        personService.addPerson(name, phoneNumber, emailAddress);
    }

    public void updatePerson(){
        textIO.getTextTerminal().println("Updating a Person");
        String searchText = textIO.newStringInputReader().read("Search Person: ");
        personService.findPerson(searchText);
        int personID = textIO.newIntInputReader().read("Enter person ID: ");
        String name = textIO.newStringInputReader()
                .withDefaultValue(personService.selectPersonById(personID).getName())
                .withInputTrimming(true)
                .withPattern("^[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?\\s[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?$")
                .read("Enter person name: ");

        String phoneNumber = textIO.newStringInputReader()
                .withDefaultValue(personService.selectPersonById(personID).getPhoneNumber())
                .withInputTrimming(true)
                .withPattern("^\\+\\d{3}\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}$")
                .read("Enter phone number: ");

        String emailAddress = textIO.newStringInputReader()
                .withDefaultValue(personService.selectPersonById(personID).getEmailAddress())
                .withInputTrimming(true)
                .withPattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                .read("Enter email address: ");

        personService.updatePeron(personID, name, phoneNumber, emailAddress);
    }

    public void removePerson(){
        textIO.getTextTerminal().println("Removing a Person");
        String searchText = textIO.newStringInputReader().read("Search Person: ");
        personService.findPerson(searchText);
        int personID = textIO.newIntInputReader().read("Enter person ID: ");
        personService.removePerson(personID);
    }

    public void listAllPersons(){
        textIO.getTextTerminal().println("List all persons");
        personService.getPersonList().forEach(System.out::println);
    }
}
