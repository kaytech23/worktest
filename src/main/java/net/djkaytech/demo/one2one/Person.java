package net.djkaytech.demo.one2one;

import javax.persistence.*;

/**
 * Created by PLKASIU on 2017-10-11.
 */

@Entity
@Table(name ="Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "IKS", joinColumns = @JoinColumn(name = "id1"), inverseJoinColumns = @JoinColumn(name = "id2"))
    private Address address;

    public Person() {
    }

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
