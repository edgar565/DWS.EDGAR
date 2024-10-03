package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

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

    public Animal(String id, String name, String species, int age, String gender, String entryDate, String adopted) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.gender = gender;
        this.entryDate = entryDate;
        this.adopted = adopted;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", nombre='" + name + '\'' +
                ", especie='" + species + '\'' +
                ", edad=" + age +
                ", sexo='" + gender + '\'' +
                ", fechaIngreso='" + entryDate + '\'' +
                ", adoptado='" + adopted + '\'' +
                '}';
    }

}