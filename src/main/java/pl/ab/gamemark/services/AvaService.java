package pl.ab.gamemark.services;

import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.entity.GamesCatalogue;

import java.util.List;

public interface AvaService {

    void save(Availability availability);
    void delete(Availability availability);
    void update(int count, Long id);
    List<Availability>getAllById(Long id);
}
