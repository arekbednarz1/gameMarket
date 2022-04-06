package pl.ab.gamemark.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String street;

    @OneToOne
    private GamesCatalogue games;

    public Shop(String name, String city, String street, GamesCatalogue games) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.games = games;
    }

    public Shop(String name, String city, String street) {
        this.name = name;
        this.city = city;
        this.street = street;
    }

    public Shop() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public GamesCatalogue getGames() {
        return games;
    }

    public void setGames(GamesCatalogue games) {
        this.games = games;
    }
}
