package org.edgar.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonManager {
    private static final Path path = Path.of("tema2P3/src/main/resources/animal.json");
    public AnimalShelter readJson(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        try{
            return mapper.readValue(path.toFile(), AnimalShelter.class);
        } catch (IOException e){
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }
    }
    public void writeJson(AnimalShelter animals) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(path.toFile(), animals);
        } catch (IOException e){
            System.out.println("Error al guardar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }
    }
}