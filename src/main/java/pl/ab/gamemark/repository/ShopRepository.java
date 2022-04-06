package pl.ab.gamemark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.Shop;

import javax.transaction.Transactional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {





    @Modifying
    @Transactional
    @Query("update Shop s SET s.games = ?1 where s.id=?2")
    void updateShopGames(GamesCatalogue gamesCatalogue,Long id);

}
