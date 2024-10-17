package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class House {
    private int id;
    private String name;
    private String founder;
    private String headOfHouse;
    private String ghost;
}
