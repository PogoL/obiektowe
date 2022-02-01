package models;

import com.example.projectlab.Db;
import models.enums.Objects;
import models.enums.Position;
import models.interfaces.ITransferable;

import java.time.LocalDateTime;
import java.util.UUID;

public class Player extends Person implements ITransferable {
    private double marketValue;
    private UUID clubId;
    private Club club;

    public Player(UUID id, String name, LocalDateTime creationTime, String lastName, int age, double marketValue, UUID clubId) {
        super(id, name, creationTime, lastName, age);
        this.position = Position.Player;
        this.marketValue = marketValue;
        this.clubId = clubId;
    }

    @Override
    public void displaySalutation() {
        System.out.println(getPosition() + ": " + getName() + ". Age: " + getAge() + ", current market value: " + getMarketValue() + ".");
    }

    public double getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public UUID getClubId() {
        return clubId;
    }
    public Club getClub() {
        return Db.getInstance().getObject(Objects.Club, clubId);
    }

    @Override
    public String toString() {
        return getPosition() + ": " + getName() + " " + getLastName() + " (" + getAge() + ")";
    }

    @Override
    public void transferToAnotherClub(UUID oldClubId, UUID newClubId) {
        System.out.println("TRANSFER: " + getName() + " " + getLastName() + " - " + getClub());
        this.clubId = clubId;
    }
}
