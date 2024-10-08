package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Animal {
    @JacksonXmlProperty(localName = "id")
    private String id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "species")
    private String species;

    @JacksonXmlProperty(localName = "age")
    private int age;

    @JacksonXmlProperty(localName = "gender")
    private String gender;

    @JacksonXmlProperty(localName = "entryDate")
    private String entryDate;

    @JacksonXmlProperty(localName = "adopted")
    private String adopted;


    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", adopted=" + adopted +
                '}';
    }
}