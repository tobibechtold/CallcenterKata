package de.tobibechtold.callcenter.model;

/**
 * Created by Tobi on 23.11.16.
 */
public interface Person {
    String getName();
    int getAge();
    String getStreet();
    String getZipCode();
    Gender getGender();
    String getCity();

    public enum Gender {
        MALE, FEMALE, UNDEFINED
    }
}
