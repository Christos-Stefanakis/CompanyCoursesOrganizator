package org.example.view;

import org.example.model.Company;
import org.example.service.CompanyService;
import org.example.view.menuOptions.ApplicationMenu;
import org.example.view.menuOptions.CompanyMenuOptions;
import org.beryx.textio.TextIO;

public class CompanyView implements ApplicationMenu {

    private final TextIO textIO;
    private final CompanyService companyService;

    public CompanyView(TextIO textIO, CompanyService companyService) {
        this.textIO = textIO;
        this.companyService = companyService;
    }

    @Override
    public void showMenu() {
        CompanyMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nCompany Menu\n----------------------\n");
        while (selectedOption != CompanyMenuOptions.BACK) {
            selectedOption = textIO.newEnumInputReader(CompanyMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(CompanyMenuOptions.BACK)
                    .read("Choose an Option");
            switch (selectedOption) {
                case ADD_COMPANY -> addCompany();
                case UPDATE_COMPANY -> updateCompany();
                case REMOVE_COMPANY -> removeCompany();
                case LIST_COMPANIES -> listAllCompanies();
                case BACK -> textIO.getTextTerminal().println("Going back");
            }
        }
    }
    public void addCompany(){
        // TODO add constraints to inputs
        textIO.getTextTerminal().println("Adding a Company");
        String name = textIO.newStringInputReader().read("Enter company name: ");
        textIO.getTextTerminal().println("Address options");

        String country = textIO.newStringInputReader().read("Enter country: ");
        String cityName = textIO.newStringInputReader().read("Enter city name: ");
        String stateAbbreviation = textIO.newStringInputReader().read("Enter State Abbreviation : ");
        String streetName = textIO.newStringInputReader().read("Enter street name : ");
        int buildNumber = textIO.newIntInputReader().read("Enter build number : ");
        String zipCode = textIO.newStringInputReader().read("Enter zip code : ");

        companyService.addCompany(name, country, cityName, stateAbbreviation, streetName, zipCode, buildNumber);
    }

    public void updateCompany(){
        textIO.getTextTerminal().println("Update a company");
        String searchText = textIO.newStringInputReader().read("Search company: ");
        companyService.findCompany(searchText);
        int id = textIO.newIntInputReader().read("Enter company id: ");
        Company selectedCompany = companyService.selectCompanyById(id);

        String name = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getName())
                .withInputTrimming(true)
                .read("Enter name: ");

        String country = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getAddress().getCountry())
                .withInputTrimming(true)
                .read("Enter country: ");

        String cityName = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getAddress().getCityName())
                .withInputTrimming(true)
                .read("Enter country: ");

        String stateAbbreviation = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getAddress().getStateAbbreviation())
                .withInputTrimming(true)
                .read("Enter state abbreviation: ");

        String streetName = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getAddress().getStreetName())
                .withInputTrimming(true)
                .read("Enter street name: ");

        int buildNumber = textIO.newIntInputReader()
                .withDefaultValue(selectedCompany.getAddress().getBuildNumber())
                .withInputTrimming(true)
                .read("Enter build number: ");

        String zipCode = textIO.newStringInputReader()
                .withDefaultValue(selectedCompany.getAddress().getZipCode())
                .withInputTrimming(true)
                .read("Enter State zip code: ");

        companyService.updateCompany(id, name, country, cityName, stateAbbreviation, streetName, zipCode, buildNumber);
    }

    public void removeCompany(){
        textIO.getTextTerminal().println("Remove a company");
        String searchText = textIO.newStringInputReader().read("Search company: ");
        companyService.findCompany(searchText);
        int companyId = textIO.newIntInputReader().read("Enter company ID: ");
        companyService.removeCompany(companyService.selectCompanyById(companyId));
    }

    public void listAllCompanies(){
        companyService.getCompaniesList();
    }
}
