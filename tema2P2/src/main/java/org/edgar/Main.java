package org.edgar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        XMLManager xmlManager = new XMLManager();
        Protectora protectora;
        Scanner scanner = new Scanner(System.in);

        try {
            protectora = xmlManager.cargarDesdeXML();
            System.out.println("Cargando datos de XML...");
        } catch (IOException e) {
            protectora = new Protectora(new ArrayList<>());
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Añadir animal");
            System.out.println("2. Borrar animal");
            System.out.println("3. Consultar animal");
            System.out.println("4. Mostrar todos los animales");
            System.out.println("5. Guardar datos en XML");
            System.out.println("6. Salir");

            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Especie: ");
                    String especie = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Sexo: ");
                    String sexo = scanner.nextLine();
                    System.out.print("Fecha de ingreso (yyyy-MM-dd): ");
                    String fechaIngreso = scanner.nextLine();
                    System.out.print("¿Adoptado? (Si/No): ");
                    String adoptado = scanner.nextLine();

                    Animal nuevoAnimal = new Animal(id, nombre, especie, edad, sexo, fechaIngreso, adoptado);
                    protectora.añadirAnimal(nuevoAnimal);
                    System.out.println("Animal añadido con éxito.");
                    break;

                case 2:
                    System.out.print("Introduce el ID del animal a borrar: ");
                    String idBorrar = scanner.nextLine();
                    protectora.borrarAnimal(idBorrar);
                    System.out.println("Animal borrado.");
                    break;

                case 3:
                    System.out.print("Introduce el ID del animal a consultar: ");
                    String idConsultar = scanner.nextLine();
                    Animal animal = protectora.consultarAnimal(idConsultar);
                    if (animal != null) {
                        System.out.println(animal);
                    } else {
                        System.out.println("Animal no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Animales en la protectora:");
                    System.out.println(protectora);
                    break;

                case 5:
                    try {
                        xmlManager.guardarEnXML(protectora);
                        System.out.println("Datos guardados en el XML.");
                    } catch (Exception e) {
                        System.out.println("Error al guardar los datos.");
                    }
                    break;

                case 6:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}