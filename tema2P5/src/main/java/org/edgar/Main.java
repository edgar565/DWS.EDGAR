package org.edgar;

import org.edgar.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://db-dws.cqpz9tc1anof.us-east-1.rds.amazonaws.com:5432/e3333";
        String user = "postgres";
        String password = "taller2014";
        List<Student> students = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();
        List<StudentSubject> studentSubjects = new ArrayList<>();
        readDataBase(urlConexion, user, password, students, subjects, houses, teachers, pets, studentSubjects);
        menu(students, subjects, houses, teachers, pets, studentSubjects, urlConexion, user, password);
    }

    public static void menu(List<Student> students, List<Subject> subjects, List<House> houses, List<Teacher> teachers, List<Pet> pets, List<StudentSubject> studentSubjects, String urlConexion, String user, String password) {
        int option = 55;
        while (option != 0) {
            System.out.print("\n #####  MENU  #####" +
                    "\n1. Comprobar estudiantes por casa" +
                    "\n2. Listado de todas las asignaturas obligatorias" +
                    "\n3. Obtener la mascota especifica de estudiante" +
                    "\n4. Listar los estudiantes que no tienen mascota" +
                    "\n5. Promedio de calificaciones de estudiante" +
                    "\n6. Número de estudiantes por casa" +
                    "\n7. Estudiantes matriculados en asignatura especifica" +
                    "\n8. Insertar nuevo estudiante" +
                    "\n9. Modificar aula de asignatura" +
                    "\n0. Salir" +
                    "\nPorfavor escoje una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("\n1. Gryffindor" +
                            "\n2. Hufflepuff" +
                            "\n3. Ravenclaw " +
                            "\n4. Slytherin" +
                            "\nPorfavor introduce el nombre de la casa:");
                    int idHouseOption = scanner.nextInt();
                    showStudentsHouse(idHouseOption, houses, students);
                    break;
                case 2:
                    System.out.println("Asignaturas obligatorias:");
                    System.out.print(showSubjectsObligatory(subjects));
                    break;
                case 3:
                    System.out.print("Porfavor introduce el nombre del estudiante:");
                    String nameStudent = scanner.next();
                    System.out.println("Mascota del estudiante:");
                    System.out.println(showPetOfStudent(nameStudent, students, pets));
                    break;
                case 4:
                    System.out.println("Estudiantes sin mascota:");
                    System.out.println(showStudentsWithoutPet(students, pets));
                    break;
                case 5:
                    System.out.print("Porfavor introduce el nombre del estudiante:");
                    nameStudent = scanner.next();
                    System.out.println("Promedio de calificación de estudiante:");
                    System.out.println("Promedio de calificación: " + showAverageCalificationStudent(nameStudent, students, studentSubjects));
                    break;
                case 6:
                    showNumberStudentsByHouse(houses, students);
                    break;
                case 7:
                    System.out.print("Porfavor introduce el nombre de la asignatura:");
                    String nameSubject = scanner.next();
                    System.out.println(showStudentsInSubjectSpecific(nameSubject, subjects, studentSubjects, students));
                    break;
                case 8:
                    System.out.print("Porfavor introduce el nombre del estudiante:");
                    nameStudent = scanner.next();
                    System.out.print("Porfavor introduce el apellido:");
                    String surname = scanner.next();
                    System.out.print("Porfavor introduce el id de la casa:");
                    int idHouse = scanner.nextInt();
                    System.out.print("Porfavor introduce el curso:");
                    int course = scanner.nextInt();
                    System.out.print("Porfavor introduce la fecha de nacimiento:");
                    LocalDate birthDate = LocalDate.parse(scanner.next());
                    Student student = new Student(0, nameStudent, surname, idHouse, course, birthDate);
                    insertStudent(students, student, urlConexion, user, password);
                    break;
                case 9:
                    System.out.print("Porfavor introduce el id de la asignatura:");
                    int idSubject = scanner.nextInt();
                    System.out.print("Porfavor introduce la nueva aula:");
                    String classRoom = scanner.next();
                    modifyClassRoom(idSubject, classRoom, urlConexion, user, password, subjects);
                    break;
                case 10:
                    System.out.print("Porfavor introduce el id del estudiante:");
                    int idStudent = scanner.nextInt();
                    System.out.print("Porfavor introduce el id de la asignatura:");
                    idSubject = scanner.nextInt();
                    delStudentOfSubject(idStudent, idSubject, urlConexion, user, password, studentSubjects);
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }

    }

    public static void readDataBase(String url, String user, String password, List<Student> students, List<Subject> subjects, List<House> houses, List<Teacher> teachers, List<Pet> pets, List<StudentSubject> studentSubjects) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            try {
                String sql = "SELECT * FROM Estudiante";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Student student = new Student(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), result.getDate(6).toLocalDate());
                    students.add(student);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT * FROM Asignatura";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Subject subject = new Subject(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4));
                    subjects.add(subject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT * FROM Casa";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    House house = new House(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                    houses.add(house);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT * FROM Mascota";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Pet pet = new Pet(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                    pets.add(pet);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT * FROM Profesor";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Teacher teacher = new Teacher(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDate(5).toLocalDate());
                    teachers.add(teacher);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT * FROM Estudiante_Asignatura";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    StudentSubject studentSubject = new StudentSubject(result.getInt(1), result.getInt(2), result.getDouble(3));
                    studentSubjects.add(studentSubject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showStudentsHouse(int idHouseOption, List<House> houses, List<Student> students) {
        String houseName = "";
        switch (idHouseOption) {
            case 1:
                houseName = "Gryffindor";
                break;
            case 2:
                houseName = "Hufflepuff";
                break;
            case 3:
                houseName = "Ravenclaw";
                break;
            case 4:
                houseName = "Slytherin";
                break;
            default:
                System.out.println("Casa no encontrada");
        }
        int idHouse = 0;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                idHouse = house.getId();
                break;
            }
        }
        for (Student student : students) {
            if (student.getIdHouse() == idHouse) {
                System.out.println(student);
            }
        }
    }
    public static List<Subject> showSubjectsObligatory(List<Subject> subjects) {
        List<Subject> subjectsObligatory = new ArrayList<>();
        for (Subject subject : subjects) {
            if(subject.getObligatory()) {
                subjectsObligatory.add(subject);
            }
        }
        return subjectsObligatory;
    }
    public static Pet showPetOfStudent(String nameStudent, List<Student> students, List<Pet> pets) {
        Pet petReturn = null;
        for (Student student : students) {
            if (student.getName().equals(nameStudent)) {
                int idStudent = student.getId();
                for (Pet pet : pets) {
                    if (pet.getIdStudent() == idStudent) {
                        petReturn = pet;
                        break;
                    }
                }
            }
        }
        return petReturn;
    }
    public static List<Student> showStudentsWithoutPet(List<Student> students, List<Pet> pets) {
        List<Student> studentsWithoutPet = new ArrayList<>();
        for (Student student : students) {
            boolean found = false;
            for (Pet pet : pets) {
                if (pet.getIdStudent() == student.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                studentsWithoutPet.add(student);
            }
        }
        return studentsWithoutPet;
    }
    public static double showAverageCalificationStudent(String nameStudent, List<Student> students, List<StudentSubject> studentSubjects) {
        double average = 0;
        for (Student student : students) {
            if (student.getName().equals(nameStudent)) {
                int idStudent = student.getId();
                int count = 0;
                for (StudentSubject studentSubject : studentSubjects) {
                    if (studentSubject.getIdStudent() == idStudent) {
                        average += studentSubject.getGrade();
                        count++;
                    }
                }
                average = average / count;
            }
        }
        return average;
    }
    public static void showNumberStudentsByHouse(List<House> houses, List<Student> students) {
        for(House house : houses) {
            int count = 0;
            for(Student student : students) {
                if(student.getIdHouse() == house.getId()) {
                    count++;
                }
            }
            System.out.println(house.getName() + ": " + count);
        }
    }
    public static List<Student> showStudentsInSubjectSpecific(String nameSubject, List<Subject> subjects, List<StudentSubject> studentSubjects, List<Student> students) {
        List<Student> studentsReturn = new ArrayList<>();
        for (Subject subject : subjects) {
            if (subject.getName().equals(nameSubject)) {
                int idSubject = subject.getId();
                for (StudentSubject studentSubject : studentSubjects) {
                    if (studentSubject.getIdSubject() == idSubject) {
                        int idStudent = studentSubject.getIdStudent();
                        for (Student student : students) {
                            if (student.getId() == idStudent) {
                                studentsReturn.add(student);
                                break;
                            }
                        }
                    }
                }

            }
        }
        return studentsReturn;
    }
    public static void insertStudent(List<Student> students, Student student, String url, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Estudiante (id_estudiante, nombre, apellido, id_casa, año_curso, fecha_nacimiento) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getIdHouse());
            ps.setInt(4, student.getCourse());
            ps.setDate(5, java.sql.Date.valueOf(student.getBirthDate()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = students.size() + 1;
        student.setId(id);
        students.add(student);
    }
    public static void modifyClassRoom(int idSubject, String classRoom, String url, String user, String password, List<Subject> subjects) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE Asignatura SET id_asignatura = ? WHERE aula = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idSubject);
            ps.setString(2, classRoom);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Subject subject : subjects) {
            if (subject.getId() == idSubject) {
                subject.setClassRoom(classRoom);
            }
        }
    }
    public static void delStudentOfSubject(int idStudent, int idSubject, String url, String user, String password, List<StudentSubject> studentSubjects) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM EstudianteAsignatura WHERE id_estudiante = ? AND id_asignatura = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idStudent);
            ps.setInt(2, idSubject);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (StudentSubject studentSubject : studentSubjects) {
            if (studentSubject.getIdStudent() == idStudent && studentSubject.getIdSubject() == idSubject) {
                studentSubjects.remove(studentSubject);
                break;
            }
        }

    }
}