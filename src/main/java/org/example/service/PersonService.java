package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter @Setter
public class PersonService implements IdMaxValue,AutoIdEditor{
    private List<Person> personList = new ArrayList<>();

    public void addPerson(String name, String phoneNumber, String emailAddress){
        phoneNumber = phoneNumber.replace(" ", "").replace("-", "");
        if (!checkDuplicateName(name)
                && nameValid(name)
//                && !checkDuplicatePhone(phoneNumber)
                && phoneValid(phoneNumber)
                && !checkDuplicateEmail(emailAddress)
                && emailValidator(emailAddress)
        )
        {
            this.personList.add(new Person(autoIdEditor(),name, phoneNumber, emailAddress));
        }
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
            persons.addAll(this.personList.stream().filter(p->p.getId() == ((Number) text).intValue()).toList());
        }
        if(text instanceof String){
            persons.addAll(this.personList.stream().filter(p-> p.getPhoneNumber().equalsIgnoreCase(((String) text))).toList());
            persons.addAll(this.personList.stream().filter(p->p.getName().toLowerCase().contains(((String) text).toLowerCase())).toList());
            persons.addAll(this.personList.stream().filter(p->p.getEmailAddress().toLowerCase().contains(((String) text).toLowerCase())).toList());
        }return persons;
    }

}
