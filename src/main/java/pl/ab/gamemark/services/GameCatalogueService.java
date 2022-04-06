package pl.ab.gamemark.services;

import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.GamesCatalogue;

import java.util.List;

public interface GameCatalogueService {


    void save(GamesCatalogue gamesCatalogue);
    void delete(GamesCatalogue gamesCatalogue);
    void update(List<Availability>availabilities,Long id);
}
