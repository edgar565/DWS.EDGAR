package org.edgar.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@BsonDiscriminator(value = "profile", key = "_cls")
public class Profile {
    @BsonId
    Object id;
    @BsonProperty(value = "name")
    String name;
    @BsonProperty(value = "status")
    String status;
    @BsonProperty(value = "age")
    int age;
    @BsonProperty(value = "since")
    LocalDate since;
    @BsonProperty(value = "posts")
    List<Post> posts;

    @BsonCreator
    public Profile(@BsonProperty("name") String name, @BsonProperty("status") String status, @BsonProperty("age") int age) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = LocalDate.now();
    }
    public Profile(@BsonProperty(value="name")String name, @BsonProperty(value="status")String status,@BsonProperty(value="age")int age,@BsonProperty(value = "posts")List<Post> posts) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = LocalDate.now();
        this.posts = posts;
    }
    @Override
    public String toString() {
        String string = "-".repeat(20) + "\n" + name + "\nUsuario desde: " + since + "\nEstado:" + status + "\nEdad: " + age + " años\n";
        if (posts == null) {
            string += "No ha publicado nada todavía.\n";
        }
        string += "-".repeat(20);
        return string;
    }
}
