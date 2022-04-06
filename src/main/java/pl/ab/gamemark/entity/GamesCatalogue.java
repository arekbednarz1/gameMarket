package pl.ab.gamemark.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class GamesCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn
   private List<Availability> games;




    public GamesCatalogue() {
    }

    public List<Availability> getGames() {
        return games;
    }

    public void setGames(List<Availability> games) {
        this.games = games;
    }
}

