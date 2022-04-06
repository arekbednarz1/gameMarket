package pl.ab.gamemark.services.implementation;

import org.springframework.stereotype.Service;
import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.repository.GameCatalogueRepository;
import pl.ab.gamemark.services.GameCatalogueService;

import java.util.List;

@Service
public class GameCatalogueServiceDB implements GameCatalogueService {

    GameCatalogueRepository gameCatalogueRepository;

    public GameCatalogueServiceDB(GameCatalogueRepository gameCatalogueRepository) {
        this.gameCatalogueRepository = gameCatalogueRepository;
    }

    @Override
    public void save(GamesCatalogue gamesCatalogue) {
        gameCatalogueRepository.save(gamesCatalogue);
    }

    @Override
    public void delete(GamesCatalogue gamesCatalogue) {
        gameCatalogueRepository.delete(gamesCatalogue);
    }

    @Override
    public void update(List<Availability> availabilities, Long id) {
        gameCatalogueRepository.updateGameCatalogue(availabilities, id);
    }
}
