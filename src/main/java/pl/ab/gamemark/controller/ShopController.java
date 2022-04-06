package pl.ab.gamemark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.Shop;
import pl.ab.gamemark.services.AvaService;
import pl.ab.gamemark.services.GameCatalogueService;
import pl.ab.gamemark.services.GameService;
import pl.ab.gamemark.services.ShopService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    GameCatalogueService gameCatalogueService;

    @Autowired
    AvaService avaService;

    @Autowired
    GameService gameService;


    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(shopService.findAll());
    }

    @PostMapping
    ResponseEntity<?> addShop(@RequestBody Shop shop) {
        shopService.save(shop);
        return ResponseEntity.created(URI.create("")).body(String.format("Utworzono sklep: %s", shop.getName()));
    }
    @GetMapping("/{id}")
    ResponseEntity<?>showGames(@PathVariable Long id){
        Optional<Shop> shop = shopService.findById(id);
        if (shop.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(shop.get().getGames());
        }
    }

    @PostMapping("/{id}/catalogue/{count}")
    ResponseEntity<?> addCatalogue(@RequestBody Game game, @PathVariable Long id, @PathVariable int count) {
        Game gameInBase = gameService.findGameByName(game.getName());
        if (gameInBase==null){
            gameService.save(game);
        }
        Optional<Shop> shop = shopService.findById(id);
        if (shop.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GamesCatalogue games = shop.get().getGames();
        if (games == null) {
            Availability av = new Availability(gameInBase, count);
            GamesCatalogue gc = new GamesCatalogue();
            gc.setGames(List.of(av));
            gameCatalogueService.save(gc);
            shopService.updateGameList(gc, id);
            return ResponseEntity.ok(String.format("stworzono katalog i dodano %s", gameInBase.getName()));
        } else {
            List<String> gameNames = shop.get().getGames().getGames().stream().map(s -> s.getGame().getName()).collect(Collectors.toList());
            if (gameNames.contains(gameInBase.getName())) {
                Game gameExist = gameService.findGameByName(game.getName());
                avaService.update(count, gameExist.getId());

                return ResponseEntity.ok(String.format("update gry w katalogu %s", gameInBase.getName()));
            } else {
                GamesCatalogue existCatalogue = shop.get().getGames();
                existCatalogue.getGames().add(new Availability(gameInBase, count));
                List<Availability> availabilities = avaService.;
                shopService.updateGameList(existCatalogue, shop.get().getId());
                return ResponseEntity.ok(String.format("dodano do katalogu %s", gameInBase.getName()));

            }

        }

    }
}
