package org.edgar;

import org.w3c.dom.ls.LSInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://db-dws.cqpz9tc1anof.us-east-1.rds.amazonaws.com:5432/e3333";
        String user = "postgres";
        String password = "taller2014";
        try (Connection connection = DriverManager.getConnection(urlConexion, user, password)) {
            menu(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menu(Connection connection) {
        int option = 0;
        while (option != 11) {
            System.out.print("\n #####  MENU  #####" +
                    "\n1. Consulta de estudiantes y sus casas" +
                    "\n2. Consulta de estudiantes y sus mascotas" +
                    "\n3. Consulta de estudiantes y sus mascotas solo si teien mascota" +
                    "\n4. Consulta de estudiantes sin mascotas" +
                    "\n5. Notas promedio por estudiante" +
                    "\n6. Número de estudiantes en quinto año" +
                    "\n7. Consulta de las mejores calificaciones en una asignatura en particular" +
                    "\n8. Promedio de calificaciones en una asignatura para una casa" +
                    "\n9. Actualizar las calificaciones" +
                    "\n10. Desmatricular estudiantes" +
                    "\n11. Salir" +
                    "\nPorfavor escoje una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println(getStudentsAndHouses(connection));
                    break;
                case 2:
                    getStudentsAndPets(connection);
                    break;
                case 3:
                    getStudentsAndPetsIfHasPet(connection);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        }
    }

    public static List<String> getStudentsAndHouses(Connection connection) {
        List<String> resultString = new ArrayList<>();
        String query = "SELECT e.nombre, e.apellido, c.nombre_casa FROM Estudiante e " +
                "JOIN Casa c ON e.id_casa = c.id_casa";
        try {
            PreparedStatement result = connection.prepareStatement(query);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                resultString.add("Estudiante: " + rs.getString("nombre") + " " + rs.getString("apellido") +
                        ", Casa: " + rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static void getStudentsAndPets(Connection connection) {
        List<String> resultStudentsAndPets = new ArrayList<>();
        String query = "SELECT e.nombre, e.apellido, m.nombre_mascota AS mascota FROM Estudiante e " +
                "LEFT JOIN Mascota m ON e.id_estudiante = m.id_estudiante";
        try {
             PreparedStatement result = connection.prepareStatement(query);
             ResultSet rs = result.executeQuery();
            while (rs.next()) {
                resultStudentsAndPets.add("Estudiante: " + rs.getString("nombre" + " " + rs.getString("apellido") +
                        ", Mascota: " + rs.getString("nombre_mascota")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultStudentsAndPets);
    }

    public static void getStudentsAndPetsIfHasPet(Connection connection) {
        List<String> resultStudentsHasPets = new ArrayList<>();
        String query = "SELECT e.nombre, e.apellido, m.nombre_mascota AS mascota FROM Estudiante e " +
                "JOIN Mascota m ON e.id_estudiante = m.id_estudiante";
        try {
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultStudentsHasPets.add("Estudiante: " + rs.getString("nombre" + " " + rs.getString("apellido") +
                        ", Mascota: " + rs.getString("mascota")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultStudentsHasPets);
    }
}
