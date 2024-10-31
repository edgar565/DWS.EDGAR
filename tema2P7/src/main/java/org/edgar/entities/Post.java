package org.edgar.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@BsonDiscriminator( value = "post", key = "_cls" )
public class Post {
    @BsonProperty(value = "title")
    String title; // título del post
    @BsonProperty(value = "content")
    String content; // contenido del post
    @BsonProperty(value = "publishedDate")
    LocalDate publishedDate; // fecha de creación del post
    @BsonProperty(value = "likes")
    int likes; // cantidad de likes del post
    @BsonProperty(value = "comments")
    List<String> comments; // lista de comentarios del post

    public Post(@BsonProperty("title") String title, @BsonProperty("content") String content, @BsonProperty("publishedDate") LocalDate publishedDate, @BsonProperty("likes") int likes, @BsonProperty("comments") List<String> comments) {
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.likes = likes;
        this.comments = comments;
    }

    @Override
    public String toString() {
        String string = title + "\n" + publishedDate + "\n" + likes + " likes\n" + content + "\n";
        for (String comment : comments) {
            string += " - " + comment + "\n";
        }
        return string;
    }
}