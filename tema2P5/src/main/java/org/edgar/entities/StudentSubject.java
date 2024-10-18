package org.edgar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentSubject {
    private int idStudent;
    private int idSubject;
    private double grade;

    @Override
    public String toString() {
        return "StudentSubject{" +
                "idStudent=" + idStudent +
                ", idSubject=" + idSubject +
                ", grade=" + grade +
                '}';
    }

}
