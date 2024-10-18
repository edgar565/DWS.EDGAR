package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private int id;
    private String name;
    private String surname;
    private int idHouse;
    private int ageHouse;
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", idHouse=" + idHouse +
                ", ageHouse=" + ageHouse +
                ", birthDate=" + birthDate +
                '}';
    }
}
