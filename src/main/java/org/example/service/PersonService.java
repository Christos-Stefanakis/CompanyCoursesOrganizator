package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Company;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter @Setter
public class PersonService implements IdMaxValue,AutoIdEditor{

    private List<Person> personList = new ArrayList<>();
    CompanyService companyService;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void addPerson(String name, String phoneNumber, String emailAddress){
        phoneNumber = phoneNumber.replace(" ", "").replace("-", "");
        if (!checkDuplicateName(name)
                && nameValid(name)
//                && !checkDuplicatePhone(phoneNumber)
                && phoneValid(phoneNumber)
                && !checkDuplicateEmail(emailAddress)
                && emailValidator(emailAddress))
            this.personList.add(
                    new Person(
                            autoIdEditor()
                            ,name
                            ,phoneNumber
                            ,emailAddress));
    }

    public <T> void updatePeron(int id, String name, String phoneNumber, String emailAddress){
        Person updatePeron = selectPersonById(id);
        updatePeron.setName(name);
        updatePeron.setPhoneNumber(phoneNumber);
        updatePeron.setEmailAddress(emailAddress);
    }

    public boolean nameValid(String name){
        String regex = "^[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?\\s[A-Z][a-zA-Z]+(?:[-'][a-zA-Z]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }return false;
    }

    public boolean phoneValid(String phoneNumber){
        String regex = "^\\+\\d{3}\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public boolean checkDuplicatePhone(String phoneNumber){
        String finalPhoneNumber = phoneNumber.replace(" ", "").replace("-", "");
        return this.personList.stream().allMatch(n->n.getPhoneNumber().equals(finalPhoneNumber));
    }

    public boolean emailValidator(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            return true;
        }return false;
    }

    public boolean checkDuplicateEmail(String email){
        return this.personList.stream().anyMatch(m->m.getEmailAddress().equals(email));
    }

    public boolean checkDuplicateName(String name){
        if(nameValid(name)){
            return this.personList.stream().anyMatch(n -> n.getName().equalsIgnoreCase(name));
        }return false;
    }

    @Override
    public int idMaxValue() {
        return this.personList.stream()
                .map(Person::getId)
                .max(Integer::compareTo).get();
    }

    @Override
    public int autoIdEditor() {
        if(this.personList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.personList.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    public int personsListSize(){
        return Optional.ofNullable(personList)
                .map(List::size)
                .orElse(0);
    }

    public <T> List<Person> findPerson(T text){
        List<Person> persons = new ArrayList<>();
        if (text instanceof Number){
            persons.add(selectPersonById(((Number) text).intValue()));
        }
        if(text instanceof String){
            persons.addAll(findPersonsByName((String) text));
            persons.addAll(findPersonsByPhoneNumber((String) text));
            persons.addAll(findPersonsByEmailAddress((String) text));
        }return persons;
    }

    public Person selectPersonById(int id){
        return this.personList
                .stream()
                .filter(c->c.getId() == id)
                .findFirst()
                .get();
    }

    public List<Person> findPersonsByName(String text){
        return this.personList
                .stream()
                .filter(c-> c.getName()
                        .toLowerCase()
                        .contains(text
                                .toLowerCase())).toList();
    }

    public List<Person> findPersonsByPhoneNumber(String text){
        return this.personList
                .stream()
                .filter(c-> c.getPhoneNumber()
                        .toLowerCase()
                        .contains(text.toLowerCase())).toList();
    }

    public List<Person> findPersonsByEmailAddress(String text){
        return this.personList
                .stream()
                .filter(c-> c.getEmailAddress()
                        .toLowerCase()
                        .contains(text.toLowerCase())).toList();
    }

    public List<Person> personsWithSpecificEmployee(Company company){
        List<Person> specificPerson = personList
                .stream()
                .filter(p->p.getEmployers() ==  company)
                .toList();
        if(specificPerson.isEmpty()){
            return new ArrayList<>();
        }return specificPerson;
    }

    public void removePerson(int id){
        this.personList = this.personList
                .stream()
                .filter(p->p != selectPersonById(id))
                .toList();
        for(Company company: companyService.findCompanyByEmployee(selectPersonById(id))){
            company.setEmployeesList(company.getEmployeesList().stream().filter(p->p != selectPersonById(id)).toList());
        }
    }

    public void removeCompanyFromPerson(Company company){
        List<Person> personsWithSpecificEmployer = this.personList.stream().filter(p->p.getEmployers() == company).toList();
        for(Person person: personsWithSpecificEmployer){
            person.setEmployers(new Company());
        }
    }

}
