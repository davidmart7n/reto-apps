package io.github.davidmart7n.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.davidmart7n.domain.Pokemon;

public class PokeApiClient {

    private final String BASE_URL;
    private HttpClient httpClient;
    ObjectMapper mapper = new ObjectMapper();

    public PokeApiClient() {
        httpClient = HttpClient.newHttpClient();
        BASE_URL="https://pokeapi.co/api/v2/pokemon/";
    }

    public JsonNode fetchPokemon(String nameOrId) throws Exception {


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + nameOrId))
                    .build();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            if(response.statusCode()>=200 && response.statusCode()<300){
                JsonNode jsonNode = mapper.readTree(response.body());
                return jsonNode;
            }
            throw new RuntimeException("Error en la llamada a la Pokedex");
    }
}

/*
package io.github.davidmart7n.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PokeApiClient {
    private final HttpClient http;
    private final ObjectMapper mapper;

    public PokeApiClient() {


        this.http = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public JsonNode fetchPokemon(String nameOrId) throws Exception {
        String url = "https://pokeapi.co/api/v2/pokemon/" + nameOrId.toLowerCase();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 200 && resp.statusCode() < 300) {
            return mapper.readTree(resp.body());
        }
        throw new RuntimeException("PokeAPI returned status " + resp.statusCode());
    }
} */
