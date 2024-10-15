package org.edgar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    @JsonProperty("base_experience")
    private int baseExperience;
    @JsonProperty("height")
    private int height;
    @JsonProperty("id")
    private int id;
    @JsonProperty("moves")
    private List<Moves> moves;
    @JsonProperty("name")
    private String name;
    @JsonProperty("order")
    private int order;
    @JsonProperty("types")
    private List<Types> types;
    @JsonProperty("weight")
    private String weight;

    @Override
    public String toString() {
        StringBuilder moveString = new StringBuilder();
        for (Moves moves : moves) {
            moveString.append(moves.getMove().toString());
        }
        StringBuilder typeString = new StringBuilder();
        for (Types types : types) {
            typeString.append(types.getType().toString());
        }
        return "Pokemon{" +
                "\n  baseExperience=" + baseExperience +
                "\n  height=" + height +
                "\n  id=" + id +
                "\n  moves {" + moveString +
                "\n}\n  name='" + name + '\'' +
                "\n  order=" + order +
                "\n  types {" + typeString +
                "\n}\n  weight=" + weight +
                '}';
    }
}