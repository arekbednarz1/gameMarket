package pl.ab.gamemark.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.services.GameService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    ResponseEntity<List<Game>>findAll(){
        return ResponseEntity.ok(gameService.findAll());
    }
    @PostMapping
    ResponseEntity<?>addNew(@RequestBody Game game){
        gameService.save(game);
        return new ResponseEntity<>(String.format("Dodano do bazy %s",game.getName()), HttpStatus.CREATED);
    }

    @DeleteMapping(path ="/id/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable Long id){
        String name = gameService.findById(id).getName();
        gameService.deleteById(id);
        return ResponseEntity.ok(String.format("Usunieto z bazy %s",name));
    }

    @DeleteMapping(path = "/name/{name}")
    public ResponseEntity<?> deleteGameByName(@PathVariable String name){
        List<String> names = gameService.findByName(name).stream().map(Game::getName).collect(Collectors.toList());
        List<Long>ids = gameService.findByName(name).stream().map(Game::getId).collect(Collectors.toList());
        for (Long id : ids){
            gameService.deleteById(id);
        }
        return ResponseEntity.ok(String.format("usunieto z bazy : %s",names));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<?> findByPartName(@PathVariable String name){
        return ResponseEntity.ok(gameService.findByName(name));
    }



}
