package org.edgar;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.edgar.entities.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBManager {
    private final MongoCollection<Document> profiles; // Colección de perfiles
    private Profile myProfile; // Mi perfil

    public MongoDBManager(String uri, String databaseName, String collectionName) {
        MongoClient mongoClient;
        try {
            mongoClient = MongoClients.create(uri);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

            profiles = database.getCollection(collectionName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createProfile(String name, String status, int age) {
        LocalDate since = LocalDate.now();
        myProfile = new Profile(name, status, age, since, null);
        Document profileDoc = new Document("name", name)
                .append("status", status)
                .append("age", age)
                .append("posts", new ArrayList<>());
        profiles.insertOne(profileDoc);
        System.out.println("Perfil creado para " + name);
    }

    public void publishPost(String title, String content) {
        Document post = new Document("title", title)
                .append("content", content)
                .append("date", new Date())
                .append("likes", 0)
                .append("comments", new ArrayList<>());

        profiles.updateOne(
                new Document("name", myProfile.getName()),
                new Document("$push", new Document("posts", post))
        );
        System.out.println("Publicación añadida: " + title);
    }

    public void updateStatus(String status) {
        myProfile.setStatus(status);
        profiles.updateOne(
                new Document("name", myProfile.getName()),
                new Document("$set", new Document("status", status))
        );
        System.out.println("Estado actualizado a: " + status);
    }

    public void deleteProfile() {
        profiles.deleteOne(new Document("name", myProfile.getName()));
        myProfile = null;
        System.out.println("Perfil eliminado.");
    }

    public void showProfiles() {
        for (Document profile : profiles.find()) {
            System.out.println(profile.toJson());
        }
    }

    public void showPosts(String profileName) {
        Document profile = profiles.find(new Document("name", profileName)).first();
        if (profile != null) {
            List<Document> posts = profile.getList("posts", Document.class);
            for (Document post : posts) {
                System.out.println(post.toJson());
            }
        } else {
            System.out.println("Perfil no encontrado");
        }
    }

    public void likePost(String profileName, String title) {
        profiles.updateOne(
                new Document("name", profileName).append("posts.title", title),
                new Document("$inc", new Document("posts.$.likes", 1))
        );
        System.out.println("Like añadido a la publicación " + title);
    }

    public void commentPost(String profileName, String title, String comment) {
        Document commentDoc = new Document("text", comment).append("date", new Date());

        profiles.updateOne(
                new Document("name", profileName).append("posts.title", title),
                new Document("$push", new Document("posts.$.comments", commentDoc))
        );
        System.out.println("Comentario añadido a la publicación " + title);    }

    public void showProfileStats() {
        Document profile = profiles.find(new Document("name", myProfile.getName())).first();
        if (profile != null) {
            List<Document> posts = profile.getList("posts", Document.class);
            int totalPosts = posts.size();
            int totalLikes = posts.stream().mapToInt(p -> p.getInteger("likes", 0)).sum();
            int totalComments = posts.stream().mapToInt(p -> p.getList("comments", Document.class).size()).sum();

            System.out.println("Estadísticas de perfil:");
            System.out.println("Total de publicaciones: " + totalPosts);
            System.out.println("Total de likes: " + totalLikes);
            System.out.println("Total de comentarios: " + totalComments);
        }
    }

    public void showAllStats() {
        long totalProfiles = profiles.countDocuments();

        long totalPosts = profiles.aggregate(Arrays.asList(
                Aggregates.unwind("$posts"),
                Aggregates.count("totalPosts")
        )).first().getInteger("totalPosts");

        long totalLikes = profiles.aggregate(Arrays.asList(
                Aggregates.unwind("$posts"),
                Aggregates.group(null, Accumulators.sum("totalLikes", "$posts.likes"))
        )).first().getInteger("totalLikes");

        long totalComments = profiles.aggregate(Arrays.asList(
                Aggregates.unwind("$posts"),
                Aggregates.unwind("$posts.comments"),
                Aggregates.count("totalComments")
        )).first().getInteger("totalComments");

        System.out.println("Estadísticas generales de la red:");
        System.out.println("Total de perfiles: " + totalProfiles);
        System.out.println("Total de publicaciones: " + totalPosts);
        System.out.println("Total de likes: " + totalLikes);
        System.out.println("Total de comentarios: " + totalComments);
    }
}