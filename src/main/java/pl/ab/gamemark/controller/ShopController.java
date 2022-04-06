package pl.ab.gamemark.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.ab.gamemark.entity.Availability;
import pl.ab.gamemark.entity.Game;
import pl.ab.gamemark.entity.GamesCatalogue;
import pl.ab.gamemark.entity.JSON.GamesFromRawgContainer;
import pl.ab.gamemark.entity.Shop;
import pl.ab.gamemark.services.AvaService;
import pl.ab.gamemark.services.GameCatalogueService;
import pl.ab.gamemark.services.GameService;
import pl.ab.gamemark.services.ShopService;

import java.io.IOException;
import java.net.URI;
import java.util.List;
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
            Availability av = new Availability(gameService.findGameByName(game.getName()), count);
            GamesCatalogue gc = new GamesCatalogue();
            gc.setGames(List.of(av));
            gameCatalogueService.save(gc);
            shopService.updateGameList(gc, id);
            return ResponseEntity.ok(String.format("stworzono katalog i dodano %s", game.getName()));
        } else {
            GamesCatalogue existCatalogue = shop.get().getGames();
            List<String> gameNames = shop.get().getGames().getGames().stream().map(s -> s.getGame().getName()).collect(Collectors.toList());
            if (gameNames.contains(game.getName())) {
                Game gameExist = gameService.findGameByName(game.getName());
                avaService.update(count, gameExist.getId());
                for (Availability g : existCatalogue.getGames()){
                    if (g.getGame().getName().equals(game.getName())){
                        g.setCount(count);
                    }
                }

                shopService.updateGameList(existCatalogue, shop.get().getId());
                return ResponseEntity.ok(String.format("update gry w katalogu %s", game.getName()));
            } else {
                Availability newGameInCatalogue = new Availability(gameService.findGameByName(game.getName()), count);
                avaService.save(newGameInCatalogue);
                existCatalogue.getGames().add(newGameInCatalogue);
                shopService.updateGameList(existCatalogue, shop.get().getId());

                return ResponseEntity.ok(String.format("dodano do katalogu %s", game.getName()));

            }

        }

    }

    @DeleteMapping("/{id}/catalogue/{game}")
    ResponseEntity<?> deleteFromCatalog(@PathVariable Long id, @PathVariable String game) {
        Optional<Shop> shop = shopService.findById(id);
        Game gameInBase = gameService.findGameByName(game);
        if (shop.isPresent()){
            Availability gameToDelete = shop.get().getGames().getGames().stream().filter(g->g.getGame().getName().equals(game)).findFirst().get();
            GamesCatalogue gamesCatalogue = shop.get().getGames();
            gamesCatalogue.getGames().remove(gameToDelete);
            shopService.updateGameList(gamesCatalogue,id);
            avaService.delete(gameToDelete);
            return ResponseEntity.ok(String.format("usunieto z katalogu gre %s",game));
        }else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rawg/{name}")
    ResponseEntity<?>getGamesFromApi(@PathVariable String name) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://api.rawg.io/api/games/716907?key=04add8db816643eebd5ab44e162b68ef")
//                .get()
//                .build();
//        Response response = client.newCall(request).execute();
        RestTemplate restTemplate = new RestTemplate();
        GamesFromRawgContainer games = restTemplate.getForObject(String.format("https://api.rawg.io/api/games/%s?key=04add8db816643eebd5ab44e162b68ef",name), pl.ab.gamemark.entity.JSON.GamesFromRawgContainer.class);
       return ResponseEntity.ok(games);
    }

}
