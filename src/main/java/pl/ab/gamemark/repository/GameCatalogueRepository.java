package pl.ab.gamemark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ab.gamemark.entity.Availability;

import pl.ab.gamemark.entity.GamesCatalogue;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GameCatalogueRepository extends JpaRepository<GamesCatalogue,Long> {


    @Transactional
    @Query("UPDATE GamesCatalogue g SET g.games=?1 where g.id=?2")
    void updateGameCatalogue(List<Availability> games, Long id);


}
