package models;

import com.example.projectlab.Db;
import models.enums.Objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Club extends BaseEntity {
    private UUID stadiumId;
    private Stadium stadium;
    private UUID leagueId;
    private League league;
    private List<UUID> playersIds = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private UUID managerId;
    private Manager manager;

    public Club(UUID id, String name, LocalDateTime creationTime, UUID stadiumId, UUID leagueId, UUID managerId) {
        super(id, name, creationTime);
        this.stadiumId = stadiumId;
        this.leagueId = leagueId;
        this.managerId = managerId;
    }

    public UUID getLeagueId() {
        return leagueId;
    }

    public League getLeague() {
        return Db.getInstance().getObject(Objects.League, leagueId);
    }

    public void addPlayer(UUID playerId) {
        playersIds.add(playerId);
    }

    public void removePlayer(UUID playerId) {
        playersIds.remove(playerId);
    }

    @Override
    public String toString() {
        return this.getLeague().getName() + ": " + this.getName();
    }

    public UUID getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(UUID stadiumId) {
        this.stadiumId = stadiumId;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }
}
