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
    private String lastName;
    private int idHouse;
    private int course;
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + lastName + '\'' +
                ", idHouse=" + idHouse +
                ", ageHouse=" + course +
                ", birthDate=" + birthDate +
                '}';
    }
}
