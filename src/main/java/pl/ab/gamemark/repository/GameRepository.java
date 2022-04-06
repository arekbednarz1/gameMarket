package pl.ab.gamemark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ab.gamemark.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {



    @Query("SELECT g FROM Game g WHERE g.name like %?1%")
    List<Game> findAllByPartName(String name);


    Game findGameByName(String name);

}
