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
        return "Pokemon{" +
                "baseExperience=" + baseExperience +
                ", height=" + height +
                ", id=" + id +
                ", moves=" + moves +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", types=" + types +
                ", weight=" + weight +
                '}';
    }
}