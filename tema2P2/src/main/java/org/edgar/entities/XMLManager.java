package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XMLManager {
    private static final String FILE_PATH = "src/main/resources/animals.xml";
    private XmlMapper xmlMapper;

    public XMLManager() {
        this.xmlMapper = new XmlMapper();
    }

    public AnimalProtector readXML() throws IOException {
        return xmlMapper.readValue(new File(FILE_PATH), AnimalProtector.class);
    }

    public void writeXML(AnimalProtector animalProtector) throws IOException {
        xmlMapper.writeValue(new File(FILE_PATH), animalProtector);
    }
}