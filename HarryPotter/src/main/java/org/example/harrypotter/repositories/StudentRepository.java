package org.example.harrypotter.repositories;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    List<Student> students = new ArrayList<>();
    public StudentRepository() {
        HouseRepository houseRepository = new HouseRepository();
        students.add(new Student("Harry Potter", houseRepository.getHouse("Gryffindor"), "Stag"));
        students.add(new Student("Hermione Granger", houseRepository.getHouse("Gryffindor"), "Otter"));
        students.add(new Student("Ron Weasley", houseRepository.getHouse("Gryffindor"), "Jack Russell Terrier"));
        students.add(new Student("Draco Malfoy", houseRepository.getHouse("Slytherin"), "None"));
        students.add(new Student("Luna Lovegood", houseRepository.getHouse("Ravenclaw"), "Hare"));
        students.add(new Student("Cedric Diggory", houseRepository.getHouse("Hufflepuff"), "Labrador"));
        students.add(new Student("Cho Chang", houseRepository.getHouse("Ravenclaw"), "Swan"));
        students.add(new Student("Neville Longbottom", houseRepository.getHouse("Gryffindor"), "None"));
        students.add(new Student("Ginny Weasley", houseRepository.getHouse("Gryffindor"), "Horse"));
        students.add(new Student("Seamus Finnigan", houseRepository.getHouse("Gryffindor"), "Fox"));
        students.add(new Student("Pansy Parkinson", houseRepository.getHouse("Slytherin"), "None"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public void createStudent(House house, Student student) {
        student.setHouse(house);
        students.add(student);

    }

    public void updateStudent(String name, Student student, House house) {
        for( Student s : students ){
            if(s.getName().equals(name)){
                s.setName(student.getName());
                s.setHouse(house);
                s.setPatronus(student.getPatronus());
            }
        }
    }
}