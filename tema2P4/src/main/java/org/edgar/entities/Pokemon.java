package org.edgar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int baseExperience;
    private int height;
    private int id;
    private List<Moves> moves;
    private String name;
    private int order;
    private List<Types> types;
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