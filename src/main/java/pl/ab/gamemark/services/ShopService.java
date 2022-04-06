package pl.ab.gamemark.services;

import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    void save(Shop shop);

    List<Shop> findAll();

    List<Shop>findByPartName(String partName);

    Optional<Shop> findById(Long id);

    void deleteShopById(Long id);

    void updateGameList(GamesCatalogue catalogue, Long id);
}
