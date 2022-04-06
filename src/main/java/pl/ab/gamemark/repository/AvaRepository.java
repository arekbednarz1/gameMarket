package pl.ab.gamemark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.entity.GamesCatalogue;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AvaRepository extends JpaRepository<Availability, Long> {

    @Transactional
    @Modifying
    @Query("update Availability a SET a.count = ?1 where a.id=?2")
    void updateShopGames(int count, Long id);

    @Query
    List<Availability>findAllById(Long id);
}
