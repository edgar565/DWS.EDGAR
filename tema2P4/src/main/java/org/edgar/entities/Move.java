package org.edgar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {
    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "\n   -name='" + name + '\'';


    }
}