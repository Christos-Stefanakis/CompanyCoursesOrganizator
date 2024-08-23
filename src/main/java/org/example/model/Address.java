package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address {
    private String country;
    private String cityName;
    private String stateAbbreviation;
    private String streetName;
    private String zipCode;
    private int buildNumber;

    public Address(String country, String cityName, String stateAbbreviation, String streetName, String zipCode, int buildNumber) {
        this.country = country;
        this.cityName = cityName;
        this.stateAbbreviation = stateAbbreviation;
        this.streetName = streetName;
        this.zipCode = zipCode(zipCode);
        this.buildNumber = buildNumber;
    }

    public static String zipCode(String zipCode){
        zipCode = zipCode.replaceAll(" ", "");
        return String.format("%s %s", zipCode.substring(0,2), zipCode.charAt(3));
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s %s-%s, %s", this.streetName, this.buildNumber, this.zipCode, this.cityName, this.stateAbbreviation, this.country);
    }
}
