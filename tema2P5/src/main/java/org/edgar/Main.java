package org.edgar;

import org.edgar.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://db-dws.cqpz9tc1anof.us-east-1.rds.amazonaws.com";
        String user = "postgres";
        String password = "taller2014";
        List<Student> students = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Pet>  pets = new ArrayList<>();
        List<StudentSubject> studentSubjects = new ArrayList<>();
        readDataBase(urlConexion, user, password, students, subjects, houses, teachers, pets, studentSubjects);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1. Comprobar estudiantes de la casa Gryffindor" +
                "\n2. Listado de todas las asignaturas obligatorias" +
                "\n3. Obtener la mascota especifica de estudiante" +
                "\n4. Listar los estudiantes que no tienen mascota" +
                "\n5. Promedio de calificaciones de estudiante" +
                "\n");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
            case 2:


    }
    public static void readDataBase(String url, String user, String password, List<Student> students, List<Subject> subjects, List<House> houses, List<Teacher> teachers, List<Pet> pets, List<StudentSubject> studentSubjects) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            try{
                String sql = "SELECT * FROM Estudiante";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Student student = new Student(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), result.getDate(6).toLocalDate());
                    students.add(student);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                String sql = "SELECT * FROM Asignatura";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Subject subject = new Subject(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4));
                    subjects.add(subject);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                String sql = "SELECT * FROM Casa";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    House house = new House(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                    houses.add(house);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                String sql = "SELECT * FROM Mascota";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Pet pet = new Pet(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                    pets.add(pet);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                String sql = "SELECT * FROM Profesor";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Teacher teacher = new Teacher(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4).toLocalDate(), result.getInt(5));
                    teachers.add(teacher);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                String sql = "SELECT * FROM Estudiante_Asignatura";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    StudentSubject studentSubject = new StudentSubject(result.getInt(1), result.getInt(2), result.getDouble(3));
                    studentSubjects.add(studentSubject);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showStudentsGryffindor(){

    }
}