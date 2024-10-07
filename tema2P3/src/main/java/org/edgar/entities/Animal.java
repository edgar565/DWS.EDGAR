package org.edgar.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Animal {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    private String name;
    private String species;
    private int age;
    private String sex;
    private String entryDate;
    private String adopted;



    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", adopted=" + adopted +
                '}';
    }
}
