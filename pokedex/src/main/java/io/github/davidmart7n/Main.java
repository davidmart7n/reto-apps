package io.github.davidmart7n;

import io.github.davidmart7n.client.PokeApiClient;
import io.github.davidmart7n.domain.Pokemon;
import io.github.davidmart7n.repository.FilePokemonRepository;
import io.github.davidmart7n.repository.PokemonRepository;
import io.github.davidmart7n.service.PokemonService;
import io.github.davidmart7n.service.PokemonServiceImpl;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        PokemonRepository repo = new FilePokemonRepository(Paths.get("pokemons.json"));
        PokeApiClient client = new PokeApiClient();
        PokemonServiceImpl service = new PokemonServiceImpl(client, repo);

        String toCapture = args.length > 0 ? args[0] : "dragonite";
        try {
            Pokemon p = service.capturePokemon(toCapture);
            System.out.println("Captured: id=" + p.getId() + ", name=" + p.getName());
        } catch (Exception e) {
            System.err.println("Error capturing pokemon: " + e.getMessage());
            e.printStackTrace();
        }
    }
}