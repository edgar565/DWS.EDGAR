package org.edgar;

import org.edgar.entities.Animal;
import org.edgar.entities.AnimalShelter;
import org.edgar.entities.XMLManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XMLManager xmlManager = new XMLManager();
        AnimalShelter animalShelter = new AnimalShelter(new ArrayList<>());

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
                    animalShelter.setAnimals(xmlManager.readXML());
                    System.out.println("Cargando datos de XML...");
                    break;
                case 2:
                    xmlManager.writeXML(animalShelter);
                    System.out.println("Guardando datos en XML...");
                    break;
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

                    Animal animal = new Animal(id, name, species, age, sex, entryDate, adopted);
                    animalShelter.addAnimal(animal);
                    System.out.println("Añadiendo animal...");
                    break;
                case 4:
                    System.out.println("Por favor, introduce el ID del animal que quieres borrar: ");
                    String idToDelete = scanner.nextLine();
                    animalShelter.delAnimal(idToDelete);
                    System.out.println("Borrando animal...");
                    break;
                case 5:
                    System.out.println("Por favor, introduce el ID del animal que quieres consultar: ");
                    String idToSearch = scanner.nextLine();
                    Animal animalSearch = animalShelter.searchAnimal(idToSearch);
                    System.out.println("Consultando animal...");
                    System.out.println(animalSearch);
                    break;
                case 6:
                    System.out.println("Mostrando todos los animales...");
                    animalShelter.getAnimals().forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }
    }
}