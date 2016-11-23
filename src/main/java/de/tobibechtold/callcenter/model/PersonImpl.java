package de.tobibechtold.callcenter.model;

/**
 * Created by Tobi on 23.11.16.
 */
public class PersonImpl implements Person {
    private String name;
    private int age;
    private String street;
    private String zipCode;
    private String city;
    private Gender gender;

    public PersonImpl(String name, int age, String street, String zipCode, String city, Gender gender) {
        this.name = name;
        this.age = age;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public Gender getGender() {
        return gender;
    }
}
