package net.djkaytech.demo.hibernate.collections2;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CarRegister")
public class CarRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "jointable", joinColumns = @JoinColumn(name = "ID"))
    private List<Car> cars = new ArrayList<>();


    public void addCar(Car car) {
        this.cars.add(car);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarRegister{" +
                "id=" + id +
                ", cars=" + cars +
                '}';
    }

    public List<Car> getCars() {
        return cars;
    }
}
