package org.edgar;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Animal {
    @JacksonXmlProperty(localName = "id")
    private String id;

    @JacksonXmlProperty(localName = "nombre")
    private String nombre;

    @JacksonXmlProperty(localName = "especie")
    private String especie;

    @JacksonXmlProperty(localName = "edad")
    private int edad;

    @JacksonXmlProperty(localName = "sexo")
    private String sexo;

    @JacksonXmlProperty(localName = "fechaIngreso")
    private String fechaIngreso;

    @JacksonXmlProperty(localName = "adoptado")
    private String adoptado;

    public Animal(String id, String nombre, String especie, int edad, String sexo, String fechaIngreso, String adoptado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
        this.adoptado = adoptado;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", adoptado='" + adoptado + '\'' +
                '}';
    }

}