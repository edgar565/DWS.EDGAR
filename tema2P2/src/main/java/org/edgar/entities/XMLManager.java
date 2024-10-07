package org.edgar.entities;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLManager {
    private static final Path path = Path.of("tema2P2/src/main/resources/animals.xml");

    public List<Animal> readXML() {
        try{
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(path.toFile(), new TypeReference<List<Animal>>() {});
        } catch (IOException e){
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }
    }
    public void writeXML(AnimalShelter animals) {
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(path.toFile(), animals);
        } catch (IOException e){
            System.out.println("Error al guardar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }
    }
}