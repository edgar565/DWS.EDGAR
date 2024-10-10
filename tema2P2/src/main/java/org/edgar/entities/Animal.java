package org.edgar.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate entryDate;

    @JacksonXmlProperty(localName = "adopted")
    private Boolean adopted;


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