package hw5.mishanya.homework5;

import java.util.List;

public class Group {
    private List<Person> people;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    private final static Group INSTANCE = new Group();

    private Group() {

    }

    public static Group getInstance() {
        return INSTANCE;
    }
}
