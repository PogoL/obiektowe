package models;

import models.enums.Country;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class League extends BaseEntity {
    private Country country;
    private List<Club> clubs = new ArrayList<>();

    public League(UUID id, String name, LocalDateTime creationTime, Country country) {
        super(id, name, creationTime);
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return this.getCountry() + ": " + this.getName();
    }
}
