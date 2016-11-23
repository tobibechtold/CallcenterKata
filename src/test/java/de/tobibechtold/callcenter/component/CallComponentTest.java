package de.tobibechtold.callcenter.component;

import de.tobibechtold.callcenter.hipath.HiPath;
import de.tobibechtold.callcenter.model.Person;
import de.tobibechtold.callcenter.model.Person.Gender;
import de.tobibechtold.callcenter.model.PersonImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Tobi on 23.11.16.
 */
public class CallComponentTest {

    private CallComponent underTest;

    @Mock
    private HiPath hiPath;

    @Before
    public void setUp() throws Exception {
        hiPath = mock(HiPath.class);
        underTest = new CallComponent(hiPath);
    }

    @Test
    public void filterPeopleOlderThan16_WithEmptyList_ReturnsEmptyList() throws Exception {
        List<Person> persons = new ArrayList<>();

        assertThat(underTest.filter(persons, p -> false).size(), is(0));
    }

    @Test
    public void filterPeopleOlderThan16_WithPersons_ReturnsListContainingPersons() throws Exception {
        List<Person> persons = new ArrayList<>();
        Person personAge15 = personWithAgeAndGender(15, Gender.MALE);
        Person personAge16 = personWithAgeAndGender(16, Gender.MALE);
        Person personAge17 = personWithAgeAndGender(17, Gender.MALE);
        persons.add(personAge15);
        persons.add(personAge16);
        persons.add(personAge17);

        List<Person> filtered = underTest.filter(persons, p -> p.getAge() >= 16);

        assertThat(filtered.contains(personAge15), is(false));
        assertThat(filtered.contains(personAge16), is(true));
        assertThat(filtered.contains(personAge17), is(true));
    }

    @Test
    public void filterPeopleBetween25And65() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Person personAge24 = personWithAgeAndGender(24, Gender.MALE);
        Person personAge25 = personWithAgeAndGender(25, Gender.MALE);
        Person personAge26 = personWithAgeAndGender(26, Gender.MALE);
        Person personAge64 = personWithAgeAndGender(64, Gender.MALE);
        Person personAge65 = personWithAgeAndGender(65, Gender.MALE);
        Person personAge66 = personWithAgeAndGender(66, Gender.MALE);
        persons.add(personAge24);
        persons.add(personAge25);
        persons.add(personAge26);
        persons.add(personAge64);
        persons.add(personAge65);
        persons.add(personAge66);

        List<Person> filtered = underTest.filter(persons, p -> p.getAge() >= 25 && p.getAge() <= 65);

        assertThat(filtered.contains(personAge24), is(false));
        assertThat(filtered.contains(personAge25), is(true));
        assertThat(filtered.contains(personAge26), is(true));
        assertThat(filtered.contains(personAge64), is(true));
        assertThat(filtered.contains(personAge65), is(true));
        assertThat(filtered.contains(personAge66), is(false));
    }

    @Test
    public void filterMalesBetween17And18() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Person personAge16 = personWithAgeAndGender(16, Gender.MALE);
        Person personAge17 = personWithAgeAndGender(17, Gender.MALE);
        Person personAge18 = personWithAgeAndGender(18, Gender.MALE);
        Person personAge19 = personWithAgeAndGender(19, Gender.MALE);
        Person female = personWithAgeAndGender(17, Gender.FEMALE);
        persons.add(personAge16);
        persons.add(personAge17);
        persons.add(personAge18);
        persons.add(personAge19);
        persons.add(female);

        List<Person> filtered = underTest.filter(persons, p -> p.getGender().equals(Gender.MALE) && p.getAge() >= 17 && p.getAge() <= 18);

        assertThat(filtered.contains(personAge16), is(false));
        assertThat(filtered.contains(personAge17), is(true));
        assertThat(filtered.contains(personAge18), is(true));
        assertThat(filtered.contains(personAge19), is(false));
        assertThat(filtered.contains(female), is(false));
    }

    @Test
    public void callWithEmptyList_DoesntExecuteCall() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();

        underTest.call(persons);

        verify(hiPath, never()).executeCall(any(Person.class));
    }

    @Test
    public void callWithPersonsExecutesCallToPersons() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Person person1 = personWithAgeAndGender(16, Gender.MALE);
        Person person2 = personWithAgeAndGender(19, Gender.FEMALE);
        persons.add(person1);
        persons.add(person2);

        underTest.call(persons);

        verify(hiPath, times(1)).executeCall(person1);
        verify(hiPath, times(1)).executeCall(person2);
    }

    @Test
    public void callWithPersonsExecutesCallToFilteredPersons() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Person person1 = personWithAgeAndGender(16, Gender.MALE);
        Person person2 = personWithAgeAndGender(19, Gender.FEMALE);
        persons.add(person1);
        persons.add(person2);

        underTest.call(underTest.filter(persons, p -> p.getAge() <= 18));

        verify(hiPath, times(1)).executeCall(person1);
        verify(hiPath, never()).executeCall(person2);
    }

    private Person personWithAgeAndGender(int age, Gender gender) {
        return new PersonImpl("foo", age, "foo", "foo", "foo", gender);
    }
}