package de.tobibechtold.callcenter.component;

import de.tobibechtold.callcenter.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Tobi on 23.11.16.
 */
public class CallComponent {

    public List<Person> filter(List<Person> persons, Predicate<Person> predicate) {
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }
}
