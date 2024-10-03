package org.edgar;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Protectora {

    @JacksonXmlElementWrapper(localName = "animales")
    @JacksonXmlProperty(localName = "animal")
    List<Animal> animales;

    public Protectora() {
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }

    public Protectora(List<Animal> animales) {
        this.animales = animales;
    }

    public void añadirAnimal(Animal animal) {
        this.animales.add(animal);
    }

    public void borrarAnimal(String id) {
        animales.removeIf(animal -> animal.getId().equals(id));
    }

    public Animal consultarAnimal(String id) {
        return animales.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public String toString() {
        return "Protectora{" +
                "animales=" + animales +
                '}';
    }
}