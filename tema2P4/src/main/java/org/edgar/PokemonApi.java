package org.edgar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.edgar.entities.ListPokemon;
import org.edgar.entities.Pokemon;

import java.net.URL;
import java.util.Scanner;

public class PokemonApi {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            String url = "https://pokeapi.co/api/v2/pokemon/";
            ListPokemon listPokemon;
            Pokemon pokemon;
            try{
                JsonNode jsonNodeListPokemon = objectMapper.readTree(new URL(url));
                listPokemon = new ListPokemon(objectMapper.readValue(jsonNodeListPokemon.get("results").traverse(), new TypeReference<>(){}));
                System.out.println(listPokemon);
            } catch (Exception e){
                System.out.println("Error al cargar los datos.");
                throw new RuntimeException(e);
            }
            System.out.println("Pofavor introduce el nombre o el id de un pokemon: ");
            String name = scanner.nextLine().toLowerCase();
            if (name.equals("exit")) break;
            try{
                JsonNode jsonNodePokemon = objectMapper.readTree(new URL(url + name));
                pokemon = objectMapper.readValue(jsonNodePokemon.traverse(), Pokemon.class);
                System.out.println(pokemon);
            } catch (Exception e){
                System.out.println("Error al cargar los datos.");
                throw new RuntimeException(e);
            }
        }

    }
}