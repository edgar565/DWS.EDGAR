package org.edgar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.edgar.entities.ListPokemon;

import java.util.Scanner;

public class PokemonApi {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Pofavor introduce el nombre de un pokemon: ");
        String name = scanner.nextLine();
        try{
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            JsonNode jsonNode = objectMapper.readTree("https://pokeapi.co/api/v2/pokemon");
            ListPokemon listPokemon = objectMapper.readTree(jsonNode.get("results").traverse(),);

        } catch (Exception e){
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }


    }
}