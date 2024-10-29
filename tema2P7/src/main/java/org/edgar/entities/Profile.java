package org.edgar.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@BsonDiscriminator(value = "profile", key = "_cls")
public class Profile {
    @BsonProperty(value = "name")
    String name; // nombre del perfil
    @BsonProperty(value = "status")
    String status; // estado del perfil
    @BsonProperty(value = "age")
    int age; // edad del perfil
    @BsonProperty(value = "since")
    LocalDate since; // fecha de creación del perfil
    @BsonProperty(value = "posts")
    List<Post> posts; // lista de amigos del perfil

    public Profile(@BsonProperty("name") String name, @BsonProperty("status") String status, @BsonProperty("age") int age, @BsonProperty("since") LocalDate since, @BsonProperty("posts") List<Post> posts) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = since;
        this.posts = posts;
    }

    @Override
    public String toString() {
        String string = "-".repeat(20) + "\n" + name + "\nUsuario desde: " + since + "\nEstado:" + status + "\nEdad: " + age + " años\n";
        if (posts != null) {
            string += "Publicaciones:\n";
            for (Post post : posts) {
                string += post + "\n";
            }
        } else {
            string += "No ha publicado nada todavía.\n";
        }
        string += "-".repeat(20);
        return string;
    }
}
