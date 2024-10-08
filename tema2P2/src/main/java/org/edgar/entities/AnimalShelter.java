package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

@JacksonXmlRootElement(localName = "animalshelter")
public class AnimalShelter {
    @JacksonXmlElementWrapper(localName = "animals")
    @JacksonXmlProperty(localName = "animal")
    private List<Animal> animals;
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
    public void delAnimal(String id) {
        animals.removeIf(animal -> animal.getId().equals(id));
    }
    public Animal searchAnimal(String id) {
        Animal search = animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (search == null) {
            throw new IllegalArgumentException("Animal not found");}
        return search;
    }
}