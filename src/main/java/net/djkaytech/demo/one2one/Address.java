package net.djkaytech.demo.one2one;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

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

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }
}
