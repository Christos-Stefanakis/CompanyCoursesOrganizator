package org.example.view;

import org.beryx.textio.TextIO;
import org.example.view.menuOptions.ApplicationMenu;
import org.example.view.menuOptions.MainMenuOptions;

public class MainView implements ApplicationMenu {
    private final TextIO textIO;
    private final PersonView personView;
    private final CompanyView companyView;

    public MainView(TextIO textIO,PersonView personView, CompanyView companyView) {
        this.textIO = textIO;
        this.personView = personView;
        this.companyView = companyView;
    }


    @Override
    public void showMenu() {
        MainMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nMain Menu\n----------------------\n");
        while (selectedOption != MainMenuOptions.EXIT) {
            selectedOption = textIO.newEnumInputReader(MainMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(MainMenuOptions.EXIT)
                    .read("Choose an option");
            switch (selectedOption) {
                case PERSON -> personView.showMenu();
                case COMPANY -> companyView.showMenu();
                case EXIT -> textIO.getTextTerminal().println("Exiting");
            }
        }
    }
}
