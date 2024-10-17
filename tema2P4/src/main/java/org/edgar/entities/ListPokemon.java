package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class ListPokemon {
    private List<PokemonData> result;

    @Override
    public String toString() {
        return "\nListPokemon{" + result + "}";


    }
}