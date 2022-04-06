package pl.ab.gamemark.entity;

import javax.persistence.*;

@Entity
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Game game;

    Integer count;


    public Availability(Game game, Integer count) {
        this.game = game;
        this.count = count;
    }

    public Availability() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
