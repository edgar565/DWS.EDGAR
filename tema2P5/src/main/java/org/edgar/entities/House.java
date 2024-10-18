package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", founder='" + founder + '\'' +
                ", headOfHouse='" + headOfHouse + '\'' +
                ", ghost='" + ghost + '\'' +
                '}';
    }
}
