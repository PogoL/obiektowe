package models;

import models.enums.Position;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Person extends BaseEntity {
    private String lastName;
    private int age;
    protected Position position;

    public Person(UUID id, String name, LocalDateTime creationTime, String lastName, int age) {
        super(id, name, creationTime);
        this.lastName = lastName;
        this.age = age;
    }

    //salutation different for each position type
    public abstract void displaySalutation();

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getLastName() {
        return lastName;
    }
}
