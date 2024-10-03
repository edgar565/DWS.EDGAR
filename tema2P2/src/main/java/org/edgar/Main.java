package org.edgar;

import org.edgar.entities.Animal;
import org.edgar.entities.AnimalProtector;
import org.edgar.entities.XMLManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        XMLManager xmlManager = new XMLManager();
        AnimalProtector animalProtector;
        Scanner scanner = new Scanner(System.in);

        try {
            animalProtector = xmlManager.readXML();
            System.out.println("Cargando datos de XML...");
        } catch (IOException e) {
            animalProtector = new AnimalProtector(new ArrayList<>());
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Añadir animal");
            System.out.println("2. Borrar animal");
            System.out.println("3. Consultar animal");
            System.out.println("4. Mostrar todos los animales");
            System.out.println("5. Guardar datos en XML");
            System.out.println("6. Salir");

            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
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

                case 2:
                    System.out.print("Introduce el ID del animal a borrar: ");
                    String idDel = scanner.nextLine();
                    animalProtector.delAnimal(idDel);
                    System.out.println("Animal borrado.");
                    break;

                case 3:
                    System.out.print("Introduce el ID del animal a consultar: ");
                    String idSearch = scanner.nextLine();
                    Animal animal = animalProtector.searchAnimal(idSearch);
                    if (animal != null) {
                        System.out.println(animal);
                    } else {
                        System.out.println("Animal no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Animales en la animalProtector:");
                    System.out.println(animalProtector);
                    break;

                case 5:
                    try {
                        xmlManager.writeXML(animalProtector);
                        System.out.println("Datos guardados en el XML.");
                    } catch (Exception e) {
                        System.out.println("Error al guardar los datos.");
                    }
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}