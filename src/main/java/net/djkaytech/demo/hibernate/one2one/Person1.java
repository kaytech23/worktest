package net.djkaytech.demo.hibernate.one2one;

import javax.persistence.*;

/**
 * Created by PLKASIU on 2017-10-11.
 */

@Entity
@Table(name ="Person")
public class Person1 {

    private int id;

    private String name;

    private Address address;

    public Person1() {
    }

    public Person1(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
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
