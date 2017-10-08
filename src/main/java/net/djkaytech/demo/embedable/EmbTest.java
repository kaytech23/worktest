package net.djkaytech.demo.embedable;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "embtest")
public class EmbTest {

    @EmbeddedId
    private EmbId id;

    @Column
    private String name;

    public EmbId getId() {
        return id;
    }

    public void setId(EmbId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
