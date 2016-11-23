package de.tobibechtold.callcenter.model;

public interface Person {
    String getName();

    int getAge();

    String getStreet();

    String getZipCode();

    Gender getGender();

    String getCity();

    enum Gender {
        MALE, FEMALE, UNDEFINED
    }
}
