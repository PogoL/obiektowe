package com.example.projectlab;

import models.*;
import models.enums.Country;
import models.enums.Objects;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Db {
    private ArrayList<BaseEntity> baseEntities;
    private Map<Objects, ArrayList> map;

    private Db() {
        baseEntities = new ArrayList<BaseEntity>();
        ArrayList<League> leagueList = new ArrayList<League>();
        League l1 = new League(UUID.randomUUID(), "Premier League", LocalDateTime.now(), Country.England);
        League l2 = new League(UUID.randomUUID(), "Ligue 1", LocalDateTime.now(), Country.France);
        League l3 = new League(UUID.randomUUID(), "Bundesliga", LocalDateTime.now(), Country.Germany);
        leagueList.add(l1);
        leagueList.add(l2);
        leagueList.add(l3);
        ArrayList<Stadium> stadiumList = new ArrayList<Stadium>();
        Stadium s1 = new Stadium(UUID.randomUUID(), "Old Trafford", LocalDateTime.now(), 80100);
        Stadium s2 = new Stadium(UUID.randomUUID(), "Anfield Road", LocalDateTime.now(), 68900);
        Stadium s3 = new Stadium(UUID.randomUUID(), "Wembley", LocalDateTime.now(), 79500);
        stadiumList.add(s1);
        stadiumList.add(s2);
        stadiumList.add(s3);
        ArrayList<Manager> managerList = new ArrayList<Manager>();
        Manager m1 = new Manager(UUID.randomUUID(), "Jurgen", LocalDateTime.now(), "Klopp", 55, 20);
        Manager m2 = new Manager(UUID.randomUUID(), "Ralf", LocalDateTime.now(), "Rangnick", 65, 30);
        Manager m3 = new Manager(UUID.randomUUID(), "Pep", LocalDateTime.now(), "Guardiola", 50, 15);
        managerList.add(m1);
        managerList.add(m2);
        managerList.add(m3);
        ArrayList<Club> clubList = new ArrayList<Club>();
        Club c1 = new Club(UUID.randomUUID(), "Manchester United", LocalDateTime.now(), s1.getId(), l1.getId(), m2.getId());
        Club c2 = new Club(UUID.randomUUID(), "Manchester City", LocalDateTime.now(), s3.getId(), l1.getId(), m3.getId());
        Club c3 = new Club(UUID.randomUUID(), "Liverpool", LocalDateTime.now(), s2.getId(), l1.getId(), m1.getId());
        clubList.add(c1);
        clubList.add(c2);
        clubList.add(c3);
        ArrayList<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player(UUID.randomUUID(), "David", LocalDateTime.now(), "De Gea", 30, 50500500, c1.getId());
        Player p2 = new Player(UUID.randomUUID(), "Paul", LocalDateTime.now(), "Pogba", 30, 90500500, c1.getId());
        Player p3 = new Player(UUID.randomUUID(), "Marcus", LocalDateTime.now(), "Rashford", 24, 150500500, c1.getId());
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);

        map = new HashMap<Objects, ArrayList>() {{
            put(Objects.League, leagueList);
            put(Objects.Club, clubList);
            put(Objects.Stadium, stadiumList);
            put(Objects.Manager, managerList);
            put(Objects.Player, playerList);
        }};
    }

    private static Db instance;

    public static Db getInstance() {
        if (instance == null) instance = new Db();
        return instance;
    }

    public ArrayList getData(Objects type) {
        return map.get(type);
    }

    public void setData(Objects type, Object object) {
        map.get(type).add(object);
    }

    public void deleteData(Objects type, UUID id) {
        ((ArrayList<BaseEntity>)map.get(type)).removeIf(p -> p.getId().equals(id));
    }

    public List<Club> getClubsByLeague(League league) {
        ArrayList<Club> allClubs = map.get(Objects.Club);
        return allClubs.stream().filter(c -> c.getLeagueId() == league.getId()).collect(Collectors.toList());
    }

    public List<Player> getPlayersByClub(Club club) {
        ArrayList<Player> allClubs = map.get(Objects.Player);
        return allClubs.stream().filter(c -> c.getClubId() == club.getId()).collect(Collectors.toList());
    }

    public static <T extends BaseEntity> T getObject(Objects type, UUID objId) {
        List<T> objects = Db.getInstance().getData(type);
        return objects.stream()
                .filter(item -> item.getId() == objId)
                .collect(Collectors.toList()).get(0);
    }
}