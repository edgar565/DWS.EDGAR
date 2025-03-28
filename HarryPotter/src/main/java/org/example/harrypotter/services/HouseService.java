package org.example.harrypotter.services;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;

import java.util.List;

public interface HouseService {
    List<House> getHouses();
    House getHouseByName(String name);
    void createHouse(House house);
    void updateHouse(String name, House house);
    void deleteHouse(String name);
}