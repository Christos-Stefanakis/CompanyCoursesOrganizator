package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    private List<Person> personList = new ArrayList<>();

    public Person() {
    }

    public Person(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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
                && emailValidator(emailAddress)
        )
        {
            this.personList.add(new Person(name, phoneNumber, emailAddress));
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
        return this.personList.stream().allMatch(n->n.phoneNumber.equals(finalPhoneNumber));
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
        return this.personList.stream().anyMatch(m->m.emailAddress.equals(email));
    }

    public boolean checkDuplicateName(String name){
        if(nameValid(name)){
            return this.personList.stream().anyMatch(n -> n.name.equalsIgnoreCase(name));
        }return false;

    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}


