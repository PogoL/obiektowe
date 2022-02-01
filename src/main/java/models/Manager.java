package models;

import com.example.projectlab.Db;
import models.enums.Objects;
import models.enums.Position;
import models.interfaces.IRetirable;
import models.interfaces.ITransferable;

import java.time.LocalDateTime;
import java.util.UUID;

public class Manager extends Person implements ITransferable, IRetirable {
    private int yearsOfExperience;
    private boolean isRetired;

    public Manager(UUID id, String name, LocalDateTime creationTime, String lastName, int age, int yearsOfExperience) {
        super(id, name, creationTime, lastName, age);
        this.yearsOfExperience = yearsOfExperience;
        this.position = Position.Manager;
    }

    @Override
    public void displaySalutation() {
        System.out.println(getPosition() + ": " + getName() + ". " + getAge() + " years old with " + getYearsOfExperience() + " years of experience.");
    }

    private Club getClub(UUID clubId) {
        return Db.getInstance().getObject(Objects.Club, clubId);
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public void retire() {
        this.isRetired = true;
    }
    public boolean isRetired() {
        return isRetired;
    }

    @Override
    public String toString() { return getPosition() + ": " + getName() + " (" + getAge() + ")"; }

    @Override
    public void transferToAnotherClub(UUID oldClubId, UUID newClubId) {
        var club = getClub(oldClubId);
        club.setManagerId(null);
        var newClub = getClub(newClubId);
        newClub.setManagerId(this.getId());
        System.out.println("MANAGER TRANSFER: " + getName() + " " + getLastName() + " FROM " + club.getName() + " TO " + newClub.getName());
    }
}
