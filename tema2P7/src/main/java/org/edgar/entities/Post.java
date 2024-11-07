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
    String title;
    @BsonProperty(value = "content")
    String content;
    @BsonProperty(value = "publishedDate")
    LocalDate publishedDate;
    @BsonProperty(value = "likes")
    int likes;
    @BsonProperty(value = "comments")
    List<String> comments;

    public Post(@BsonProperty("title") String title, @BsonProperty("content") String content, @BsonProperty("comments") List<String> comments) {
        this.title = title;
        this.content = content;
        this.publishedDate = LocalDate.now();
        this.comments = comments;
    }
    public void addComment(String comment) {
        comments.add(comment);
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