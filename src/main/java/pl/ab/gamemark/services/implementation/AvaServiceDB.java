package pl.ab.gamemark.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.repository.AvaRepository;
import pl.ab.gamemark.services.AvaService;

import java.util.List;

@Service
public class AvaServiceDB implements AvaService {

    @Autowired
    AvaRepository avaRepository;

    @Override
    public void save(Availability availability) {
    avaRepository.save(availability);
    }

    @Override
    public void delete(Availability availability) {
        avaRepository.delete(availability);
    }

    @Override
    public void update(int count, Long id) {
        avaRepository.updateShopGames(count, id);
    }

    @Override
    public List<Availability> getAllById(Long id) {
        return avaRepository.;
    }
}
