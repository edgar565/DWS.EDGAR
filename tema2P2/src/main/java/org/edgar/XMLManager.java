package org.edgar;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XMLManager {
    private static final String FILE_PATH = "src/main/resources/animales.xml";
    private XmlMapper xmlMapper;

    public XMLManager() {
        this.xmlMapper = new XmlMapper();
    }

    public Protectora cargarDesdeXML() throws IOException {
        return xmlMapper.readValue(new File(FILE_PATH), Protectora.class);
    }

    public void guardarEnXML(Protectora protectora) throws IOException {
        xmlMapper.writeValue(new File(FILE_PATH), protectora);
    }
}