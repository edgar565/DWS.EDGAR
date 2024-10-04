package org.edgar;

import org.edgar.entities.Animal;
import org.edgar.entities.AnimalProtector;
import org.edgar.entities.XMLManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnimalProtector animalProtector = new AnimalProtector(new ArrayList<>());
        Scanner scanner = new Scanner(System.in);
        XMLManager xmlManager = new XMLManager();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Cargar información de fichero XML");
            System.out.println("2. Guardar información en fichero XML");
            System.out.println("3. Añadir animal");
            System.out.println("4. Borrar animal");
            System.out.println("5. Consultar animal");
            System.out.println("6. Mostrar todos los animales");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    xmlManager.readXML().forEach(System.out::println);
                    System.out.println("Cargando datos de XML...");
                case 2:

                case 3:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Especie: ");
                    String species = scanner.nextLine();
                    System.out.print("Edad: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Sexo: ");
                    String sex = scanner.nextLine();
                    System.out.print("Fecha de ingreso (yyyy-MM-dd): ");
                    String entryDate = scanner.nextLine();
                    System.out.print("¿Adoptado? (Si/No): ");
                    String adopted = scanner.nextLine();

                    Animal nuevoAnimal = new Animal(id, name, species, age, sex, entryDate, adopted);
                    animalProtector.addAnimal(nuevoAnimal);
                    System.out.println("Animal añadido con éxito.");
                    break;

                case 4:
                    System.out.print("Introduce el ID del animal a borrar: ");
                    String idDel = scanner.nextLine();
                    animalProtector.delAnimal(idDel);
                    System.out.println("Animal borrado.");
                    break;

                case 5:
                    System.out.print("Introduce el ID del animal a consultar: ");
                    String idSearch = scanner.nextLine();
                    Animal animal = animalProtector.searchAnimal(idSearch);
                    if (animal != null) {
                        System.out.println(animal);
                    } else {
                        System.out.println("Animal no encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("Animales en la animalProtector:");
                    System.out.println(animalProtector);
                    break;

                case 7:
                    exit = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}