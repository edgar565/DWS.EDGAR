package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class AnimalProtector {

    @JacksonXmlElementWrapper(localName = "animals")
    @JacksonXmlProperty(localName = "animal")
    List<Animal> animals;
    public AnimalProtector(List<Animal> animales) {
        this.animals = animales;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public void delAnimal(String id) {
        animals.removeIf(animal -> animal.getId().equals(id));
    }

    public Animal searchAnimal(String id) {
        return animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public String toString() {
        return "Protectora{" +
                "animales=" + animals +
                '}';
    }
}