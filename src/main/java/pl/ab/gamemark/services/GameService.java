package pl.ab.gamemark.services;

import pl.ab.gamemark.entity.Game;

import java.util.List;

public interface GameService {

    void save(Game game);

    List<Game> findAll();

    void deleteById(Long id);

    Game findById(Long id);

    List<Game>findByName(String name);

    Game findGameByName(String name);

}
