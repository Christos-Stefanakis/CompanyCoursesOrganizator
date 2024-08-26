package org.example.model;


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

    public Address() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public static String zipCode(String zipCode){
        zipCode = zipCode.replaceAll(" ", "");
        return String.format("%s %s", zipCode.substring(0,3), zipCode.substring(3,5));
    }

    @Override
    public String toString() {
        if(this.streetName == null && this.buildNumber == 0 && this.zipCode == null && this.cityName == null  && this.stateAbbreviation == null && this.country == null){
            return "";
        }
        return String.format("%s %s, %s %s-%s, %s", this.streetName, this.buildNumber, this.zipCode, this.cityName, this.stateAbbreviation, this.country);
    }
}
