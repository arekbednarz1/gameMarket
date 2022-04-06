package pl.ab.gamemark.services.implementation;

import org.springframework.stereotype.Service;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.repository.GameRepository;
import pl.ab.gamemark.services.GameService;

import java.util.List;

@Service
public class GameServiceDB implements GameService {
    GameRepository gameRepository;

    public GameServiceDB(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.getById(id);
    }

    @Override
    public List<Game> findByName(String name) {
        return gameRepository.findAllByPartName(name);
    }

    @Override
    public Game findGameByName(String name) {
        return gameRepository.findGameByName(name);
    }

}
