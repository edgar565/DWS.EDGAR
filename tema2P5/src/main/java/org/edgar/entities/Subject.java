package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Subject {
    private int id;
    private String name;
    private String classRoom;
    private Boolean obligatory;

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classRoom='" + classRoom + '\'' +
                "}";
    }
}
