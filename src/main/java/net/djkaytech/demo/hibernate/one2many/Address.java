package net.djkaytech.demo.hibernate.one2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PLKASIU on 2017-10-11.
 */

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String street;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Joiner", joinColumns = @JoinColumn(name = "id1"), inverseJoinColumns = @JoinColumn(name = "id2"))
    private List<Person> people = new ArrayList<>();

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", people=" + people +
                '}';
    }
}
