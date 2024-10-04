package org.edgar.entities;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XMLManager {
    private static final String filePath =  "/home/edgsannic/IdeaProjects/DWS.EDGAR/tema2P2/src/main/resources/animals.xml";
    private XmlMapper xmlMapper;

    public XMLManager() {
        this.xmlMapper = new XmlMapper();
    }

    public AnimalProtector readXML(){
        try{
            return xmlMapper.readValue(new File(filePath), AnimalProtector.class);
        } catch (IOException e){
            System.out.println("Error al cargar los datos. Se creará una nueva lista de animales.");
            throw new RuntimeException(e);
        }
    }

}