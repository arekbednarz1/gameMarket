package pl.ab.gamemark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.Shop;

import javax.transaction.Transactional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {



    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Shop s SET s.games = :catalog where s.id=:id")
    void updateShopGames(@Param("catalog") GamesCatalogue gamesCatalogue,@Param("id") Long id);

}
