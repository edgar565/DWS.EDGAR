package org.example.harrypotter.repositories;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class HouseRepository {
    private List<House> houses = new ArrayList<>();

    public HouseRepository() {
        houses.add(new House("Gryffindor", "Lion", "Godric Gryffindor"));
        houses.add(new House("Hufflepuff", "Badger", "Helga Hufflepuff"));
        houses.add(new House("Ravenclaw", "Eagle", "Rowena Ravenclaw"));
        houses.add(new House("Slytherin", "Serpent", "Salazar Slytherin"));
    }

    public List<House> getHouses() {
        return houses;
    }

    public House getHouse(String name) {
        for (House house : houses) {
            if (house.getName().equals(name)) {
                return house;
            }
        }
        return null;
    }
    public void createHouse(House house) {
        houses.add(house);
    }
    public void updateHouse(String name, House house) {
        for (House h : houses) {
            if (h.getName().equals(name)) {
                h.setName(house.getName());
                h.setMascot(house.getMascot());
                h.setFounder(house.getFounder());
                break;
            }
        }
    }

    public void deleteHouse(String name) {
        for (House h : houses) {
            if (h.getName().equals(name)) {
                houses.remove(h);
                break;
            }
        }
    }
}