package org.edgar;

import java.sql.*;
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
                    getStudentsWithouPets(connection);
                    break;
                case 5:
                    averageCalificationsPerStudent(connection);
                    break;
                case 6:
                    System.out.println(studentsFifthYearForHome(connection));
                    break;
                case 7:
                    String subject = showMenuSubjects();
                    bestBestScoreBySubject(connection, subject);
                    break;
                case 8:
                    subject = showMenuSubjects();
                    averageScoresByAssignmentHouse(connection, subject);
                    break;
                case 9:
                    increaseCalificationLastYear(connection);
                    break;
                case 10:
                    delSubjectStudents(connection);
                    break;
            }
        }
    }
    public static String showMenuSubjects() {
        System.out.println("Por favor, elige una asignatura ingresando el número correspondiente:");
        System.out.println("1. Transformaciones");
        System.out.println("2. Pociones");
        System.out.println("3. Encantamientos");
        System.out.println("4. Defensa Contra las Artes Oscuras");
        System.out.println("5. Herbología");
        System.out.println("6. Historia de la Magia");
        System.out.println("7. Astronomía");
        System.out.println("8. Adivinación");
        System.out.println("9. Cuidado de Criaturas Mágicas");
        System.out.println("10. Estudios Muggles");
        System.out.println("11. Aritmancia");
        System.out.println("12. Runas Antiguas");
        System.out.println("13. Alquimia");
        System.out.println("14. Aparición");
        System.out.println("15. Vuelo");
        System.out.println("16. Estudios Avanzados de Aritmancia");

        int opcion = scanner.nextInt();

        String subject;
        switch (opcion) {
            case 1:
                subject = "Transformaciones";
                break;
            case 2:
                subject = "Pociones";
                break;
            case 3:
                subject = "Encantamientos";
                break;
            case 4:
                subject = "Defensa Contra las Artes Oscuras";
                break;
            case 5:
                subject = "Herbología";
                break;
            case 6:
                subject = "Historia de la Magia";
                break;
            case 7:
                subject = "Astronomía";
                break;
            case 8:
                subject = "Adivinación";
                break;
            case 9:
                subject = "Cuidado de Criaturas Mágicas";
                break;
            case 10:
                subject = "Estudios Muggles";
                break;
            case 11:
                subject = "Aritmancia";
                break;
            case 12:
                subject = "Runas Antiguas";
                break;
            case 13:
                subject = "Alquimia";
                break;
            case 14:
                subject = "Aparición";
                break;
            case 15:
                subject = "Vuelo";
                break;
            case 16:
                subject = "Estudios Avanzados de Aritmancia";
                break;
            default:
                subject = "Opción inválida";
                break;
        }
        return subject;
    }

    public static List<String> getStudentsAndHouses(Connection connection) {
        List<String> resultString = new ArrayList<>();
        String query = "SELECT e.nombre, e.apellido, c.nombre_casa FROM Estudiante e " +
                "JOIN Casa c ON e.id_casa = c.id_casa";
        try {
            PreparedStatement result = connection.prepareStatement(query);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                resultString.add("\nEstudiante: " + rs.getString("nombre") + " " + rs.getString("apellido") +
                        ", Casa: " + rs.getString("nombre_casa"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }

    public static void getStudentsAndPets(Connection connection) {
        String query = "SELECT e.nombre, e.apellido, m.nombre_mascota AS nombre_mascota FROM Estudiante e " +
                "LEFT JOIN Mascota m ON e.id_estudiante = m.id_estudiante";
        try {
            PreparedStatement result = connection.prepareStatement(query);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                String nombreEstudiante = rs.getString("nombre") + " " + rs.getString("apellido");
                String nombreMascota = rs.getString("nombre_mascota");

                if (nombreMascota != null) {
                    System.out.println("Estudiante: " + nombreEstudiante + ", Mascota: " + nombreMascota);
                } else {
                    System.out.println("Estudiante: " + nombreEstudiante + ", Mascota: Ninguna");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void getStudentsAndPetsIfHasPet(Connection connection) {
        List<String> resultStudentsHasPets = new ArrayList<>();
        String query = "SELECT e.nombre, e.apellido, m.nombre_mascota AS nombre_mascota FROM Estudiante e " +
                "JOIN Mascota m ON e.id_estudiante = m.id_estudiante";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombreEstudiante = rs.getString("nombre") + " " + rs.getString("apellido");
                String nombreMascota = rs.getString("nombre_mascota");
                if (nombreMascota != null) {
                    resultStudentsHasPets.add("\nEstudiante: " + nombreEstudiante + ", Mascota: " + nombreMascota);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(resultStudentsHasPets);
    }

    public static void getStudentsWithouPets(Connection connection) {
        String query = "SELECT e.nombre, e.apellido FROM Estudiante e " +
                "LEFT JOIN Mascota m ON e.id_estudiante = m.id_estudiante " +
                "WHERE m.id_estudiante IS NULL";
        try {
            PreparedStatement result = connection.prepareStatement(query);
            ResultSet rs = result.executeQuery();
                while (rs.next()) {
                    System.out.println("Estudiante sin mascota: " + rs.getString("nombre") + " " + rs.getString("apellido"));
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void averageCalificationsPerStudent(Connection connection) {
        String query = "SELECT e.nombre, e.apellido, AVG(ea.calificacion) AS promedio " +
                "FROM Estudiante e JOIN Estudiante_Asignatura ea ON e.id_estudiante = ea.id_estudiante " +
                "GROUP BY e.nombre, e.apellido";
        try {
             PreparedStatement result = connection.prepareStatement(query);
             ResultSet rs = result.executeQuery();
            while (rs.next()) {
                System.out.println("Estudiante: " + rs.getString("nombre") + " " + rs.getString("apellido") +
                        ", Promedio: " + rs.getDouble("promedio"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> studentsFifthYearForHome(Connection connection) {
        List<String> resultStudentsFifthYear = new ArrayList<>();
        String query = "SELECT c.nombre_casa, COUNT(e.id_estudiante) AS num_estudiantes " +
                "FROM Estudiante e JOIN Casa c ON e.id_casa = c.id_casa " +
                "WHERE e.año_curso = 5 GROUP BY c.nombre_casa";
        try {
             PreparedStatement result = connection.prepareStatement(query);
             ResultSet rs = result.executeQuery();
            while (rs.next()) {
                resultStudentsFifthYear.add("\nCasa: " + rs.getString("nombre_casa") + ", " +
                        "Número de estudiantes en quinto año: " + rs.getInt("num_estudiantes"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultStudentsFifthYear;
    }
    public static void bestBestScoreBySubject(Connection connection, String subject) {
        String query = "SELECT e.nombre, e.apellido, MAX(ea.calificacion) AS mejor_calificacion " +
                "FROM Estudiante e JOIN Estudiante_Asignatura ea ON e.id_estudiante = ea.id_estudiante " +
                "JOIN Asignatura a ON ea.id_asignatura = a.id_asignatura WHERE a.nombre_asignatura = ? " +
                "GROUP BY e.nombre, e.apellido ORDER BY mejor_calificacion DESC LIMIT 1";
        try {
             PreparedStatement result = connection.prepareStatement(query);
            result.setString(1, subject);
            try (ResultSet rs = result.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nEstudiante: " + rs.getString("nombre") + " " + rs.getString("apellido") + ", Mejor calificación en " + subject + ": " + rs.getDouble("mejor_calificacion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void averageScoresByAssignmentHouse(Connection connection, String subject) {
        String query = "SELECT c.nombre_casa, AVG(ea.calificacion) AS promedio " +
                "FROM Estudiante e JOIN Estudiante_Asignatura ea ON e.id_estudiante = ea.id_estudiante " +
                "JOIN Casa c ON e.id_casa = c.id_casa " +
                "JOIN Asignatura a ON ea.id_asignatura = a.id_asignatura WHERE a.nombre_asignatura = ? " +
                "GROUP BY c.nombre_casa";
        try {
             PreparedStatement result = connection.prepareStatement(query);
            result.setString(1, subject);
            try (ResultSet rs = result.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Casa: " + rs.getString("nombre_casa") + ", Promedio en " + subject + ": " + rs.getDouble("promedio"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void increaseCalificationLastYear(Connection connection) {
        String query = "UPDATE Estudiante_Asignatura SET calificacion = calificacion * 1.10 " +
                "WHERE id_estudiante IN (SELECT id_estudiante FROM Estudiante WHERE año_curso = 5)";
        try {
             PreparedStatement result = connection.prepareStatement(query);
            int rowsUpdated = result.executeUpdate();
            System.out.println("Calificaciones actualizadas para " + rowsUpdated + " estudiantes.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delSubjectStudents(Connection connection) {
        String query = "DELETE FROM Estudiante_Asignatura WHERE calificacion < 5 AND " +
                "id_asignatura IN (SELECT id_asignatura FROM Asignatura WHERE obligatoria = false)";
        try {
             PreparedStatement result = connection.prepareStatement(query);
            int rowsDeleted = result.executeUpdate();
            System.out.println("Desmatriculadas " + rowsDeleted + " asignaturas optativas.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}