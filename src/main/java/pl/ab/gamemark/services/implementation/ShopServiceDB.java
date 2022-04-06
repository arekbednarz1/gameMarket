package pl.ab.gamemark.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.Shop;
import pl.ab.gamemark.repository.ShopRepository;
import pl.ab.gamemark.services.ShopService;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceDB implements ShopService {

    @Autowired
    ShopRepository shopRepository;



   @Override
    public void save(Shop shop) {
       shopRepository.save(shop);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public List<Shop> findByPartName(String partName) {
        return null;
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }


    @Override
    public void deleteShopById(Long id) {
       shopRepository.deleteById(id);
    }

    @Override
    public void updateGameList(GamesCatalogue catalogue, Long id) {
       shopRepository.updateShopGames(catalogue,id);
    }
}
